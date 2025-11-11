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

import com.entity.RenlingxinxiEntity;
import com.entity.view.RenlingxinxiView;

import com.service.RenlingxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 认领信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 09:36:13
 */
@RestController
@RequestMapping("/renlingxinxi")
public class RenlingxinxiController {
    @Autowired
    private RenlingxinxiService renlingxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RenlingxinxiEntity renlingxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dengjishijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dengjishijianend,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<RenlingxinxiEntity> ew = new EntityWrapper<RenlingxinxiEntity>();
        if(dengjishijianstart!=null) ew.ge("dengjishijian", dengjishijianstart);
        if(dengjishijianend!=null) ew.le("dengjishijian", dengjishijianend);

		String tableName = request.getSession().getAttribute("tableName").toString();
        ew.andNew();
		if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
            ew.or();
            ew.eq("renlingzhanghao", (String)request.getSession().getAttribute("username"));
		}
        ew.andNew("1=1");

        //查询结果
		PageUtils page = renlingxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renlingxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,RenlingxinxiEntity renlingxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dengjishijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dengjishijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RenlingxinxiEntity> ew = new EntityWrapper<RenlingxinxiEntity>();
        if(dengjishijianstart!=null) ew.ge("dengjishijian", dengjishijianstart);
        if(dengjishijianend!=null) ew.le("dengjishijian", dengjishijianend);

        //查询结果
		PageUtils page = renlingxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renlingxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RenlingxinxiEntity renlingxinxi){
       	EntityWrapper<RenlingxinxiEntity> ew = new EntityWrapper<RenlingxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( renlingxinxi, "renlingxinxi"));
        return R.ok().put("data", renlingxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RenlingxinxiEntity renlingxinxi){
        EntityWrapper< RenlingxinxiEntity> ew = new EntityWrapper< RenlingxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( renlingxinxi, "renlingxinxi"));
		RenlingxinxiView renlingxinxiView =  renlingxinxiService.selectView(ew);
		return R.ok("查询认领信息成功").put("data", renlingxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RenlingxinxiEntity renlingxinxi = renlingxinxiService.selectById(id);
        renlingxinxi = renlingxinxiService.selectView(new EntityWrapper<RenlingxinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renlingxinxi,deSens);
        return R.ok().put("data", renlingxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RenlingxinxiEntity renlingxinxi = renlingxinxiService.selectById(id);
        renlingxinxi = renlingxinxiService.selectView(new EntityWrapper<RenlingxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renlingxinxi,deSens);
        return R.ok().put("data", renlingxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenlingxinxiEntity renlingxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renlingxinxi);

        renlingxinxiService.insert(renlingxinxi);
        return R.ok().put("data",renlingxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RenlingxinxiEntity renlingxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(renlingxinxi);

        renlingxinxiService.insert(renlingxinxi);
        return R.ok().put("data",renlingxinxi.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RenlingxinxiEntity renlingxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renlingxinxi);
        //全部更新
        renlingxinxiService.updateById(renlingxinxi);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        renlingxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
