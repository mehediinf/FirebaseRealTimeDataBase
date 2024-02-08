package com.example.firebaserealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button saveDataButton;
    private EditText nameEditText,ageEditText;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        saveDataButton = findViewById(R.id.saveDataButton_Id);
        nameEditText = findViewById(R.id.nameEditText_Id);
        ageEditText = findViewById(R.id.ageEditText_Id);
        
        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                saveData();
                
            }
        });

    }

    private void saveData() {

        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        Student student = new Student(name,age);

        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(),"Student info is added",Toast.LENGTH_LONG).show();

    }
}