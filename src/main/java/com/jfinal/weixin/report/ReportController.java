package com.jfinal.weixin.report;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.model.TblSchool;
import com.jfinal.weixin.utils.XLSFileKit;
import com.jfinal.wxaapp.util.RetUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportController extends Controller {

    private ReportService reportService = ReportService.me;

    public void index(){
         render("../page/report.html");
    }

    public void loadReport(){
        render("../page/loadReport.html");
    }

    public void total(){
        String date = getPara("date");
        date = date.replaceAll("\\/","-");
        String school = getPara("school");
        String grade = getPara("grade");
        String dormitory = getPara("dormitory");
        List<Map<String,Object>> list = reportService.total(school,grade,dormitory,date);
       if(list.size()>0){
          renderJson(RetUtil.ok(list));
       }else{
           renderJson( RetUtil.fail());
       }
    }

    public void downloadR(){
        String date = getPara("date");
        String school = getPara("school");
        String grade = getPara("grade");
        String dormitory = getPara("dormitory");
        String fileName = "";
        String filePath ="";
        if(date.isEmpty() && school.isEmpty() && grade.isEmpty() && dormitory.isEmpty()){
            fileName = "全部人员通行信息.xls";
        }else{
            if(!date.isEmpty()){
                date = date.replaceAll("\\/","-");
                fileName += date +"_";
            }
            if(!school.isEmpty()){
                fileName += school +"_";
            }
            if(!grade.isEmpty()){
                fileName += grade +"_";
            }
            if(!dormitory.isEmpty()){
                fileName += dormitory +"_";
            }
            fileName = fileName.substring(0,fileName.length()-1);
            fileName+=".xls";
        }
        filePath = getRequest().getRealPath("/")+"/file/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        filePath += fileName;
        XLSFileKit xlsFileKit = new XLSFileKit(filePath);
        List<List<Object>> content = new ArrayList<List<Object>>();
        List<String> title = new ArrayList<String>();
        List<Record> listData = reportService.downloadData(school,grade,dormitory,date);
        title.add("序号");
        title.add("学校名称");
        title.add("姓名");
        title.add("学号");
        title.add("班级");
        title.add("宿舍");
        title.add("体温");
        title.add("体温状态");
        title.add("测量时间");
        int i = 0;
        OK:
        while (true){
            if (listData.size() < (i + 1)) {
                break OK;
            }
            // 判断单元格是否为空，不为空添加数据
            int index = i + 1;
            List<Object> row = new ArrayList<Object>();
            row.add(index + "");
            row.add(null == listData.get(i).get("schoolName") ? "" : listData.get(i).get("schoolName"));
            row.add(null == listData.get(i).get("userName") ? "" : listData.get(i).get("userName"));
            row.add(null == listData.get(i).get("studentNumber") ? "" : listData.get(i).get("studentNumber"));
            row.add(null == listData.get(i).get("groupName") ? "" : listData.get(i).get("groupName"));
            row.add(null == listData.get(i).get("dormitory") ? "" : listData.get(i).get("dormitory"));
            row.add(null == listData.get(i).get("animalHeat") ? "" : listData.get(i).get("animalHeat"));
            row.add("normal" == listData.get(i).get("status") ? "正常" : "异常");
            row.add(null == listData.get(i).get("date") ? "" : listData.get(i).get("date"));
            content.add(row);
            i++;
        }
        xlsFileKit.addSheet(content, fileName.replace(".xls",""), title);
        boolean save = xlsFileKit.save();
        if (save) {
            File file1 = new File(getRequest().getRealPath("/") + "/file/"+fileName);
            renderFile(file1);
        } else {
            renderText("报表生成失败");
        }

    }

    public void schoolList(){
        List<TblSchool> list = reportService.findSchoolList();
        if(list.size()>0){
            renderJson(RetUtil.ok(list));
        }else{
            renderJson(RetUtil.fail());
        }

    }

    public void gradeList(){
        String school = getPara("school");
        List<Record> list = reportService.findGradeList(school);
        if(list.size()>0){
            renderJson(RetUtil.ok(list));
        }else{
            renderJson(RetUtil.fail());
        }
    }

    public void dormitoryList(){
        String school = getPara("school");
        List<Record> list = reportService.findDormitoryList(school);
        if(list.size()>0){
            renderJson(RetUtil.ok(list));
        }else{
            renderJson(RetUtil.fail());
        }
    }
}
