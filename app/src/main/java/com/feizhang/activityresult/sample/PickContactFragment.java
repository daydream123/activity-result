package com.feizhang.activityresult.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.feizhang.activityresult.ActivityResult;
import com.feizhang.activityresult.OnResultCallback;

public class PickContactFragment extends Fragment {
    private ActivityResult mActivityResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityResult = new ActivityResult(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pick_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                mActivityResult.startActivityForResult(intent, new OnResultCallback() {

                    @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            String phoneNum = Util.resolvePhoneNumber(v.getContext(), data.getData());
                            Toast.makeText(v.getContext(), "phone number: " + phoneNum, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}