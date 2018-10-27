package com.feizhang.activityresult.sample;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(v.getContext())
                        .edit().putBoolean("loginState", true).apply();
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    public static boolean isLogin(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("loginState", false);
    }

}
