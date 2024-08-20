package com.example.mvvm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Model.DataModel;
import com.example.mvvm.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public  Context context;
    public ArrayList<DataModel> arrayList;

    public MyAdapter(Context context, ArrayList<DataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, sClass, roll, regestarion, faherName, school, gmail, number;
        ImageView imageView;

        @SuppressLint("WrongViewCast")
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataModel dataModel = arrayList.get(position);
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
        return arrayList.size();
    }


}
