package com.feizhang.activityresult.sample.validate;

import com.feizhang.activityresult.sample.validate.annotations.Min;

class MinValidator extends ConstraintValidator<Min> {

    MinValidator(Min annotation) {
        super(annotation);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), annotation.field(), annotation.min());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        int minValue = annotation.min();

        if (value instanceof Number) {
            return ((Number) value).intValue() >= minValue;
        }

        throw new IllegalArgumentException(value + " is not number type");
    }
}
