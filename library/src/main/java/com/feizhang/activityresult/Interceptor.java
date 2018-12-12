package com.feizhang.activityresult;

import android.content.Context;
import android.content.Intent;

public abstract class Interceptor {
    private int requestCode;

    /**
     * Request code used to start activity for result.
     *
     * @return request code
     */
    public final int getRequestCode() {
        return requestCode;
    }

    /**
     * You should not set request code by yourself, it'll set by {@link ActivityResult} automatically.
     *
     * @param requestCode request code
     */
    public final void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    /**
     * Check interceptor's condition is meet or no.
     *
     * @param context Android context
     * @return condition is meet or no
     */
    public abstract boolean isValid(Context context);

    /**
     * if condition was not satisfied, it'll be called to acquire resource or permission and so on.
     */
    public abstract Intent getTargetIntent(Context context);
}