package com.putao.tools.dbobserver;

import com.example.dbobserver.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class MainActivity extends Activity {

    private Uri mUri = Uri.parse(PutaoObserverUtils.PUTAO_DB_WARNING_TABLE_URI);
    private PutaoObserver observer;
    private Handler mMyHandler = new Handler() {
        
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case PutaoObserverUtils.BASE_MSG_CODE:
                Log.v("PutaoObserver", "[db change]");
                break;
            case PutaoObserverUtils.GSENSOR_MSG_CODE:
                Log.v("PutaoObserver", "[db gsensor change]" + msg.obj);
                break;
            case PutaoObserverUtils.LSENSOR_MSG_CODE:
                Log.v("PutaoObserver", "[db lsensor change]" + msg.obj);
                break;
            case PutaoObserverUtils.OSENSOR_MSG_CODE:
                Log.v("PutaoObserver", "[db osensor change]" + msg.obj);
            case PutaoObserverUtils.ALARM_MSG_CODE:
                Log.v("PutaoObserver", "[alarm]" + msg.obj);
            case PutaoObserverUtils.TIME_LIMIT_MSG_CODE:
                Log.v("PutaoObserver", "[time]" + msg.obj);
                break;
            default:
                Log.v("PutaoObserver", "[no info]");
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 添加观察者
        observer = new PutaoObserver(mMyHandler, this, mUri);
        getContentResolver().registerContentObserver(mUri, true, observer);
        Log.e("PutaoObserver", "start");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        // 解除观察者
        if (observer != null) {
            getContentResolver().unregisterContentObserver(observer);
            Log.e("PutaoObserver", "end");
        }

    }

}
