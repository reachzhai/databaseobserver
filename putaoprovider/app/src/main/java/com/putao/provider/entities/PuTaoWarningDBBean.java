package com.putao.provider.entities;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "putaowarning")
public class PuTaoWarningDBBean extends EntityBase {

    @Column(column = "warning_id")
    @NoAutoIncrement
    @Id
    private int mPuTaoWarningId;

    @Column(column = "warning_name")
    private String mPuTaoWarningName;

    @Column(column = "warning_state")
    private boolean mPutaoWarningState;

    @Column(column = "warning_data")
    private String mPutaoWarningData;

    @Column(column = "warning_time")
    private long mPutaoWarningTime;

    public String ismPutaoWarningData() {
        return mPutaoWarningData;
    }

    public void setmPutaoWarningData(String mPutaoWarningData) {
        this.mPutaoWarningData = mPutaoWarningData;
    }

    public long getmPutaoWarningTime() {
        return mPutaoWarningTime;
    }

    public void setmPutaoWarningTime(long mPutaoWarningTime) {
        this.mPutaoWarningTime = mPutaoWarningTime;
    }

    public int getmPuTaoWarningId() {
        return mPuTaoWarningId;
    }

    public void setmPuTaoWarningId(int mPuTaoWarningId) {
        this.mPuTaoWarningId = mPuTaoWarningId;
    }

    public String getmPuTaoWarningName() {
        return mPuTaoWarningName;
    }

    public void setmPuTaoWarningName(String mPuTaoWarningName) {
        this.mPuTaoWarningName = mPuTaoWarningName;
    }

    public boolean ismPutaoWarningState() {
        return mPutaoWarningState;
    }

    public void setmPutaoWarningState(boolean mPutaoWarningState) {
        this.mPutaoWarningState = mPutaoWarningState;
    }

    @Override
    public String toString() {
        return "PuTaoWarningDBBean{" + "warning_id=" + getmPuTaoWarningId()
                + ", warning_name=" + mPuTaoWarningName + ", warning_state="
                + mPutaoWarningState + ", warning_data=" + mPutaoWarningData
                + ", warning_time=" + mPutaoWarningTime + "}";
    }

}
