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

    ;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mvvmactivity, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        DataViewModel dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        dataViewModel.getDataModelList().observe(getActivity(), dataModels -> {
            if (dataModels != null) {
                myAdapter = new MyAdapter(getActivity(),dataModels);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
