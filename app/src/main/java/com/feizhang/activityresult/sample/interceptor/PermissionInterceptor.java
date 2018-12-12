package com.feizhang.activityresult.sample.interceptor;

import android.content.Context;
import android.content.Intent;

import com.feizhang.activityresult.Interceptor;
import com.feizhang.activityresult.sample.PermissionGrantActivity;

public class PermissionInterceptor extends Interceptor {

    @Override
    public boolean isValid(Context context) {
        return PermissionGrantActivity.isPermissionAllowed(context);
    }

    @Override
    public Intent getTargetIntent(Context context) {
        return new Intent(context, PermissionGrantActivity.class);
    }
}
