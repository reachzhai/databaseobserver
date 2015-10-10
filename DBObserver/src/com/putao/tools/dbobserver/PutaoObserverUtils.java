package com.putao.tools.dbobserver;

public class PutaoObserverUtils {
    public static final String PUTAO_OBSERVER_TAG = "PutaoObserver";

    public static final String PUTAO_DB_WARNING_TABLE_URI = "content://com.android.putao/putaowarning";
    public static final String TABLE_COLUMN_NAME_WARNING_ID = "warning_id";
    public static final String TABLE_COLUMN_NAME_WARNING_NAME = "warning_name";
    public static final String TABLE_COLUMN_NAME_WARNING_DATA = "warning_data";
    public static final String TABLE_COLUMN_NAME_WARNING_STATE = "warning_state";
    public static final String TABLE_COLUMN_NAME_WARNING_TIME = "warning_time";

    public static final int BASE_MSG_CODE = 666;
    public static final int GSENSOR_MSG_CODE = BASE_MSG_CODE + 1;
    public static final int LSENSOR_MSG_CODE = BASE_MSG_CODE +2;
    public static final int OSENSOR_MSG_CODE = BASE_MSG_CODE +3;
    public static final int ALARM_MSG_CODE = BASE_MSG_CODE + 4;
    public static final int TIME_LIMIT_MSG_CODE = BASE_MSG_CODE + 5;
}
