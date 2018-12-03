package com.feizhang.activityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;

/**
 * It's a interceptor fragment and it is used to startActivityForResult()
 * and pass activity result to its observer via callback.
 */
public class ResultFragment extends Fragment {
    private static final String TAG = "ResultFragment";

    private SparseArray<OnResultCallback> mResultCallbackStorage = new SparseArray<>();
    private int mRequestCode = 200;
    private boolean mLogging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    void startActivityForResult(Intent intent, OnResultCallback callback) {
        startActivityForResult(intent, ++mRequestCode);
        mResultCallbackStorage.put(mRequestCode, callback);
    }

    void setResultCallback(OnResultCallback callback) {
        mResultCallbackStorage.put(++mRequestCode, callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OnResultCallback callback = mResultCallbackStorage.get(requestCode);
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, data);
        }
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
