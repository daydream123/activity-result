package com.feizhang.activityresult.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.feizhang.activityresult.ActivityResult;
import com.feizhang.activityresult.InterceptWith;
import com.feizhang.activityresult.OnInterceptResult;
import com.feizhang.activityresult.sample.interceptor.LoginInterceptor;

@InterceptWith(LoginInterceptor.class)
public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ActivityResult result = new ActivityResult(this);
        result.intercept(new OnInterceptResult() {

            /**
             * init data or load data from http and so on.
             */
            @SuppressLint("SetTextI18n")
            @Override
            public void invoke() {
                TextView imageView = findViewById(R.id.contentView);
                imageView.setText("This Is the Order Detail Page");
            }
        });
    }
}
