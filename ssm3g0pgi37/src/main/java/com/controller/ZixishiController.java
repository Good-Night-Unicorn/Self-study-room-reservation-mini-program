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

import com.entity.ZixishiEntity;
import com.entity.view.ZixishiView;

import com.service.ZixishiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 自习室
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 09:36:12
 */
@RestController
@RequestMapping("/zixishi")
public class ZixishiController {
    @Autowired
    private ZixishiService zixishiService;


    @Autowired
    private StoreupService storeupService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZixishiEntity zixishi,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<ZixishiEntity> ew = new EntityWrapper<ZixishiEntity>();


        //查询结果
		PageUtils page = zixishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixishi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZixishiEntity zixishi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZixishiEntity> ew = new EntityWrapper<ZixishiEntity>();

        //查询结果
		PageUtils page = zixishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixishi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZixishiEntity zixishi){
       	EntityWrapper<ZixishiEntity> ew = new EntityWrapper<ZixishiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zixishi, "zixishi"));
        return R.ok().put("data", zixishiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZixishiEntity zixishi){
        EntityWrapper< ZixishiEntity> ew = new EntityWrapper< ZixishiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zixishi, "zixishi"));
		ZixishiView zixishiView =  zixishiService.selectView(ew);
		return R.ok("查询自习室成功").put("data", zixishiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZixishiEntity zixishi = zixishiService.selectById(id);
		zixishi.setClicktime(new Date());
		zixishiService.updateById(zixishi);
        zixishi = zixishiService.selectView(new EntityWrapper<ZixishiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zixishi,deSens);
        return R.ok().put("data", zixishi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZixishiEntity zixishi = zixishiService.selectById(id);
		zixishi.setClicktime(new Date());
		zixishiService.updateById(zixishi);
        zixishi = zixishiService.selectView(new EntityWrapper<ZixishiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zixishi,deSens);
        return R.ok().put("data", zixishi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZixishiEntity zixishi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zixishiService.selectCount(new EntityWrapper<ZixishiEntity>().eq("refno", zixishi.getRefno()))>0) {
            return R.error("编号已存在");
        }
        //ValidatorUtils.validateEntity(zixishi);

        zixishiService.insert(zixishi);
        return R.ok().put("data",zixishi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZixishiEntity zixishi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zixishiService.selectCount(new EntityWrapper<ZixishiEntity>().eq("refno", zixishi.getRefno()))>0) {
            return R.error("编号已存在");
        }
    	//ValidatorUtils.validateEntity(zixishi);

        zixishiService.insert(zixishi);
        return R.ok().put("data",zixishi.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZixishiEntity zixishi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zixishi);
        //验证字段唯一性，否则返回错误信息
        if(zixishiService.selectCount(new EntityWrapper<ZixishiEntity>().ne("id", zixishi.getId()).eq("refno", zixishi.getRefno()))>0) {
            return R.error("编号已存在");
        }
        //全部更新
        zixishiService.updateById(zixishi);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zixishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZixishiEntity zixishi, HttpServletRequest request,String pre){
        EntityWrapper<ZixishiEntity> ew = new EntityWrapper<ZixishiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");

        params.put("order", "desc");
		PageUtils page = zixishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixishi), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（按收藏推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,ZixishiEntity zixishi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "leixing";
        // 查询收藏集合
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "zixishi").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<ZixishiEntity> zixishiList = new ArrayList<ZixishiEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            List<String> typeList = new ArrayList<String>();
            for(StoreupEntity s : storeups) {
                if(typeList.contains(s.getInteltype())) continue;
                typeList.add(s.getInteltype());
                zixishiList.addAll(zixishiService.selectList(new EntityWrapper<ZixishiEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<ZixishiEntity> ew = new EntityWrapper<ZixishiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        // 根据协同结果查询结果并返回
        PageUtils page = zixishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixishi), params), params));
        List<ZixishiEntity> pageList = (List<ZixishiEntity>)page.getList();
        if(zixishiList.size()<limit) {
            int toAddNum = (limit-zixishiList.size())<=pageList.size()?(limit-zixishiList.size()):pageList.size();
            for(ZixishiEntity o1 : pageList) {
                boolean addFlag = true;
                for(ZixishiEntity o2 : zixishiList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    zixishiList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(zixishiList.size()>limit) {
            zixishiList = zixishiList.subList(0, limit);
        }
        page.setList(zixishiList);
        return R.ok().put("data", page);
    }







}
