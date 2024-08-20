package com.example.mvvm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.Model.DataModel;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {

    MutableLiveData<ArrayList<DataModel>> mutableLiveData = new MutableLiveData<>();

    public void setData(ArrayList<DataModel> arrayList) {
        mutableLiveData.setValue(arrayList);
    }

    public MutableLiveData<ArrayList<DataModel>> getData() {
        return mutableLiveData;
    }
}
