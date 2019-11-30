package com.modules.pdffreemarkeritext.annotion;

import com.modules.pdffreemarkeritext.annotion.impl.ImsNotNullImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Copyright (C) 2019 Kingstar Winning, Inc. All rights reserved.
 *
 * @description:
 * @author: Fang Kun
 * @date: Created in  2019/11/25 15:13
 * @version: 1.0.0
 */
@Target({ElementType.FIELD, METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImsNotNullImpl.class)
public @interface ImsNotNull {

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
