package com.feizhang.activityresult.sample.interceptor;

import android.content.Context;
import android.content.Intent;

import com.feizhang.activityresult.Interceptor;
import com.feizhang.activityresult.sample.LoginActivity;

public class LoginInterceptor extends Interceptor {

    @Override
    public boolean isValid(Context context) {
        return LoginActivity.isLogin(context);
    }

    @Override
    public Intent getTargetIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}