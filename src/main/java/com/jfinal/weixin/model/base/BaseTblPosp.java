package com.jfinal.weixin.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTblPosp<M extends BaseTblPosp<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setOrgId(java.lang.Long orgId) {
		set("orgId", orgId);
		return (M)this;
	}
	
	public java.lang.Long getOrgId() {
		return getLong("orgId");
	}

	public M setPospCode(java.lang.String pospCode) {
		set("pospCode", pospCode);
		return (M)this;
	}
	
	public java.lang.String getPospCode() {
		return getStr("pospCode");
	}

	public M setPospName(java.lang.String pospName) {
		set("pospName", pospName);
		return (M)this;
	}
	
	public java.lang.String getPospName() {
		return getStr("pospName");
	}

	public M setCstatus(java.lang.String cstatus) {
		set("cstatus", cstatus);
		return (M)this;
	}
	
	public java.lang.String getCstatus() {
		return getStr("cstatus");
	}

	public M setRonDomNum(java.lang.String ronDomNum) {
		set("ronDomNum", ronDomNum);
		return (M)this;
	}
	
	public java.lang.String getRonDomNum() {
		return getStr("ronDomNum");
	}

	public M setMac(java.lang.String mac) {
		set("mac", mac);
		return (M)this;
	}
	
	public java.lang.String getMac() {
		return getStr("mac");
	}

	public M setStartTime(java.lang.String startTime) {
		set("startTime", startTime);
		return (M)this;
	}
	
	public java.lang.String getStartTime() {
		return getStr("startTime");
	}

	public M setEndTime(java.lang.String endTime) {
		set("endTime", endTime);
		return (M)this;
	}
	
	public java.lang.String getEndTime() {
		return getStr("endTime");
	}

	public M setRsapublicKey(java.lang.String rsapublicKey) {
		set("rsapublicKey", rsapublicKey);
		return (M)this;
	}
	
	public java.lang.String getRsapublicKey() {
		return getStr("rsapublicKey");
	}

	public M setRespriviceKey(java.lang.String respriviceKey) {
		set("respriviceKey", respriviceKey);
		return (M)this;
	}
	
	public java.lang.String getRespriviceKey() {
		return getStr("respriviceKey");
	}

	public M setLicense(java.lang.String license) {
		set("license", license);
		return (M)this;
	}
	
	public java.lang.String getLicense() {
		return getStr("license");
	}

}
