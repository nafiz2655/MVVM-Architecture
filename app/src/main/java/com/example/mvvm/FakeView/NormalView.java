package com.example.mvvm.FakeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Adapter.MyAdapter;
import com.example.mvvm.Model.DataModel;
import com.example.mvvm.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NormalView extends AppCompatActivity {

    RecyclerView recyclerView;
   // ArrayList<DataModel> arrayList = new ArrayList<>();

    ArrayList<DataModel> privateArrayList = new ArrayList<>();

    ProgressBar progress;
    MyAdapter myAdapter;
    PrivateAdapter PrivateAdapter;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_normalview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        recyclerView = findViewById(R.id.recyclerView);
        progress = findViewById(R.id.progress);

        //myAdapter = new MyAdapter(this, arrayList);
        //recyclerView.setAdapter(myAdapter);

        PrivateAdapter = new PrivateAdapter();
        recyclerView.setAdapter(PrivateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetDataInFirebase();


    }

    private void GetDataInFirebase() {

        progress.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataModel dataModel = dataSnapshot.getValue(DataModel.class);
                    //arrayList.add(dataModel);
                    privateArrayList.add(dataModel);

                }

                //myAdapter.notifyDataSetChanged();
                PrivateAdapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.setVisibility(View.GONE);
                Toast.makeText(NormalView.this, "Something Wrong Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Distroy", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    private class PrivateAdapter extends RecyclerView.Adapter<PrivateAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, sClass, roll, regestarion, faherName, school, gmail, number;
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tv_name);
                sClass = itemView.findViewById(R.id.tv_class);
                roll = itemView.findViewById(R.id.tv_roll);
                regestarion = itemView.findViewById(R.id.tv_reg);
                faherName = itemView.findViewById(R.id.tv_fathername);
                school = itemView.findViewById(R.id.tv_school);
                gmail = itemView.findViewById(R.id.tv_email);
                number = itemView.findViewById(R.id.tv_phone);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.layout, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            DataModel dataModel = privateArrayList.get(position);
            holder.name.setText("Name : " + dataModel.getName());
            holder.sClass.setText("Class : " + dataModel.getsClass());
            holder.roll.setText("Roll : " + dataModel.getRoll());
            holder.regestarion.setText("Reg : " + dataModel.getRegestarion());
            holder.faherName.setText("Father Name : " + dataModel.getFaherName());
            holder.school.setText("School : " + dataModel.getSchool());
            holder.gmail.setText("Gmail : " + dataModel.getGmail());
            holder.number.setText("Number : " + dataModel.getNumber());

            String image = dataModel.getImage();
            Picasso.get().load(image).into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return privateArrayList.size();
        }


    }
}