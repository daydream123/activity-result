package com.feizhang.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Callback of {@link Activity#startActivityForResult(Intent, int)}
 * or {@link Fragment#startActivityForResult(Intent, int)}
 */
public interface OnResultCallback {

    /**
     * Called while activity result is responded.
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
