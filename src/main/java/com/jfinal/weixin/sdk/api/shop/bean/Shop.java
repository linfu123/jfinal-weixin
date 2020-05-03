package com.jfinal.weixin.sdk.api.shop.bean;

import java.util.List;

/**
 * 商品属性
 * @author wgm
 *
 */
public class Shop {
	private ShopBaseAttr shopbase;//商品基本信息
	private List<SKU> skulist;//sku信息列表
	private ShopAttrExt shopattrext;//商品附加属性
	private ShopDeliveryInfo shopdeliveryinfo;//商品快递信息
	public ShopBaseAttr getShopbase() {
		return shopbase;
	}
	public void setShopbase(ShopBaseAttr shopbase) {
		this.shopbase = shopbase;
	}
	public List<SKU> getSkulist() {
		return skulist;
	}
	public void setSkulist(List<SKU> skulist) {
		this.skulist = skulist;
	}
	public ShopAttrExt getShopattrext() {
		return shopattrext;
	}
	public void setShopattrext(ShopAttrExt shopattrext) {
		this.shopattrext = shopattrext;
	}
	public ShopDeliveryInfo getShopdeliveryinfo() {
		return shopdeliveryinfo;
	}
	public void setShopdeliveryinfo(ShopDeliveryInfo shopdeliveryinfo) {
		this.shopdeliveryinfo = shopdeliveryinfo;
	}
	
}
