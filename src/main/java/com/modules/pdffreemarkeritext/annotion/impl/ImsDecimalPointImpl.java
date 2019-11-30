package com.modules.pdffreemarkeritext.annotion.impl;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.modules.pdffreemarkeritext.annotion.ImsDecimalPoint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description:
 * @author: Fang Kun
 * @date: Created in  2019/11/28 19:15
 * @version: 1.0.0
 */
public class ImsDecimalPointImpl implements ConstraintValidator<ImsDecimalPoint, Object> {

    private static final String POINT_OF_NUMBER=".";
    private static final String ZERO="0";

    /**
     * 非整数长度下限
     */
    private int min;
    /***
     * 非整数长度上限
     */
    private int max;
    /**
     * 小数点保留默认两位
     */
    private int point;
    /**
     * 填写该字段的中文名称
     */
    private String message;
    /**
     * 这个字段校验的异常编码
     */
    private int code;

    public void initialize(ImsDecimalPoint constraintAnnotation) {
        this.min=constraintAnnotation.min();
        this.max=constraintAnnotation.max();
        this.point=constraintAnnotation.point();
        this.message=constraintAnnotation.message();
        this.code=constraintAnnotation.code();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }
        context.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message", message+"不是数字类型的数值。");
        jsonObject.put("code", code);
        Double d=0.0;
        if(value instanceof Double){
            d=(Double) value;
        }else if(value instanceof BigDecimal){
            BigDecimal bigDecimal=(BigDecimal)value;
            d=bigDecimal.doubleValue();
        }else if(value instanceof String){
            boolean number = NumberUtil.isNumber((String) value);
            if(number){
                d=Double.parseDouble((String)value);
            }
            context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
            return false;
        }else if(value instanceof Boolean){
            context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
            return false;
        }else if(value instanceof Character){
            context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
            return false;
        }else {
            d=Double.parseDouble(String.valueOf(value));
        }

        // 判断小数点前面的数据长度
        String doubleStr=d.toString();
        jsonObject.put("message", message + "数值长度不对，应该是长度为" + max + "小数保留" + point + "位的非整数。");
        jsonObject.put("code", code);
        // 判断这个数值有没有小数点
        String numPrefix=null;
        if(doubleStr.indexOf(POINT_OF_NUMBER)>-1){
            // 小数点前面字符串
            numPrefix=doubleStr.substring(0,doubleStr.indexOf(POINT_OF_NUMBER));
            // 小数点后面的字符串
            String numSuffix=doubleStr.substring(doubleStr.indexOf(POINT_OF_NUMBER)+1);
            // 排除0.2这种情况的数值
            if(ZERO.equals(numPrefix)){
                if(numSuffix.length()>point) {
                    context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
                    return false;
                }
            }else{
                if(numPrefix.length()<min || numPrefix.length()>max || numSuffix.length()>point){
                    context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
                    return false;
                }
            }
        }else{
            // 没有小数点
            numPrefix=doubleStr;
            if(numPrefix.length()<min || numPrefix.length()>max){
                context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
