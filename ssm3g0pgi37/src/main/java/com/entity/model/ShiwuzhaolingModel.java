package com.entity.model;

import com.entity.ShiwuzhaolingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
 

/**
 * 失物招领
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-05-08 09:36:13
 */
public class ShiwuzhaolingModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 物品数量
	 */
	
	private String wupinshuliang;
		
	/**
	 * 物品类型
	 */
	
	private String wupinleixing;
		
	/**
	 * 物品图片
	 */
	
	private String wupintupian;
		
	/**
	 * 状态
	 */
	
	private String zhuangtai;
		
	/**
	 * 拾得地址
	 */
	
	private String shidedizhi;
		
	/**
	 * 拾得时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shideshijian;
		
	/**
	 * 认领地址
	 */
	
	private String renlingdizhi;
		
	/**
	 * 物品描述
	 */
	
	private String wupinmiaoshu;
		
	/**
	 * 发布账号
	 */
	
	private String zhanghao;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
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
	 * 设置：状态
	 */
	 
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	
	/**
	 * 获取：状态
	 */
	public String getZhuangtai() {
		return zhuangtai;
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
	 * 设置：拾得时间
	 */
	 
	public void setShideshijian(Date shideshijian) {
		this.shideshijian = shideshijian;
	}
	
	/**
	 * 获取：拾得时间
	 */
	public Date getShideshijian() {
		return shideshijian;
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
	 * 设置：物品描述
	 */
	 
	public void setWupinmiaoshu(String wupinmiaoshu) {
		this.wupinmiaoshu = wupinmiaoshu;
	}
	
	/**
	 * 获取：物品描述
	 */
	public String getWupinmiaoshu() {
		return wupinmiaoshu;
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
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
