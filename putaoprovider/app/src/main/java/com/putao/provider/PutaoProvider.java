package com.putao.provider;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.lidroid.xutils.exception.DbException;
import com.putao.dbutils.PuTaoDBUtils;
import com.putao.provider.entities.PuTaoWarningDBBean;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class PutaoProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private Context mContext;
    //private ContentResolver mContentResolver;
    private DbUtils db = null;
    private static PutaoProvider mInstance;
    private DbUpgradeListener dbListener = null;

    static {
        sUriMatcher.addURI(PuTaoDBUtils.AUTHORITY, "putaowarning", PuTaoDBUtils.WARNING_STATE);
        sUriMatcher.addURI(PuTaoDBUtils.AUTHORITY, "putaowarning/#", PuTaoDBUtils.WARNING_STATE_ID);
        sUriMatcher.addURI(PuTaoDBUtils.AUTHORITY, "putaowarning/delete", PuTaoDBUtils.WARNING_STATE_DELETE);
    }

    private boolean initialize() {
        mInstance = this;
        mContext = getContext();
        //mContentResolver = mContext.getContentResolver();
        dbListener = new DbUpgradeListener() {

            @Override
            public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                try {
                    db.dropTable(PuTaoWarningDBBean.class);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        };
        return true;
    }

    private void initializeDB() {
        db = DbUtils.create(mContext, PuTaoDBUtils.DB_PATH, PuTaoDBUtils.DB_NAME, PuTaoDBUtils.DB_VERSION, dbListener);
        db.configAllowTransaction(true);
        db.configDebug(true);
    }

    @Override
    public boolean onCreate() {
        return initialize();
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final int match = sUriMatcher.match(uri);
        if (match == PuTaoDBUtils.WARNING_STATE) {
            Cursor c = null;
            try {
                c = db.execQuery("select * from putaowarning");
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//            SQLiteDatabase dataBase = db.execQuery(sql);
//            qb.setTables(TABLE_NAME);
//            Cursor c = qb.query(db, projection, selection, null, null, null, sortOrder);
//            c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        if (match == PuTaoDBUtils.WARNING_STATE) {
            insertOrUpdate(match, values);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        if (match == PuTaoDBUtils.WARNING_STATE) {
            deleteTableWarning();
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        if (match == PuTaoDBUtils.WARNING_STATE) {
            insertOrUpdate(match, values);
        }
        return 0;
    }

    private boolean insertOrUpdate(int match, ContentValues values) {
        boolean flag = false;
        if (values == null) return flag;

        PuTaoWarningDBBean data = new PuTaoWarningDBBean();
        if (values.getAsInteger("warning_id") != null) {
            data.setmPuTaoWarningId(values.getAsInteger("warning_id"));
        }

        if (values.getAsString("warning_name") != null) {
            data.setmPuTaoWarningName(values.getAsString("warning_name"));
        }

        if (values.getAsBoolean("warning_state") != null) {
            data.setmPutaoWarningState(values.getAsBoolean("warning_state"));
        }

        if (values.getAsString("warning_data") != null) {
            data.setmPutaoWarningData(values.getAsString("warning_data"));
        }

        if (values.getAsLong("warning_time") != null) {
            data.setmPutaoWarningTime(values.getAsLong("warning_time"));
        }

        try {
            initializeDB();
            if (db != null) {
                db.saveOrUpdate(data);
                getContext().getContentResolver().notifyChange(PuTaoDBUtils.CONTENT_URI, null);
            }
            flag = true;
        } catch (DbException e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    private boolean deleteTableWarning() {
        boolean flag = false;

        try {
            initializeDB();
            if (db != null)
                db.dropTable(PuTaoWarningDBBean.class);
            flag = true;
        } catch (DbException e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

}
