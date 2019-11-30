package com.modules.pdffreemarkeritext.annotion;

import com.modules.pdffreemarkeritext.annotion.impl.ImsFieldLengthImpl;

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
 * @description: 该注解校验字段的长度, 可以为空, 针对非必填字段
 * @author: Fang Kun
 * @date: Created in  2019/11/25 14:23
 * @version: 1.0.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImsFieldLengthImpl.class)
public @interface ImsFieldLength {
    String value();

    /**
     * 这个字段校验的异常编码
     * @return
     */
    int code();

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    /**
     * 填写该字段的中文名称
     * @return
     */
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
