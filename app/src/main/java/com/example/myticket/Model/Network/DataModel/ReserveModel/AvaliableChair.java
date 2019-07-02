package com.example.myticket.Model.Network.DataModel.ReserveModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvaliableChair
{
    boolean isChecked = false;

    int qRCodeId = -1;

    public int getqRCodeId() {
        return qRCodeId;
    }

    public void setqRCodeId(int qRCodeId) {
        this.qRCodeId = qRCodeId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("chair_num")
    @Expose
    private String chairNum;
    @SerializedName("detail")
    @Expose
    private ChairDetail detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChairNum() {
        return chairNum;
    }

    public void setChairNum(String chairNum) {
        this.chairNum = chairNum;
    }

    public ChairDetail getDetail() {
        return detail;
    }

    public void setDetail(ChairDetail detail) {
        this.detail = detail;
    }
}
