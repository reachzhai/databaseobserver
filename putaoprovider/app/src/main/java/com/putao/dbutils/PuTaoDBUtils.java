package com.putao.dbutils;

import java.io.File;

import android.net.Uri;
import android.os.Environment;

public class PuTaoDBUtils {

    public static final int WARNING_STATE = 0;
    public static final int WARNING_STATE_ID = 1;

    public static final int WARNING_STATE_DELETE = 2;

    public static final int DB_VERSION = 3;//when data change,db will drop table warning

    public static final int EVENTS_ID = 0;
    public static final int INSTANCES = 3;
    public static final int CALENDARS = 4;
    public static final int CALENDARS_ID = 5;
    public static final int ATTENDEES = 6;
    public static final int ATTENDEES_ID = 7;
    public static final int REMINDERS = 8;
    public static final int REMINDERS_ID = 9;
    public static final int EXTENDED_PROPERTIES = 10;

    public static final String DB_PATH = Environment.getExternalStorageDirectory().getPath()
            + File.separator + ".Putao";
    public static final String DB_NAME = "putao.db";
    /**
     * This authority is used for writing to or querying from the calendar
     * provider. Note: This is set at first run and cannot be changed without
     * breaking apps that access the provider.
     */
    public static final String AUTHORITY = "com.android.putao";

    /**
     * The content:// style URL for the top-level calendar authority
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);


}
