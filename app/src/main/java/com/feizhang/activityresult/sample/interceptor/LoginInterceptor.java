package com.feizhang.activityresult.sample.interceptor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.feizhang.activityresult.Interceptor;
import com.feizhang.activityresult.sample.LoginActivity;

public class LoginInterceptor extends Interceptor {

    @Override
    public int getRequestCode() {
        return 111;
    }

    @Override
    public boolean isValid(Context context) {
        return LoginActivity.isLogin(context);
    }

    @Override
    public void process(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), LoginActivity.class);
        fragment.startActivityForResult(intent, getRequestCode());
    }
}