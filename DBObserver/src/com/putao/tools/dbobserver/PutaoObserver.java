package com.putao.tools.dbobserver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class PutaoObserver extends ContentObserver {

    private static final String TAG = PutaoObserverUtils.PUTAO_OBSERVER_TAG;
    private Handler mHandler;
    private Context mContext;
    private ContentResolver mContentResolver;
    private Uri mUri = Uri.parse(PutaoObserverUtils.PUTAO_DB_WARNING_TABLE_URI);
    private boolean mDebug = false;
    private int mMsgCode = PutaoObserverUtils.BASE_MSG_CODE;

    public PutaoObserver(Handler handler) {
        super(handler);
        mHandler = handler;
    }

    public PutaoObserver(Handler handler, Context context, Uri uri) {
        super(handler);
        mHandler = handler;
        mContext = context;
        mUri = uri;
        mContentResolver = mContext.getContentResolver();
        Log.v(TAG, "PutaoObserver");
    }

    public PutaoObserver(Handler handler, Context context, Uri uri,boolean debug) {
        super(handler);
        mHandler = handler;
        mContext = context;
        mUri = uri;
        mContentResolver = mContext.getContentResolver();
        mDebug = debug;
        Log.v(TAG, "PutaoObserver debug:"+mDebug);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.v(TAG, "onChange");
        String[] projection = new String[] {
                PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_ID,
                PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_NAME,
                PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_STATE,
                PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_DATA,
                PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_TIME };
        Cursor cursor = mContentResolver.query(mUri, projection, null, null,
                "warning_id desc");

        if (cursor == null) {

        } else if (!cursor.moveToFirst()) {
            cursor.close();
        } else {
            if (mDebug)
                Log.v(TAG, "========================");
            do {
                boolean dataState;
                String dataName = cursor
                        .getString(cursor
                                .getColumnIndex(PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_NAME));
                int dataId = cursor
                        .getInt(cursor
                                .getColumnIndex(PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_ID));
                int tmpDataState = cursor
                        .getInt(cursor
                                .getColumnIndex(PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_STATE));
                String dataData = cursor
                        .getString(cursor
                                .getColumnIndex(PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_DATA));
                long dataTime = cursor
                        .getLong(cursor
                                .getColumnIndex(PutaoObserverUtils.TABLE_COLUMN_NAME_WARNING_TIME));

                switch (tmpDataState) {
                case 1:
                    dataState = true;
                    break;
                default:
                    dataState = false;
                    break;
                }
                if (mDebug) {
                    Log.v(TAG, "dataId : " + dataId);
                    Log.v(TAG, "dataName : " + dataName);
                    Log.v(TAG, "dataState : " + dataState);
                    Log.v(TAG, "dataData : " + dataData);
                    Log.v(TAG, "dataTime : " + dataTime);
                    Log.v(TAG, "========================");
                }
                Message message = Message.obtain();
                message.what = mMsgCode + dataId;
                if (dataId == PutaoObserverUtils.OSENSOR_MSG_CODE) {
                    message.obj = dataData;
                } else {
                    message.obj = dataState;
                }
                mHandler.sendMessage(message);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

}
