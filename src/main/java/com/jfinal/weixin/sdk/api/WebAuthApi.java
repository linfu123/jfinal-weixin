package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * 微信网页授权页面
 * 
 * @author wgm 1 第一步：用户同意授权，获取code
 * 
 *         2 第二步：通过code换取网页授权access_token 微信网页授权是通过OAuth2.0机制实现的，在用户授权给公众号后，
 *         公众号可以获取到一个网页授权特有的接口调用凭证（网页授权access_token），
 *         通过网页授权access_token可以进行授权后接口调用，如获取用户基本信息；
 * 
 *         3 第三步：刷新access_token（如果需要）
 * 
 *         4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 * 
 */
public class WebAuthApi {

	private static String agreeAuth = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	/**
	 * 用户同意授权，获取code
	 * 
	 * @param appid
	 * @param redirect_uri
	 * @return
	 */
	private static ApiResult agreeAuth(String redirect_uri) {
		String post_uri = agreeAuth.replaceAll("APPID", ApiConfigKit.getAppId()).replaceAll("REDIRECT_URI",
				redirect_uri);
		return new ApiResult(HttpUtils.post(post_uri, null));
	}

	private static String authAccessTokenUri = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	/**
	 * 通过CODE获得用户OPenID
	 * 
	 * @param code
	 * @return { "access_token":"ACCESS_TOKEN", "expires_in":7200,
	 *         "refresh_token":"REFRESH_TOKEN", "openid":"OPENID",
	 *         "scope":"SCOPE" }
	 */
	private static ApiResult getAccessToken(String code) {
		String postAcc_uri = authAccessTokenUri.replaceAll("APPID", ApiConfigKit.getAppId())
				.replaceAll("SECRET", PropKit.get("appSecret")).replaceAll("CODE", code);
		return new ApiResult(HttpUtils.post(postAcc_uri, null));
	}

	// 通过网页授权获取用户的基本信息
	private static String userInfoURI = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 
	 * @param access_token
	 * @param openid
	 * @return
	 */
	private static ApiResult getUserInfoAuth2(String access_token, String openid) {
		String getUrserInfoURI = userInfoURI.replaceAll("ACCESS_TOKEN", access_token).replaceAll("OPENID", openid);
		return new ApiResult(HttpUtils.get(getUrserInfoURI));

	}

	/**
	 * 刷新access_token
	 */

	private static String refreshTokenURI = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	private static ApiResult refreshAccessToken(String openid, String refresh_token) {
		String refreshURI = refreshTokenURI.replaceAll("OPENID", openid).replaceAll("REFRESH_TOKEN", refresh_token);
		return new ApiResult(HttpUtils.get(refreshURI));
	}

	// 判断用户网页授权Access_token 是否有效
	private static String checkAccessTokenURI = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

	private static boolean checkAccessToken(String access_token, String openid) {
		boolean flag = true;
		String checkURI = checkAccessTokenURI.replaceAll("ACCESS_TOKEN", access_token).replaceAll("OPENID", openid);
		flag = new ApiResult(HttpUtils.get(checkURI)).isSucceed();
		return flag;
	}

}
