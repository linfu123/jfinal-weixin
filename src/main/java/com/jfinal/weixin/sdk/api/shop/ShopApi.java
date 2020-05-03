package com.jfinal.weixin.sdk.api.shop;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.shop.bean.Shop;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 微信商品API
 * 
 * @author Administrator
 *
 */
public class ShopApi {

	// 添加商品
	private static String createshopuri = "https://api.weixin.qq.com/merchant/create?access_token=";

	/**
	 * 
	 * @param jonsStr
	 *            shop类转化
	 * @return
	 */
	public static ApiResult createshop(Shop shop) {
		String jonstr = HttpUtils.post(createshopuri + AccessTokenApi.getAccessTokenStr(), JsonUtils.toJson(shop));
		return new ApiResult(jonstr);
	}

	//删除商品信息
	private static String delshopuri = "https://api.weixin.qq.com/merchant/del?access_token=";

	public static ApiResult delshop(String product_id) {
		Map<String, String> mapData = new HashMap<String, String>();
		if (StrKit.notBlank(product_id)) {
			mapData.put("product_id", product_id);
		}

		String jonStr = HttpUtils.post(delshopuri + AccessTokenApi.getAccessTokenStr(), JsonUtils.toJson(mapData));
		return new ApiResult(jonStr);
	}

}
