package com.modules.pdffreemarkeritext.annotion.impl;

import com.alibaba.fastjson.JSONObject;
import com.modules.pdffreemarkeritext.annotion.ImsNotNull;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description: 校验不为空
 * @author: Fang Kun
 * @date: Created in  2019/11/25 15:19
 * @version: 1.0.0
 */
public class ImsNotNullImpl implements ConstraintValidator<ImsNotNull, Object> {
    /**
     * 错误码
     */
    private int code;
    /**
     * 字段中文名称
     */
    private String message;

    public void initialize(ImsNotNull constraintAnnotation) {
        this.code=constraintAnnotation.code();
        this.message=constraintAnnotation.message();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();//禁用默认的message的值
        //重新添加错误提示语句
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("message", message+"不能为空");
        jsonObject.put("code", code);
        context.buildConstraintViolationWithTemplate(jsonObject.toJSONString()).addConstraintViolation();
        if(ObjectUtils.isEmpty(value)){
            return false;
        }
        return true;
    }
}
