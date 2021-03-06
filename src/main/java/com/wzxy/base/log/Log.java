package com.wzxy.base.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/9/25/025.
 * 操作日志
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    String operateName() default "";// 操作名称
    String operateFun() default ""; // 操作方法
    String operateDescribe() default ""; // 操作描述
}
