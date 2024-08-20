package com.example.mvvm.Veiw;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvm.Fragment.MVVMActivity;
import com.example.mvvm.Fragment.NormalActivity;
import com.example.mvvm.R;

public class DataView extends AppCompatActivity {

    public static int i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dataview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (i==0){

            fragmentTransaction.add(R.id.frameLayout, new NormalActivity());
            fragmentTransaction.commit();
        }else if (i==1){

            fragmentTransaction.add(R.id.frameLayout, new MVVMActivity());
            fragmentTransaction.commit();
        }



    }
}