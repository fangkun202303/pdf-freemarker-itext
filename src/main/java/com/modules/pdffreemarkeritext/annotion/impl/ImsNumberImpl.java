package com.modules.pdffreemarkeritext.annotion.impl;

import com.alibaba.fastjson.JSONObject;
import com.modules.pdffreemarkeritext.annotion.ImsNumber;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description: 校验数值大于0,且长度在范围内;只判断整数
 * @author: Fang Kun
 * @date: Created in  2019/11/25 16:48
 * @version: 1.0.0
 */
public class ImsNumberImpl implements ConstraintValidator<ImsNumber, Object> {
    /**
     * 这个字段校验的异常编码
     * @return
     */
    int code;

    /**
     * 填写该字段的中文名称
     * @return
     */
    String message;
    /**
     * 数值的长度上限下限
     */
    private int min;
    private int max;
    public void initialize(ImsNumber constraintAnnotation) {
        this.code=constraintAnnotation.code();
        this.message=constraintAnnotation.message();
        this.min=constraintAnnotation.min();
        this.max=constraintAnnotation.max();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句
        JSONObject jsonObject=new JSONObject();
        if(ObjectUtils.isEmpty(value)){
            jsonObject.put("message", message+"不能为空。");
            jsonObject.put("code", code);
            context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
            return false;
        }
        // 统一按int类型处理
        int num=0;
        if(value instanceof BigDecimal){
            BigDecimal bigDecimal = (BigDecimal) value;
            num= bigDecimal.intValue();
        }
        if(value instanceof Integer){
            num=(Integer)value;
        }
        if(num>0){
            // 判断字段长度
            String intValueStr=num+"";
            if(intValueStr.length()<min || intValueStr.length()>max){
                jsonObject.put("message", message+"数值太大，精度应该在"+min+"到"+max+"之间。");
                jsonObject.put("code", code);
                context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
                return false;
            }
        }else{
            jsonObject.put("message", message+"必须为正数。");
            jsonObject.put("code", code);
            context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
