package com.example.mvvm.Veiw;

import android.annotation.SuppressLint;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvvm.Model.DataModel;
import com.example.mvvm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddData extends AppCompatActivity {

    EditText name,roll,sClass,regestarion,faherName,school,gmail,number,image;
    Button button,chooseImage;
    ImageView imageVeiw;

    Uri mainURI;
    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sclass), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        sClass = findViewById(R.id.sclass);
        regestarion = findViewById(R.id.regestarion);
        faherName = findViewById(R.id.faherName);
        school = findViewById(R.id.school);
        gmail = findViewById(R.id.gmail);
        number = findViewById(R.id.number);
        image = findViewById(R.id.image);
        button = findViewById(R.id.button);
        imageVeiw = findViewById(R.id.imageVeiw);
        chooseImage = findViewById(R.id.chooseImage);
        progressBar = findViewById(R.id.progressBar);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Image");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);


                StorageReference sRef = storageReference.child(System.currentTimeMillis()+".jpg");
                sRef.putFile(mainURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(AddData.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String Name = name.getText().toString();
                                    String Roll = roll.getText().toString();
                                    String SClass = sClass.getText().toString();
                                    String Regestarion = regestarion.getText().toString();
                                    String FaherName = faherName.getText().toString();
                                    String School = school.getText().toString();
                                    String Gmail = gmail.getText().toString();
                                    String Number = number.getText().toString();
                                    String Image = uri.toString();

                                    DataInsert(Name,Roll,SClass,Regestarion,FaherName,School,Gmail,Number,Image);
                                }
                            });

                        }else {
                            Toast.makeText(AddData.this, "Image Not Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });



    }

    private void DataInsert(String name, String roll, String SClass, String regestarion, String faherName, String school, String gmail, String number, String image) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        DataModel dataModel = new DataModel(name, image, SClass, roll, regestarion, faherName, school, gmail, number);
        databaseReference.push().setValue(dataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Toast.makeText(AddData.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(AddData.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.GONE);
                }


            }
        });

    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    imageVeiw.setImageURI(uri);
                    mainURI= uri;
                    // Handle the returned Uri
                }
            });
}