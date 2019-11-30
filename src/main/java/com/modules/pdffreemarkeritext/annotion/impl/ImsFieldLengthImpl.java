package com.modules.pdffreemarkeritext.annotion.impl;

import cn.hsa.ims.api.annotion.ImsFieldLength;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description: 校验非空字段的长度
 * @author: Fang Kun
 * @date: Created in  2019/11/25 14:25
 * @version: 1.0.0
 */
public class ImsFieldLengthImpl implements ConstraintValidator<ImsFieldLength, String> {

    private String msg;

    private int min;

    private int max;

    private Integer code;

    public void initialize(ImsFieldLength constraintAnnotation) {
        this.min=constraintAnnotation.min();
        this.max=constraintAnnotation.max();
        this.msg=constraintAnnotation.message();
        this.code=constraintAnnotation.code();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message", msg+"长度不在范围内，应该在"+min+"到"+max+"之间。");
        jsonObject.put("code", code);
        context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
        if(StringUtils.isEmpty(value)){
            return true;
        }
        if(value.length()<min || value.length()>max ){
            return false;
        }
        return true;
    }
}
