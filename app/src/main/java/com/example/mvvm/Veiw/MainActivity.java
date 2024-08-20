package com.example.mvvm.Veiw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvvm.FakeView.MVVMview;
import com.example.mvvm.R;

public class MainActivity extends AppCompatActivity {

    Button adddata,normalView,MVVMView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sclass), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        adddata = findViewById(R.id.button2);
        normalView = findViewById(R.id.button3);
        MVVMView = findViewById(R.id.button4);

        adddata.setOnClickListener(v -> {
            startActivity( new Intent(MainActivity.this,AddData.class));
        });
        normalView.setOnClickListener(v -> {
            DataView.i=0;
            startActivity( new Intent(MainActivity.this,DataView.class));
        });
        MVVMView.setOnClickListener(v -> {
            DataView.i=1;
            startActivity( new Intent(MainActivity.this, DataView.class));
        });



    }



}