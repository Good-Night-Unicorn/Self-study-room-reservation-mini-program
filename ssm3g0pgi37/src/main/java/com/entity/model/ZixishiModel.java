package com.entity.model;

import com.entity.ZixishiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
 

/**
 * 自习室
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-05-08 09:36:12
 */
public class ZixishiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 名称
	 */
	
	private String mingcheng;
		
	/**
	 * 类型
	 */
	
	private String leixing;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 费用
	 */
	
	private Integer feiyong;
		
	/**
	 * 列数
	 */
	
	private Integer columns;
		
	/**
	 * 行数
	 */
	
	private Integer rows;
		
	/**
	 * 总数
	 */
	
	private Integer totals;
		
	/**
	 * 开放时间
	 */
	
	private String opentime;
		
	/**
	 * 自习室介绍
	 */
	
	private String zixishijieshao;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 评分
	 */
	
	private Double totalscore;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：名称
	 */
	 
	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}
	
	/**
	 * 获取：名称
	 */
	public String getMingcheng() {
		return mingcheng;
	}
				
	
	/**
	 * 设置：类型
	 */
	 
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	
	/**
	 * 获取：类型
	 */
	public String getLeixing() {
		return leixing;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：费用
	 */
	 
	public void setFeiyong(Integer feiyong) {
		this.feiyong = feiyong;
	}
	
	/**
	 * 获取：费用
	 */
	public Integer getFeiyong() {
		return feiyong;
	}
				
	
	/**
	 * 设置：列数
	 */
	 
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	/**
	 * 获取：列数
	 */
	public Integer getColumns() {
		return columns;
	}
				
	
	/**
	 * 设置：行数
	 */
	 
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * 获取：行数
	 */
	public Integer getRows() {
		return rows;
	}
				
	
	/**
	 * 设置：总数
	 */
	 
	public void setTotals(Integer totals) {
		this.totals = totals;
	}
	
	/**
	 * 获取：总数
	 */
	public Integer getTotals() {
		return totals;
	}
				
	
	/**
	 * 设置：开放时间
	 */
	 
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	
	/**
	 * 获取：开放时间
	 */
	public String getOpentime() {
		return opentime;
	}
				
	
	/**
	 * 设置：自习室介绍
	 */
	 
	public void setZixishijieshao(String zixishijieshao) {
		this.zixishijieshao = zixishijieshao;
	}
	
	/**
	 * 获取：自习室介绍
	 */
	public String getZixishijieshao() {
		return zixishijieshao;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：评分
	 */
	 
	public void setTotalscore(Double totalscore) {
		this.totalscore = totalscore;
	}
	
	/**
	 * 获取：评分
	 */
	public Double getTotalscore() {
		return totalscore;
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
