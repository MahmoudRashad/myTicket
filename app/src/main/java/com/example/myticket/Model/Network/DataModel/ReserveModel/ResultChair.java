package com.example.myticket.Model.Network.DataModel.ReserveModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultChair
{

    @SerializedName("avaliable_chair")
    @Expose
    private List<AvaliableChair> avaliableChair = null;
    @SerializedName("type_chair")
    @Expose
    private List<TypeChair> typeChair = null;

    public List<AvaliableChair> getAvaliableChair() {
        return avaliableChair;
    }

    public void setAvaliableChair(List<AvaliableChair> avaliableChair) {
        this.avaliableChair = avaliableChair;
    }

    public List<TypeChair> getTypeChair() {
        return typeChair;
    }

    public void setTypeChair(List<TypeChair> typeChair) {
        this.typeChair = typeChair;
    }
}
