package com.jfinal.weixin.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTblSchool<M extends BaseTblSchool<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setSchoolName(java.lang.String schoolName) {
		set("schoolName", schoolName);
		return (M)this;
	}
	
	public java.lang.String getSchoolName() {
		return getStr("schoolName");
	}

	public M setSwjCode(java.lang.String swjCode) {
		set("swjCode", swjCode);
		return (M)this;
	}
	
	public java.lang.String getSwjCode() {
		return getStr("swjCode");
	}

}
