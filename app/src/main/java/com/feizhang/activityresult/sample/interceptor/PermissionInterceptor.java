package com.feizhang.activityresult.sample.interceptor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.feizhang.activityresult.Interceptor;
import com.feizhang.activityresult.sample.PermissionGrantActivity;

public class PermissionInterceptor extends Interceptor {

    @Override
    public int getRequestCode() {
        return 222;
    }

    @Override
    public boolean isValid(Context context) {
        return PermissionGrantActivity.isPermissionAllowed(context);
    }

    @Override
    public void process(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), PermissionGrantActivity.class);
        fragment.startActivityForResult(intent, getRequestCode());
    }
}
