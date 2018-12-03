package com.feizhang.activityresult.sample.validate;

import com.feizhang.activityresult.sample.validate.annotations.Len;

public class LenValidator extends ConstraintValidator<Len> {

    LenValidator(Len annotation) {
        super(annotation);
    }

    @Override
    public boolean isValid(Object value) {
        if (value == null){
            return false;
        }

        int len = annotation.len();
        if (value instanceof String) {
            return ((String) value).length() == len;
        }

        throw new IllegalArgumentException(value + " is not String type");
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), annotation.field(), annotation.len());
        } else {
            return message;
        }
    }
}
