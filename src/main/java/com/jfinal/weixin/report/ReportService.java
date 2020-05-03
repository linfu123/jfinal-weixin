package com.jfinal.weixin.report;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.model.TbStatement;
import com.jfinal.weixin.model.TbTemperatureRecord;
import com.jfinal.weixin.model.TblSchool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    public static final ReportService me =new ReportService();

    public List<TbTemperatureRecord> test(){
        return TbTemperatureRecord.dao.find("select * from tb_temperature_record");
    }

    public List<Map<String,Object>> total(String school,String grade,String dormitory,String date){
        List<Map<String,Object>> list = new ArrayList<>();
        if(school.isEmpty()){
            List<TblSchool> schoollist = TblSchool.dao.find("select * from tbl_school");
            for(int i=0;i<schoollist.size();i++){
                Map<String,Object> map = new HashMap<>();
                map.put("school",schoollist.get(i).getSchoolName());
                //总人数sql查询语句
                String schoolStudentNumsql = "select count(*) from tb_personnel p \n" +
                    "LEFT JOIN tbl_device d ON d.swjCode = p.expt1\n" +
                    "LEFT JOIN tbl_school s ON d.schoolId = s.id where s.id = "+schoollist.get(i).getId();
                //通行次数sql查询语句
                String accessCountsql = "select count(*) from tb_statement where schoolId = "+schoollist.get(i).getId();
                //实际通行人数查询
                String realAccessCountsql = "select count(DISTINCT studentNumber,userName ) FROM tb_statement where schoolId = "+schoollist.get(i).getId();
                //异常体温人数查询
                String abnormalCountsql = "select count(DISTINCT studentNumber,userName ) FROM tb_statement where animalHeat >= 37.4 and schoolId = "+schoollist.get(i).getId();

                //判断前端grade，dormitory，date传参
                if(!grade.isEmpty()){
                    schoolStudentNumsql +=" and p.groupName = '"+grade+"'";
                    accessCountsql +=" and groupName = '"+grade+"'";
                    realAccessCountsql +=" and groupName = '"+grade+"'";
                    abnormalCountsql += " and groupName = '"+grade+"'";
                }
                if(!dormitory.isEmpty()){
                    schoolStudentNumsql +=" and p.dormitory = '"+dormitory+"'";
                    accessCountsql +=" and dormitory = '"+dormitory+"'";
                    realAccessCountsql +=" and dormitory = '"+dormitory+"'";
                    abnormalCountsql +=" and dormitory = '"+dormitory+"'";
                }
                if(!date.isEmpty()){
                    accessCountsql += " and left(date,10) = '"+date+"'";
                    realAccessCountsql += " and left(date,10) = '"+date+"'";
                    abnormalCountsql +=" and left(date,10) = '"+date+"'";
                }
              //  int schoolStudentNum = Db.queryInt(schoolStudentNumsql);
                map.put("schoolStudentNum",Db.queryInt(schoolStudentNumsql));
             //   int accessCount =  Db.queryInt(accessCountsql);
                map.put("accessCount",Db.queryInt(accessCountsql));
             //   int realAccessCount = Db.queryInt(realAccessCountsql);
                map.put("realAccessCount",Db.queryInt(realAccessCountsql));
              //  int abnormalCount = Db.queryInt(abnormalCountsql);
                map.put("abnormalCount",Db.queryInt(abnormalCountsql));
                list.add(map);
            }
        }else{
            List<TblSchool> schoollist =  TblSchool.dao.find("select * from tbl_school where schoolName = '"+school+"'");
            System.out.println(schoollist.size());
            if(schoollist.size()>0){
                TblSchool tblSchool = schoollist.get(0);
                Map<String,Object> map = new HashMap<>();
                map.put("school",tblSchool.getSchoolName());
                //总人数sql查询语句
                String schoolStudentNumsql = "select count(*) from tb_personnel p \n" +
                    "LEFT JOIN tbl_device d ON d.swjCode = p.expt1\n" +
                    "LEFT JOIN tbl_school s ON d.schoolId = s.id where s.id = "+tblSchool.getId();
                //通行次数sql查询语句
                String accessCountsql = "select count(*) from tb_statement where schoolId = "+tblSchool.getId();
                //实际通行人数查询
                String realAccessCountsql = "select count(DISTINCT studentNumber,userName ) FROM tb_statement where schoolId = "+tblSchool.getId();
                //异常体温人数查询
                String abnormalCountsql = "select count(DISTINCT studentNumber,userName ) FROM tb_statement where animalHeat >= 37.4 and schoolId = "+tblSchool.getId();
                //判断前端grade，dormitory，date传参
                if(!grade.isEmpty()){
                    schoolStudentNumsql +=" and p.groupName = '"+grade+"'";
                    accessCountsql +=" and groupName = '"+grade+"'";
                    realAccessCountsql +=" and groupName = '"+grade+"'";
                    abnormalCountsql += " and groupName = '"+grade+"'";
                }
                if(!dormitory.isEmpty()){
                    schoolStudentNumsql +=" and p.dormitory = '"+dormitory+"'";
                    accessCountsql +=" and dormitory = '"+dormitory+"'";
                    realAccessCountsql +=" and dormitory = '"+dormitory+"'";
                    abnormalCountsql +=" and dormitory = '"+dormitory+"'";
                }
                if(!date.isEmpty()){
                    accessCountsql += " and left(date,10) = '"+date+"'";
                    realAccessCountsql += " and left(date,10) = '"+date+"'";
                    abnormalCountsql +=" and left(date,10) = '"+date+"'";
                }
                map.put("schoolStudentNum",Db.queryInt(schoolStudentNumsql));
                map.put("accessCount",Db.queryInt(accessCountsql));
                map.put("realAccessCount",Db.queryInt(realAccessCountsql));
                map.put("abnormalCount",Db.queryInt(abnormalCountsql));
                list.add(map);
            }
        }
        return list;
    }


    public List<Record> downloadData(String schoolName,String grade,String dormitory,String date){
        String sql = "select *,s.schoolName from tb_statement t LEFT JOIN tbl_school s on t.schoolId = s.id ";
        if(!schoolName.isEmpty()){
            sql+="where s.schoolName = '"+schoolName+"'";
            if( !grade.isEmpty()){
                sql +=" and t.groupName = '"+grade+"'";
            }
            if(!dormitory.isEmpty()){
                sql +=" and t.dormitory = '"+dormitory+"'";
            }
            if(!date.isEmpty()){
                sql +=" and left(t.date,10) = '"+date+"'";
            }
            return Db.find(sql);
        }
        if(!grade.isEmpty()){
            sql+="where t.groupName = '"+grade+"'";
            if(!schoolName.isEmpty()){
                sql +=" and s.schoolName = '"+schoolName+"'";
            }
            if(!dormitory.isEmpty()){
                sql +=" and t.dormitory = '"+dormitory+"'";
            }
            if(!date.isEmpty()){
                sql +=" and left(t.date,10) = '"+date+"'";
            }
            return Db.find(sql);
        }
        if(!dormitory.isEmpty()){
            sql+="where t.dormitory = '"+dormitory+"'";
            if(!schoolName.isEmpty()){
                sql +=" and s.schoolName = '"+schoolName+"'";
            }
            if(!grade.isEmpty()){
                sql +=" and t.groupName = '"+grade+"'";
            }
            if(!date.isEmpty()){
                sql +=" and left(t.date,10) = '"+date+"'";
            }
            return Db.find(sql);
        }
        if(!date.isEmpty()){
            sql+="where left(t.date,10) = '"+date+"'";
            if(!schoolName.isEmpty()){
                sql +=" and s.schoolName = '"+schoolName+"'";
            }
            if(!grade.isEmpty()){
                sql +=" and t.dormitory = '"+grade+"'";
            }
            if(!dormitory.isEmpty()){
                sql +=" and  t.dormitory= '"+dormitory+"'";
            }
            return Db.find(sql);
        }
        return Db.find(sql);
    }

    public List<TblSchool> findSchoolList(){
        return TblSchool.dao.find("select * from tbl_school");
    }
    public List<Record> findGradeList(String school){
        return Db.find("SELECT DISTINCT p.groupName from tb_personnel p LEFT JOIN tbl_device d on p.expt1 = d.swjCode LEFT JOIN tbl_school s on s.id = d.schoolId where s.schoolName = ?",school);
    }
    public List<Record> findDormitoryList(String school){
        return Db.find("SELECT DISTINCT p.dormitory from tb_personnel p LEFT JOIN tbl_device d on p.expt1 = d.swjCode LEFT JOIN tbl_school s on s.id = d.schoolId where s.schoolName = ?",school);
    }

    public int updateStatement(String stuNum){
        return Db.update("update tb_statement\n" +
            "set studentNumber = '"+stuNum+"',\n" +
            "userName = (SELECT userName FROM tb_personnel where studentNumber = '"+stuNum+"'),\n" +
            "dormitory = (SELECT dormitory FROM tb_personnel where studentNumber = '"+stuNum+"'),\n" +
            "groupName = (SELECT groupName FROM tb_personnel where studentNumber = '"+stuNum+"')\n" +
            "where id =  floor(RAND()*(151754-128848)+128848)");
    }

    public List<TbStatement> selectStatement(String stuNum){
        return TbStatement.dao.find("select * from tb_statement where studentNumber = '" + stuNum + "'");
    }

    public int deleteStatement(int id,String stuNum){
        return Db.delete("delete  from tb_statement where id > " + id + " and studentNumber = '" + stuNum + "'");
    }
}
