package com.feizhang.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class to provider a simplified startActivityForResult()
 * and an api to check interceptors.
 */
public class ActivityResult {
    private static final String TAG = ActivityResult.class.getSimpleName();

    private List<Interceptor> mInterceptors = new ArrayList<>();
    private Lazy<ResultFragment> mResultFragment;

    public ActivityResult(FragmentActivity activity) {
        mResultFragment = getLazySingleton(activity.getSupportFragmentManager());
        findInterceptors(activity);
    }

    public ActivityResult(Fragment fragment) {
        mResultFragment = getLazySingleton(fragment.getChildFragmentManager());
        findInterceptors(fragment);
    }

    /**
     * Convenient method to start activity for result.
     */
    public void startActivityForResult(Intent intent, OnResultCallback callback) {
        mResultFragment.get().startActivityForResult(intent, callback);
    }

    /**
     * Check if interceptors specified with annotation {@link InterceptWith} are valid or not.
     */
    public void intercept(final OnInterceptResult callback) {
        mResultFragment.get().intercept(new OnResultCallback() {
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                for (Interceptor interceptor : mInterceptors) {
                    if (interceptor.getRequestCode() == requestCode) {
                        if (resultCode == Activity.RESULT_OK) {
                            verifyInterceptors(callback);
                            break;
                        } else if (resultCode == Activity.RESULT_CANCELED) {
                            callback.finishSelf();
                            break;
                        }
                    }
                }
            }
        });

        // verify interceptors
        if (!mInterceptors.isEmpty()) {
            verifyInterceptors(callback);
        }
    }

    private void findInterceptors(Object object) {
        mInterceptors.clear();
        InterceptWith annotation = object.getClass().getAnnotation(InterceptWith.class);
        if (annotation != null) {
            Class<? extends Interceptor>[] classes = annotation.value();
            for (Class<? extends Interceptor> clazz : classes) {
                try {
                    mInterceptors.add(clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void verifyInterceptors(OnInterceptResult callback) {
        if (mInterceptors.isEmpty()) {
            return;
        }

        for (int i = 0; i < mInterceptors.size(); i++) {
            Interceptor interceptor = mInterceptors.get(i);
            if (interceptor.isValid(mResultFragment.get().getContext())) {
                if (i == mInterceptors.size() - 1) {
                    callback.invoke();
                    break;
                }
            } else {
                interceptor.process(mResultFragment.get());
                break;
            }
        }
    }

    @NonNull
    private Lazy<ResultFragment> getLazySingleton(@NonNull final FragmentManager fragmentManager) {
        return new Lazy<ResultFragment>() {
            private ResultFragment permissionsFragment;

            @Override
            public synchronized ResultFragment get() {
                if (permissionsFragment == null) {
                    permissionsFragment = getPermissionsFragment(fragmentManager);
                    permissionsFragment.setLogging(true);
                }
                return permissionsFragment;
            }
        };
    }

    private ResultFragment getPermissionsFragment(@NonNull final FragmentManager fragmentManager) {
        ResultFragment permissionsFragment = (ResultFragment) fragmentManager.findFragmentByTag(TAG);
        boolean isNewInstance = permissionsFragment == null;
        if (isNewInstance) {
            permissionsFragment = new ResultFragment();
            fragmentManager
                    .beginTransaction()
                    .add(permissionsFragment, TAG)
                    .commitNow();
        }
        return permissionsFragment;
    }

    @FunctionalInterface
    interface Lazy<V> {
        V get();
    }
}
