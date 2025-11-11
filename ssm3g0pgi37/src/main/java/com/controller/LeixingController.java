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

import com.entity.LeixingEntity;
import com.entity.view.LeixingView;

import com.service.LeixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 09:36:12
 */
@RestController
@RequestMapping("/leixing")
public class LeixingController {
    @Autowired
    private LeixingService leixingService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LeixingEntity leixing,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<LeixingEntity> ew = new EntityWrapper<LeixingEntity>();


        //查询结果
		PageUtils page = leixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, leixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,LeixingEntity leixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<LeixingEntity> ew = new EntityWrapper<LeixingEntity>();

        //查询结果
		PageUtils page = leixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, leixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LeixingEntity leixing){
       	EntityWrapper<LeixingEntity> ew = new EntityWrapper<LeixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( leixing, "leixing"));
        return R.ok().put("data", leixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LeixingEntity leixing){
        EntityWrapper< LeixingEntity> ew = new EntityWrapper< LeixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( leixing, "leixing"));
		LeixingView leixingView =  leixingService.selectView(ew);
		return R.ok("查询类型成功").put("data", leixingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LeixingEntity leixing = leixingService.selectById(id);
        leixing = leixingService.selectView(new EntityWrapper<LeixingEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(leixing,deSens);
        return R.ok().put("data", leixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LeixingEntity leixing = leixingService.selectById(id);
        leixing = leixingService.selectView(new EntityWrapper<LeixingEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(leixing,deSens);
        return R.ok().put("data", leixing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LeixingEntity leixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(leixingService.selectCount(new EntityWrapper<LeixingEntity>().eq("leixing", leixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
        //ValidatorUtils.validateEntity(leixing);

        leixingService.insert(leixing);
        return R.ok().put("data",leixing.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LeixingEntity leixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(leixingService.selectCount(new EntityWrapper<LeixingEntity>().eq("leixing", leixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
    	//ValidatorUtils.validateEntity(leixing);

        leixingService.insert(leixing);
        return R.ok().put("data",leixing.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LeixingEntity leixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(leixing);
        //验证字段唯一性，否则返回错误信息
        if(leixingService.selectCount(new EntityWrapper<LeixingEntity>().ne("id", leixing.getId()).eq("leixing", leixing.getLeixing()))>0) {
            return R.error("类型已存在");
        }
        //全部更新
        leixingService.updateById(leixing);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        leixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
