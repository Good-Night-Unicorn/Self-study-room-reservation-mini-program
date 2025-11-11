package com.entity.view;

import com.entity.ZuoweiyuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 座位预约
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-05-08 09:36:12
 */
@TableName("zuoweiyuyue")
public class ZuoweiyuyueView  extends ZuoweiyuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZuoweiyuyueView(){
	}
 
 	public ZuoweiyuyueView(ZuoweiyuyueEntity zuoweiyuyueEntity){
 	try {
			BeanUtils.copyProperties(this, zuoweiyuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
