package com.example.hw1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw1.DetailActivity;
import com.example.hw1.R;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Log.d("idid", String.valueOf(getIntent().getIntExtra("id", -1)));

        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        String address = getIntent().getStringExtra("address");
        int id = getIntent().getIntExtra("id", -1);


        EditText editName = findViewById(R.id.editName);
        editName.setText(name);
        EditText editNumber = findViewById(R.id.editNumber);
        editNumber.setText(number);
        EditText editAddress = findViewById(R.id.editAddress);
        editAddress.setText(address);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nName = String.valueOf(editName.getText());
                String nNumber = String.valueOf(editNumber.getText());
                String nAddress = String.valueOf(editAddress.getText());

                Log.d("index", String.valueOf(id));
                Intent intent = new Intent(EditActivity.this, DetailActivity.class);
                intent.putExtra("updatedName", nName);
                intent.putExtra("updatedNumber", nNumber);
                intent.putExtra("updatedAddress", nAddress);
                intent.putExtra("updatedId", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}