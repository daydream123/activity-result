package com.feizhang.activityresult.sample.validate;

import java.lang.annotation.Annotation;

public abstract class ConstraintValidator<A extends Annotation> {
    protected A annotation;

    public abstract boolean isValid(Object value);

    ConstraintValidator(A annotation){
        this.annotation = annotation;
    }

    public abstract String getMessage();
}
