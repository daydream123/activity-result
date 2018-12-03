package com.feizhang.activityresult.sample.validate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    String field();

    int min();

    int max();

    String message() default "%s不在范围%d和%d之间";
}
