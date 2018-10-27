package com.feizhang.activityresult;

import android.support.v4.app.FragmentActivity;

/**
 * Callback of interceptor.
 */
public abstract class OnInterceptResult {
    private final FragmentActivity mActivity;

    protected OnInterceptResult(FragmentActivity activity) {
        mActivity = activity;
    }

    /**
     * Called when all interceptors are valid,
     * your should implement it to init data, views and http access job .
     */
    public abstract void invoke();

    /**
     * Called when canceled in interceptor's target Activity.
     */
    void finishSelf() {
        mActivity.finish();
    }

}
