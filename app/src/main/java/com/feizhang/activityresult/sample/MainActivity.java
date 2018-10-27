package com.feizhang.activityresult.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.feizhang.activityresult.ActivityResult;
import com.feizhang.activityresult.OnResultCallback;

public class MainActivity extends AppCompatActivity {
    private ActivityResult mActivityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityResult = new ActivityResult(this);

        // open order detail from activity
        Button fromActivityBtn = findViewById(R.id.fromActivityBtn);
        fromActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), OrderDetailActivity.class));
            }
        });

        // open admin page (must be in login state and permission is granted)
        Button checkTwoBtn = findViewById(R.id.checkTwoBtn);
        checkTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AdminActivity.class));
            }
        });

        // pick contact from activity
        Button pickContactBtn = findViewById(R.id.pickContactFromActivity);
        pickContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                mActivityResult.startActivityForResult(intent, new OnResultCallback() {

                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            String phoneNum = Util.resolvePhoneNumber(getApplicationContext(), data.getData());
                            Toast.makeText(MainActivity.this, "phone number: " + phoneNum, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


}
