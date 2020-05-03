package com.jfinal.weixin.demo;

import com.jfinal.weixin.service.InformationService;
import com.jfinal.wxaapp.jfinal.WxaController;
import com.jfinal.wxaapp.util.RetUtil;
/**
 * 获取首页信息
 * @author Administrator
 *
 */
public class WxaInfomationController extends WxaController {
	
	InformationService infomation = InformationService.info;
	
	
	public void getBanner(){
		renderJson(RetUtil.ok(infomation.getBanner()));
		
	}
	
	public void getNews(){
		renderJson(RetUtil.ok(infomation.getNews()));
		
	}
	
	public void getNotices(){
		renderJson(RetUtil.ok(infomation.getNotice()));
	}
	
	public void getOption(){
		renderJson(RetUtil.ok(infomation.getOption()));
	}

}
