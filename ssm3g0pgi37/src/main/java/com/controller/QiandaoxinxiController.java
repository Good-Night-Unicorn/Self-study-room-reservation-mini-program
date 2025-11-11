package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.QiandaoxinxiEntity;
import com.entity.view.QiandaoxinxiView;

import com.service.QiandaoxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 签到信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 09:36:13
 */
@RestController
@RequestMapping("/qiandaoxinxi")
public class QiandaoxinxiController {
    @Autowired
    private QiandaoxinxiService qiandaoxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiandaoxinxiEntity qiandaoxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			qiandaoxinxi.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<QiandaoxinxiEntity> ew = new EntityWrapper<QiandaoxinxiEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);


        //查询结果
		PageUtils page = qiandaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiandaoxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QiandaoxinxiEntity qiandaoxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QiandaoxinxiEntity> ew = new EntityWrapper<QiandaoxinxiEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);

        //查询结果
		PageUtils page = qiandaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiandaoxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiandaoxinxiEntity qiandaoxinxi){
       	EntityWrapper<QiandaoxinxiEntity> ew = new EntityWrapper<QiandaoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiandaoxinxi, "qiandaoxinxi"));
        return R.ok().put("data", qiandaoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiandaoxinxiEntity qiandaoxinxi){
        EntityWrapper< QiandaoxinxiEntity> ew = new EntityWrapper< QiandaoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiandaoxinxi, "qiandaoxinxi"));
		QiandaoxinxiView qiandaoxinxiView =  qiandaoxinxiService.selectView(ew);
		return R.ok("查询签到信息成功").put("data", qiandaoxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiandaoxinxiEntity qiandaoxinxi = qiandaoxinxiService.selectById(id);
        qiandaoxinxi = qiandaoxinxiService.selectView(new EntityWrapper<QiandaoxinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiandaoxinxi,deSens);
        return R.ok().put("data", qiandaoxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiandaoxinxiEntity qiandaoxinxi = qiandaoxinxiService.selectById(id);
        qiandaoxinxi = qiandaoxinxiService.selectView(new EntityWrapper<QiandaoxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiandaoxinxi,deSens);
        return R.ok().put("data", qiandaoxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiandaoxinxiEntity qiandaoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiandaoxinxi);

        qiandaoxinxiService.insert(qiandaoxinxi);
        return R.ok().put("data",qiandaoxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiandaoxinxiEntity qiandaoxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qiandaoxinxi);

        qiandaoxinxiService.insert(qiandaoxinxi);
        return R.ok().put("data",qiandaoxinxi.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiandaoxinxiEntity qiandaoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiandaoxinxi);
        //全部更新
        qiandaoxinxiService.updateById(qiandaoxinxi);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiandaoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
