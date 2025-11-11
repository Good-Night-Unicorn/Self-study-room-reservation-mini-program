package com.entity.vo;

import com.entity.RenlingxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 认领信息
 * @author 
 * @email 
 * @date 2025-05-08 09:36:13
 */
public class RenlingxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 物品名称
	 */
	
	private String wupinmingcheng;
		
	/**
	 * 物品图片
	 */
	
	private String wupintupian;
		
	/**
	 * 物品数量
	 */
	
	private String wupinshuliang;
		
	/**
	 * 物品类型
	 */
	
	private String wupinleixing;
		
	/**
	 * 拾得地址
	 */
	
	private String shidedizhi;
		
	/**
	 * 认领地址
	 */
	
	private String renlingdizhi;
		
	/**
	 * 发布账号
	 */
	
	private String zhanghao;
		
	/**
	 * 认领账号
	 */
	
	private String renlingzhanghao;
		
	/**
	 * 认领人
	 */
	
	private String renlingren;
		
	/**
	 * 联系电话
	 */
	
	private String lianxidianhua;
		
	/**
	 * 登记时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date dengjishijian;
				
	
	/**
	 * 设置：物品名称
	 */
	 
	public void setWupinmingcheng(String wupinmingcheng) {
		this.wupinmingcheng = wupinmingcheng;
	}
	
	/**
	 * 获取：物品名称
	 */
	public String getWupinmingcheng() {
		return wupinmingcheng;
	}
				
	
	/**
	 * 设置：物品图片
	 */
	 
	public void setWupintupian(String wupintupian) {
		this.wupintupian = wupintupian;
	}
	
	/**
	 * 获取：物品图片
	 */
	public String getWupintupian() {
		return wupintupian;
	}
				
	
	/**
	 * 设置：物品数量
	 */
	 
	public void setWupinshuliang(String wupinshuliang) {
		this.wupinshuliang = wupinshuliang;
	}
	
	/**
	 * 获取：物品数量
	 */
	public String getWupinshuliang() {
		return wupinshuliang;
	}
				
	
	/**
	 * 设置：物品类型
	 */
	 
	public void setWupinleixing(String wupinleixing) {
		this.wupinleixing = wupinleixing;
	}
	
	/**
	 * 获取：物品类型
	 */
	public String getWupinleixing() {
		return wupinleixing;
	}
				
	
	/**
	 * 设置：拾得地址
	 */
	 
	public void setShidedizhi(String shidedizhi) {
		this.shidedizhi = shidedizhi;
	}
	
	/**
	 * 获取：拾得地址
	 */
	public String getShidedizhi() {
		return shidedizhi;
	}
				
	
	/**
	 * 设置：认领地址
	 */
	 
	public void setRenlingdizhi(String renlingdizhi) {
		this.renlingdizhi = renlingdizhi;
	}
	
	/**
	 * 获取：认领地址
	 */
	public String getRenlingdizhi() {
		return renlingdizhi;
	}
				
	
	/**
	 * 设置：发布账号
	 */
	 
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
	/**
	 * 获取：发布账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
				
	
	/**
	 * 设置：认领账号
	 */
	 
	public void setRenlingzhanghao(String renlingzhanghao) {
		this.renlingzhanghao = renlingzhanghao;
	}
	
	/**
	 * 获取：认领账号
	 */
	public String getRenlingzhanghao() {
		return renlingzhanghao;
	}
				
	
	/**
	 * 设置：认领人
	 */
	 
	public void setRenlingren(String renlingren) {
		this.renlingren = renlingren;
	}
	
	/**
	 * 获取：认领人
	 */
	public String getRenlingren() {
		return renlingren;
	}
				
	
	/**
	 * 设置：联系电话
	 */
	 
	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}
	
	/**
	 * 获取：联系电话
	 */
	public String getLianxidianhua() {
		return lianxidianhua;
	}
				
	
	/**
	 * 设置：登记时间
	 */
	 
	public void setDengjishijian(Date dengjishijian) {
		this.dengjishijian = dengjishijian;
	}
	
	/**
	 * 获取：登记时间
	 */
	public Date getDengjishijian() {
		return dengjishijian;
	}
			
}
