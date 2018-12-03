package com.feizhang.activityresult;

/**
 * Callback of interceptor.
 */
public interface OnInterceptResult {

    /**
     * Called when all interceptors are valid,
     * your should implement it to init data, views and http access job .
     */
    void invoke();
}
