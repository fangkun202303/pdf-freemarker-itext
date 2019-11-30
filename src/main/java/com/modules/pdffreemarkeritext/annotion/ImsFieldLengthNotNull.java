package com.modules.pdffreemarkeritext.annotion;

import com.modules.pdffreemarkeritext.annotion.impl.ImsFieldLengthNotNullImpl;

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
 * @description: 字段长度的校验注解, 该字段是不能为空的
 *
 *      // 这个成员变量是在非控制层实现校验使用的
 *     private static Validator validator = Validation.byProvider(HibernateValidator.class).
 *             //failFast(true)遇到一个错误信息便返回；failFast(false)校验完所有参数后才返回
 *             configure().failFast(true).buildValidatorFactory().getValidator();
 *
 *             // 会返回这个集合,剩下的自定义组装
 *             Set<ConstraintViolation<MyAnnotionTest>> validate = validator.validate(myAnnotionTest);
 *
 * @author: Fang Kun
 * @date: Created in  2019/11/22 17:23
 * @version: 1.0.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImsFieldLengthNotNullImpl.class)
public @interface ImsFieldLengthNotNull {

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
