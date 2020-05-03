package com.jfinal.weixin.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseTbFailreceive<M extends BaseTbFailreceive<M>> extends Model<M> implements IBean {

	public M setFaceIp(java.lang.String faceIp) {
		set("faceIp", faceIp);
		return (M)this;
	}
	
	public java.lang.String getFaceIp() {
		return getStr("faceIp");
	}

	public M setUserName(java.lang.String userName) {
		set("userName", userName);
		return (M)this;
	}
	
	public java.lang.String getUserName() {
		return getStr("userName");
	}

	public M setIdCard(java.lang.String idCard) {
		set("idCard", idCard);
		return (M)this;
	}
	
	public java.lang.String getIdCard() {
		return getStr("idCard");
	}

	public M setUserType(java.lang.String userType) {
		set("userType", userType);
		return (M)this;
	}
	
	public java.lang.String getUserType() {
		return getStr("userType");
	}

	public M setReceiveTime(java.lang.String receiveTime) {
		set("receiveTime", receiveTime);
		return (M)this;
	}
	
	public java.lang.String getReceiveTime() {
		return getStr("receiveTime");
	}

	public M setReceiveFlag(java.lang.String receiveFlag) {
		set("receiveFlag", receiveFlag);
		return (M)this;
	}
	
	public java.lang.String getReceiveFlag() {
		return getStr("receiveFlag");
	}

	public M setVisitorUUID(java.lang.String visitorUUID) {
		set("visitorUUID", visitorUUID);
		return (M)this;
	}
	
	public java.lang.String getVisitorUUID() {
		return getStr("visitorUUID");
	}

	public M setOpera(java.lang.String opera) {
		set("opera", opera);
		return (M)this;
	}
	
	public java.lang.String getOpera() {
		return getStr("opera");
	}

	public M setDownNum(java.lang.Integer downNum) {
		set("downNum", downNum);
		return (M)this;
	}
	
	public java.lang.Integer getDownNum() {
		return getInt("downNum");
	}

}
