package com.entity.vo;

import com.entity.ZuoweiyuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 座位预约
 * @author 
 * @email 
 * @date 2025-05-08 09:36:12
 */
public class ZuoweiyuyueVO  implements Serializable {
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
	 * 座位号
	 */
	
	private String seatnum;
		
	/**
	 * 预约日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date reservationdate;
		
	/**
	 * 时间段
	 */
	
	private String timeslot;
		
	/**
	 * 账号
	 */
	
	private String zhanghao;
		
	/**
	 * 手机
	 */
	
	private String shouji;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
		
	/**
	 * 座位状态
	 */
	
	private String reservationstate;
		
	/**
	 * 是否签到
	 */
	
	private String shifouqiandao;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
		
	/**
	 * 是否支付
	 */
	
	private String ispay;
				
	
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
	 * 设置：座位号
	 */
	 
	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}
	
	/**
	 * 获取：座位号
	 */
	public String getSeatnum() {
		return seatnum;
	}
				
	
	/**
	 * 设置：预约日期
	 */
	 
	public void setReservationdate(Date reservationdate) {
		this.reservationdate = reservationdate;
	}
	
	/**
	 * 获取：预约日期
	 */
	public Date getReservationdate() {
		return reservationdate;
	}
				
	
	/**
	 * 设置：时间段
	 */
	 
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	
	/**
	 * 获取：时间段
	 */
	public String getTimeslot() {
		return timeslot;
	}
				
	
	/**
	 * 设置：账号
	 */
	 
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
	/**
	 * 获取：账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
				
	
	/**
	 * 设置：手机
	 */
	 
	public void setShouji(String shouji) {
		this.shouji = shouji;
	}
	
	/**
	 * 获取：手机
	 */
	public String getShouji() {
		return shouji;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：座位状态
	 */
	 
	public void setReservationstate(String reservationstate) {
		this.reservationstate = reservationstate;
	}
	
	/**
	 * 获取：座位状态
	 */
	public String getReservationstate() {
		return reservationstate;
	}
				
	
	/**
	 * 设置：是否签到
	 */
	 
	public void setShifouqiandao(String shifouqiandao) {
		this.shifouqiandao = shifouqiandao;
	}
	
	/**
	 * 获取：是否签到
	 */
	public String getShifouqiandao() {
		return shifouqiandao;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：是否支付
	 */
	 
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}
			
}
