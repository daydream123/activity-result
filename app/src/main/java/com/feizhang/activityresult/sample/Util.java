package com.feizhang.activityresult.sample;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

public class Util {

    public static String resolvePhoneNumber(Context context, Uri contactUri) {
        Cursor cursor = context.getContentResolver().query(contactUri,
                null, null, null, null);
        if (cursor == null) {
            return "";
        }

        try {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER);
                if (columnIndex < 0) {
                    return "";
                }
                boolean havePhone = cursor.getInt(columnIndex) == 1;

                if (havePhone) {
                    columnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    if (columnIndex < 0) {
                        return "";
                    }
                    String phoneNumber = cursor.getString(columnIndex);

                    // format phone number
                    if (!TextUtils.isEmpty(phoneNumber)) {
                        return phoneNumber.replace("+86", "").replaceAll("\\s*", "").replaceAll("-", "");
                    }
                }
            }
            return "";
        } finally {
            cursor.close();
        }
    }
}
