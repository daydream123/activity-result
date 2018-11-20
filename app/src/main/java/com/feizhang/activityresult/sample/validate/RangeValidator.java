package com.feizhang.activityresult.sample.validate;

import com.feizhang.activityresult.sample.validate.annotations.Range;

class RangeValidator<V> extends ConstraintValidator<Range> {

    RangeValidator(Range annotation) {
        super(annotation);
    }

    @Override
    public String getMessage() {
        String message = annotation.message();
        if (message.contains("%")) {
            return String.format(annotation.message(), annotation.field(),
                    annotation.min(), annotation.max());
        } else {
            return message;
        }
    }

    @Override
    public boolean isValid(Object value) {
        int minValue = annotation.min();
        int maxValue = annotation.max();

        if (value instanceof Number) {
            int number = ((Number) value).intValue();
            return number >= minValue && number <= maxValue;
        }

        throw new IllegalArgumentException(value + " is not number type");
    }
}
