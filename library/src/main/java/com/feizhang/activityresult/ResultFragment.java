package com.feizhang.activityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * It's a interceptor fragment and it is used to startActivityForResult()
 * and pass activity result to its observer via callback.
 */
public class ResultFragment extends Fragment {
    private static final String TAG = "PermissionsFragment";
    public static final int REQUEST_CODE = 9998;

    private OnResultCallback mOnResultCallback;
    private boolean mLogging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    void startActivityForResult(Intent intent, OnResultCallback callback) {
        startActivityForResult(intent, REQUEST_CODE);
        mOnResultCallback = callback;
    }

    void setResultCallback(OnResultCallback callback) {
        mOnResultCallback = callback;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mOnResultCallback.onActivityResult(requestCode, resultCode, data);
    }

    void setLogging(boolean logging) {
        mLogging = logging;
    }

    void log(String message) {
        if (mLogging) {
            Log.d(TAG, message);
        }
    }
}
