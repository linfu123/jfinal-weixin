package com.jfinal.weixin.demo;

import org.eclipse.jetty.util.StringUtil;

import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CallbackIpApi;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.service.DemoService;
/**
 * 接口测试类
 * @author Administrator
 *
 */
public class DemoController extends Controller{
	
	public DemoService srv = DemoService.me;
	/**
	 * 正常参数测试
	 */
	public void addInfo(){
		String aa = getPara(0);
		System.out.println(aa);
		String name = getPara("name");
		String idNO = getPara("idno");
		String phone = getPara("phone");
		if(StringUtil.isBlank(name)||StringUtil.isBlank(idNO)||StringUtil.isBlank(phone)){
			System.out.println("上传参数不正确");
			render("参数不正确");
		}else{
			boolean bool = srv.addInfo(name, idNO, phone);
			if(bool){
//				String jsonStr = "{\"touser\":\"oI1ejwwt2u7EMMJT9UyuQEIhQ02k\",\"template_id\":\"eKOqlkxxjUPC0-4oZImcYyDOvVmGyjFXlpOPklG_pm8\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"name\": {\"value\":\"黄先生\",\"color\":\"#173177\"}}}";
//				ApiResult apiResult = TemplateMsgApi.send(jsonStr);
//		    	renderText(apiResult.getJson());
//				renderText("success");
				
				System.out.println(ApiConfigKit.getAppId());
				renderText(UserApi.getUserInfo(ApiConfigKit.getAppId()).getJson());
			}else{
				renderText("error");
			}
			
		}
		
	}
	
	/**
	 * 微信服务器IP测试
	 */
	public void getIP(){
		renderText(CallbackIpApi.getCallbackIp().getJson());
	}
	
	
	public void templetInfo(){
		String jsonStr = "{\"touser\":\"oI1ejwwt2u7EMMJT9UyuQEIhQ02k\",\"template_id\":\"eKOqlkxxjUPC0-4oZImcYyDOvVmGyjFXlpOPklG_p\",\"url\":\"http://weixin.qq.com/download\",\"topcolor\":\"#FF0000\",\"data\":{\"name\": {\"value\":\"黄先生\",\"color\":\"#173177\"}}";
		ApiResult apiResult = TemplateMsgApi.send(jsonStr);
    	renderText(apiResult.getJson());
	}
	
	public void getUserInfo(){
		String code = getPara("code");
	}

}
