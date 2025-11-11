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

import com.entity.ZuoweiyuyueEntity;
import com.entity.view.ZuoweiyuyueView;

import com.service.ZuoweiyuyueService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 座位预约
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 09:36:12
 */
@RestController
@RequestMapping("/zuoweiyuyue")
public class ZuoweiyuyueController {
    @Autowired
    private ZuoweiyuyueService zuoweiyuyueService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoweiyuyueEntity zuoweiyuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			zuoweiyuyue.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);


        //查询结果
		PageUtils page = zuoweiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoweiyuyue), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZuoweiyuyueEntity zuoweiyuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);

        //查询结果
		PageUtils page = zuoweiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoweiyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoweiyuyueEntity zuoweiyuyue){
       	EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoweiyuyue, "zuoweiyuyue"));
        return R.ok().put("data", zuoweiyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoweiyuyueEntity zuoweiyuyue){
        EntityWrapper< ZuoweiyuyueEntity> ew = new EntityWrapper< ZuoweiyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoweiyuyue, "zuoweiyuyue"));
		ZuoweiyuyueView zuoweiyuyueView =  zuoweiyuyueService.selectView(ew);
		return R.ok("查询座位预约成功").put("data", zuoweiyuyueView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(id);
        zuoweiyuyue = zuoweiyuyueService.selectView(new EntityWrapper<ZuoweiyuyueEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoweiyuyue,deSens);
        return R.ok().put("data", zuoweiyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(id);
        zuoweiyuyue = zuoweiyuyueService.selectView(new EntityWrapper<ZuoweiyuyueEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoweiyuyue,deSens);
        return R.ok().put("data", zuoweiyuyue);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoweiyuyue);

        zuoweiyuyueService.insert(zuoweiyuyue);
        return R.ok().put("data",zuoweiyuyue.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zuoweiyuyue);

        zuoweiyuyueService.insert(zuoweiyuyue);
        return R.ok().put("data",zuoweiyuyue.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoweiyuyue);
        //全部更新
        zuoweiyuyueService.updateById(zuoweiyuyue);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZuoweiyuyueEntity> list = new ArrayList<ZuoweiyuyueEntity>();
        for(Long id : ids) {
            ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(id);
            zuoweiyuyue.setSfsh(sfsh);
            zuoweiyuyue.setShhf(shhf);
            list.add(zuoweiyuyue);
        }
        zuoweiyuyueService.updateBatchById(list);
        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zuoweiyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }








    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        //获取结果
        List<Map<String, Object>> result = zuoweiyuyueService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = zuoweiyuyueService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = zuoweiyuyueService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = zuoweiyuyueService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_zuoweiyuyue_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = zuoweiyuyueService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            zuoweiyuyue.setZhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        int count = zuoweiyuyueService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoweiyuyue), params), params));
        return R.ok().put("data", count);
    }

}
