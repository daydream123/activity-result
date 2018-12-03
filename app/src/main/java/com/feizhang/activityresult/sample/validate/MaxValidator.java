package com.feizhang.activityresult.sample.validate;

import com.feizhang.activityresult.sample.validate.annotations.Max;

class MaxValidator<V> extends ConstraintValidator<Max> {

    MaxValidator(Max annotation) {
        super(annotation);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), annotation.field(), annotation.max());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        int maxValue = annotation.max();

        if (value instanceof Number) {
            return ((Number) value).intValue() <= maxValue;
        }

        throw new IllegalArgumentException(value + " is not number type");
    }
}
