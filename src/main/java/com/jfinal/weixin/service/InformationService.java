package com.jfinal.weixin.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.weixin.model.TxBanner;
import com.jfinal.weixin.model.TxNews;
import com.jfinal.weixin.model.TxNotice;
import com.jfinal.weixin.model.TxOption;

public class InformationService {
	
	public static final	InformationService info = new InformationService();
	public static final TxBanner bdao = new TxBanner();
	public static final TxNews ndao = new TxNews();
	public static final TxNotice nodao = new TxNotice();
	public static final TxOption odao = new TxOption();
	
	public List<TxBanner> getBanner(){
		List<TxBanner> list = bdao.findAll();
		return list;
	}
	
	
	public List<TxNews> getNews(){
		List<TxNews> list = ndao.findAll();
		return list;
	}
	
	public List<TxNotice> getNotice(){
		List<TxNotice> list = nodao.findAll();
		return list;
	}
	
	public List<TxOption> getOption(){
		List<TxOption> list = odao.findAll();
		return list;
	}


}
