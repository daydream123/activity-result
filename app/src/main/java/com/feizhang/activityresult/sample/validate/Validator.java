package com.feizhang.activityresult.sample.validate;

import android.content.Context;
import android.widget.Toast;

import com.feizhang.activityresult.sample.validate.annotations.Len;
import com.feizhang.activityresult.sample.validate.annotations.Max;
import com.feizhang.activityresult.sample.validate.annotations.Min;
import com.feizhang.activityresult.sample.validate.annotations.NotEmpty;
import com.feizhang.activityresult.sample.validate.annotations.NotNull;
import com.feizhang.activityresult.sample.validate.annotations.Range;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * A validator to validate fields of Object with Annotations(@Max, @Min, @NotEmpty, @NotNull, @Rang, etc.),
 * if someone field's value did't pass the validations, it'll toast a message specified by its annotation.
 */
public class Validator {

    /**
     * Validate fields under object.
     */
    public static boolean validate(Context context, Object object) {
        if (object == null) {
            return false;
        }

        // ignore String and Number
        if (object instanceof String || object instanceof Number) {
            throw new IllegalArgumentException("String or Number instance is not supported");
        }

        // check fields in java.util.List
        if (object instanceof List) {
            List list = (List) object;
            for (Object item : list) {
                boolean valid = validate(context, item);
                if (!valid) {
                    return false;
                }
            }

            return true;
        }

        // check fields in array
        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            for (Object item : array) {
                boolean valid = validate(context, item);
                if (!valid) {
                    return false;
                }
            }

            return true;
        }

        // check fields of object
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // ignore static
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if (isStatic) {
                continue;
            }

            try {
                field.setAccessible(true);
                Object value = field.get(object);
                Annotation[] annotations = field.getAnnotations();
                if (annotations.length > 0) {
                    for (Annotation annotation : annotations) {
                        // validate object its self
                        boolean valid = validateWithAnnotation(context, annotation, value);
                        if (!valid) {
                            return false;
                        }
                    }
                }

                // validate fields of object, but make sure it's not String or Number
                if (!(value instanceof String) && !(value instanceof Number)) {
                    boolean valid = validate(context, value);
                    if (!valid) {
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * Validate value with annotations that we support.
     *
     * @param context    Android context
     * @param annotation annotation to validate with
     * @param object     object to validate
     * @return return true when the object passes validation
     * or the annotation we don't support
     */
    private static <A extends Annotation> boolean validateWithAnnotation(Context context, A annotation, Object object) {
        ConstraintValidator validator = null;
        if (annotation instanceof NotNull) {
            validator = new NotNullValidator<>((NotNull) annotation);
        } else if (annotation instanceof NotEmpty) {
            validator = new NotEmptyValidator<>((NotEmpty) annotation);
        } else if (annotation instanceof Min) {
            validator = new MinValidator((Min) annotation);
        } else if (annotation instanceof Max) {
            validator = new MaxValidator((Max) annotation);
        } else if (annotation instanceof Range) {
            validator = new RangeValidator((Range) annotation);
        } else if (annotation instanceof Len){
            validator = new LenValidator((Len) annotation);
        }

        if (validator != null) {
            boolean valid = validator.isValid(object);
            if (!valid) {
                Toast.makeText(context, validator.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}
