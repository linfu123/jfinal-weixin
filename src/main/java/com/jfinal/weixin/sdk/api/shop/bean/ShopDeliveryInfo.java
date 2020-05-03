package com.jfinal.weixin.sdk.api.shop.bean;

/**
 * 运费信息
 * 
 * @author wgm
 *
 */
public class ShopDeliveryInfo {
	private String delivery_type;// 运费类型(0-使用下面express字段的默认模板,
									// 1-使用template_id代表的邮费模板, 详见邮费模板相关API)
	private String template_id;// 邮费模板ID
	private Express express;

	public String getDelivery_type() {
		return delivery_type;
	}

	public void setDelivery_type(String delivery_type) {
		this.delivery_type = delivery_type;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public static class Express {
		private String id;// 快递ID
		private String price;// 运费(单位 : 分)

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

	}

}
