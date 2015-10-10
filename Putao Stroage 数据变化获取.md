

####**0.项目中引用jar文件putao_observer_support.jar**
------------


####**1.在AndroidManifest.xml中添加读putao provider的权限**
-------------------------------------------

    <uses-permission android:name="com.android.putao.read" />
    

####**2.代码中增加如下code,注意需要用到上下文环境context**
--------------------------------

    private Uri mUri = Uri.parse(PutaoObserverUtils.PUTAO_DB_WARNING_TABLE_URI);
    private PutaoObserver mPutaoObserver;
    private Handler mHandler = new Handler() {
        
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
    

####**3.注册和解除注册**
---------

        // 添加观察者
    if(mPutaoObserver ==null){
        mPutaoObserver = new PutaoObserver(mHandler, this, mUri);
        getContentResolver().registerContentObserver(mUri, true, mPutaoObserver);
        Log.e("PutaoObserver", "start");
    }

        // 解除观察者
    if (mPutaoObserver != null) {
        getContentResolver().unregisterContentObserver(mPutaoObserver);
        Log.e("PutaoObserver", "end");
    }