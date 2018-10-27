package com.feizhang.activityresult;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class Interceptor {

    /**
     * Request code used to start activity for result.
     *
     * @return request code
     */
    public abstract int getRequestCode();

    /**
     * Check interceptor's condition is meet or no.
     *
     * @param context Android context
     * @return condition is meet or no
     */
    public abstract boolean isValid(Context context);

    /**
     * if condition was not satisfied, it'll be called to acquire resource or permission and so on.
     *
     * @param fragment see {@link Activity}
     */
    public abstract void process(Fragment fragment);
}