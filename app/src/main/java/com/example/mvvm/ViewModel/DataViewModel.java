package com.example.mvvm.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.Model.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {

    private MutableLiveData<ArrayList<DataModel>> mutableLiveData = new MutableLiveData<>();;
    private DatabaseReference databaseReference;

    public DataViewModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<DataModel> arrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataModel dataModel = dataSnapshot.getValue(DataModel.class);
                    arrayList.add(dataModel);
                }
                mutableLiveData.setValue(arrayList); // Data set to LiveData
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error case, maybe set an error message to LiveData
            }
        });
    }

    public LiveData<ArrayList<DataModel>> getDataModelList() {
        return mutableLiveData;
    }
}
