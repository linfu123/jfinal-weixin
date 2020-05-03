package com.jfinal.weixin.service;

import com.jfinal.weixin.model.WUser;

public class DemoService {
	
	public static final	DemoService me = new DemoService();
	
	public boolean addInfo(String name,String idNO,String phone){
		//获取jxt.sql 文件已经在插件加载时加载完毕 
		//Sqlpara sql = Db.getSqlPara(key, data); key为jxt文件中的名称，data为传入的参数（Map）
		//Sqlpara sql = dao.getSqlPara(key, data)
		return new WUser().set("name",name).set("idno", idNO).set("phone", phone).save();
	}

}
