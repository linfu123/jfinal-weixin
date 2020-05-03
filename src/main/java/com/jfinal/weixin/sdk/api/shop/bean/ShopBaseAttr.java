package com.jfinal.weixin.sdk.api.shop.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 微信小店基本参数定义类
 * @author wgm 2020-03-17
 * 
 *
 */
public class ShopBaseAttr implements Serializable{
	private String name;//商品名称
	private String category;//商品分类id
	private String main_img;//商品主图URL
	private List<String> img;//商品图片列表URL
	private List<Detail> details;//商品详情列表，显示在客户端的商品详情页内
	private List<Property> propertys;//商品属性列表，属性列表请通过《获取指定分类的所有属性》获取
	private List<SkuInfo> sku_infos;//商品sku定义，SKU列表请通过《获取指定子分类的所有SKU》获取
	private String buy_limit;//用户商品限购数量
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMain_img() {
		return main_img;
	}
	public void setMain_img(String main_img) {
		this.main_img = main_img;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	public List<Property> getPropertys() {
		return propertys;
	}
	public void setPropertys(List<Property> propertys) {
		this.propertys = propertys;
	}
	public List<SkuInfo> getSku_infos() {
		return sku_infos;
	}
	public void setSku_infos(List<SkuInfo> sku_infos) {
		this.sku_infos = sku_infos;
	}
	public String getBuy_limit() {
		return buy_limit;
	}
	public void setBuy_limit(String buy_limit) {
		this.buy_limit = buy_limit;
	}
	/**
	 * 商品详情列表
	 * @author Administrator
	 *
	 */
	public static class Detail{
		private String text;
		private String img;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		
	}
	/**
	 * 商品属性列表
	 * @author Administrator
	 *
	 */
	public static class Property{
		//商品属性列表，属性列表请通过《获取指定分类的所有属性》获取
		private String id;
		private String vid;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getVid() {
			return vid;
		}
		public void setVid(String vid) {
			this.vid = vid;
		}
		
	}
	/**
	 * 产品sku定义列表
	 * @author Administrator
	 *
	 */
	public static class SkuInfo{
		//商品sku定义，SKU列表请通过《获取指定子分类的所有SKU》获取
		private String id;
		private String vid;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getVid() {
			return vid;
		}
		public void setVid(String vid) {
			this.vid = vid;
		}
		
		
		
	}

}
