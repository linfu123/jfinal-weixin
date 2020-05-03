package com.jfinal.weixin.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTxOption<M extends BaseTxOption<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setIcontext(java.lang.String icontext) {
		set("icontext", icontext);
		return (M)this;
	}
	
	public java.lang.String getIcontext() {
		return getStr("icontext");
	}

	public M setIconUrl(java.lang.String iconUrl) {
		set("iconUrl", iconUrl);
		return (M)this;
	}
	
	public java.lang.String getIconUrl() {
		return getStr("iconUrl");
	}

	public M setPageUrl(java.lang.String pageUrl) {
		set("pageUrl", pageUrl);
		return (M)this;
	}
	
	public java.lang.String getPageUrl() {
		return getStr("pageUrl");
	}

}
