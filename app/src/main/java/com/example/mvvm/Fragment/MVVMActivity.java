package com.example.mvvm.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvvm.Adapter.MyAdapter;
import com.example.mvvm.Model.DataModel;
import com.example.mvvm.R;
import com.example.mvvm.ViewModel.DataViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MVVMActivity extends Fragment {

    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList = new ArrayList<>();
    ProgressBar progress;
    MyAdapter myAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mvvmactivity, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progress = view.findViewById(R.id.progress);

        myAdapter = new MyAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GetDataInFirebase();

        return view;
    }


    private void GetDataInFirebase () {

        progress.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataModel dataModel = dataSnapshot.getValue(DataModel.class);
                    arrayList.add(dataModel);

                }

                myAdapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something Wrong Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy () {
        Toast.makeText(getActivity(), "Distroy", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

}
