package com.modules.pdffreemarkeritext.annotion;


import com.modules.pdffreemarkeritext.annotion.impl.ImsDecimalPointImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description: 校验非整数的长度
 * @author: Fang Kun
 * @date: Created in  2019/11/28 19:14
 * @version: 1.0.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImsDecimalPointImpl.class)
public @interface ImsDecimalPoint {

    /**
     * 非整数长度下限
     * @return
     */
    int min() default 1;

    /**
     * 非整数长度上限
     * @return
     */
    int max() default Integer.MAX_VALUE;

    /**
     * 小数点保留默认两位
     * @return
     */
    int point() default 2;

    /**
     * 这个字段校验的异常编码
     * @return
     */
    int code();

    /**
     * 填写该字段的中文名称
     * @return
     */
    String message() default "";


    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
