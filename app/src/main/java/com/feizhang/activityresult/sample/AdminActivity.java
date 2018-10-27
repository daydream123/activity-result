package com.feizhang.activityresult.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.feizhang.activityresult.ActivityResult;
import com.feizhang.activityresult.InterceptWith;
import com.feizhang.activityresult.OnInterceptResult;
import com.feizhang.activityresult.sample.interceptor.LoginInterceptor;
import com.feizhang.activityresult.sample.interceptor.PermissionInterceptor;

@InterceptWith({LoginInterceptor.class, PermissionInterceptor.class})
public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activity);

        ActivityResult activityResult = new ActivityResult(this);
        activityResult.intercept(new OnInterceptResult(this) {
            @Override
            public void invoke() {
                TextView textView = findViewById(R.id.contentView);
                textView.setText("This The Admin Manager page");
            }
        });
    }
}
