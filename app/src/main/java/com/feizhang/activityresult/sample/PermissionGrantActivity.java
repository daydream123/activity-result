package com.feizhang.activityresult.sample;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PermissionGrantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_verify);

        Button button = findViewById(R.id.grantBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(v.getContext())
                        .edit().putBoolean("permissionAllowed", true).apply();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    public static boolean isPermissionAllowed(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("permissionAllowed", false);
    }

}
