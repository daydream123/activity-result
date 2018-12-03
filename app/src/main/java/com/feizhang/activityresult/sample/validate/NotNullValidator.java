package com.feizhang.activityresult.sample.validate;

import android.text.TextUtils;

import com.feizhang.activityresult.sample.validate.annotations.NotNull;

class NotNullValidator<V> extends ConstraintValidator<NotNull> {

    NotNullValidator(NotNull annotation) {
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

        return !(value instanceof String) || !TextUtils.isEmpty((CharSequence) value);
    }
}
