package com.feizhang.activityresult.sample.validate;

import com.feizhang.activityresult.sample.validate.annotations.NotEmpty;

import java.util.Collection;

class NotEmptyValidator<V> extends ConstraintValidator<NotEmpty> {

    NotEmptyValidator(NotEmpty annotation) {
        super(annotation);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")){
            return String.format(annotation.message(), annotation.field());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null) {
            return false;
        }

        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        }

        throw new IllegalArgumentException(value + " is not Collection type");
    }
}
