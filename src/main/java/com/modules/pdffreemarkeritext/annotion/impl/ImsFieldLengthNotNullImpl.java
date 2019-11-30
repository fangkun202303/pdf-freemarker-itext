package com.modules.pdffreemarkeritext.annotion.impl;

import com.alibaba.fastjson.JSONObject;
import com.modules.pdffreemarkeritext.annotion.ImsFieldLengthNotNull;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description: 校验非空,范围长度
 * @author: Fang Kun
 * @date: Created in  2019/11/22 17:26
 * @version: 1.0.0
 */
public class ImsFieldLengthNotNullImpl implements ConstraintValidator<ImsFieldLengthNotNull, String> {

    private String msg;

    private int min;

    private int max;

    private Integer code;

    public void initialize(ImsFieldLengthNotNull constraintAnnotation) {
        this.min=constraintAnnotation.min();
        this.max=constraintAnnotation.max();
        this.msg=constraintAnnotation.message();
        this.code=constraintAnnotation.code();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value!=null){
            value=value.trim();
        }
        context.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message", msg+"长度不在范围内，应该在"+min+"到"+max+"之间。");
        jsonObject.put("code", code);
        context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
        if(StringUtils.isEmpty(value)){
            return false;
        }
        if(value.length()<min || value.length()>max ){
            return false;
        }
        return true;
    }
}
