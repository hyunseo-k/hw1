package com.example.hw1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw1.Junbun;
import com.example.hw1.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("전화번호 추가");

        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editNumber = (EditText) findViewById(R.id.editNumber);
        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast t = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);
//                t.show();
                Intent intent = getIntent();
                String newName = editName.getText().toString();
                String newNumber = editNumber.getText().toString();
                String newAddress = editAddress.getText().toString();
                int newIndex = intent.getIntExtra("id", -1);

                Junbun junbun = new Junbun(newName, newNumber, newAddress, newIndex);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("junbun", junbun);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }
}