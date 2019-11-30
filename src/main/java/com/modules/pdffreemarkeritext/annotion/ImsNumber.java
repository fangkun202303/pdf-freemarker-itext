package com.modules.pdffreemarkeritext.annotion;

import com.modules.pdffreemarkeritext.annotion.impl.ImsNumberImpl;

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
 * @description: 校验数值大于0
 * @author: Fang Kun
 * @date: Created in  2019/11/25 16:47
 * @version: 1.0.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImsNumberImpl.class)
public @interface ImsNumber {
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

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
