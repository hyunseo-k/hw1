package com.example.hw1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw1.ui.home.EditActivity;

public class DetailActivity extends AppCompatActivity {
    Intent intent;
    String name;
    String number;
    String address;
    int index;
    Button button2;
    Button button;

    private static final int TEST_TYPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent = getIntent();
        name = intent.getStringExtra("name");
        number = intent.getStringExtra("number");
        address = intent.getStringExtra("address");
        index = intent.getIntExtra("index", 0);
        getSupportActionBar().setTitle(name+" Info");

        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(name);
        TextView numberText = findViewById(R.id.numberText);
        numberText.setText(number);
        TextView addressText = findViewById(R.id.addressText);
        addressText.setText(address);
        Button buttonEdit = findViewById(R.id.buttonEdit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("number", number);
                intent.putExtra("address", address);
                intent.putExtra("index", index);
                startActivityForResult(intent, TEST_TYPE);
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = "tel:" + number;
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp));
                startActivity(mIntent);
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsIntent(number);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEST_TYPE) {
            if (resultCode == RESULT_OK) {
                // Retrieve the updated data from the result intent
                String updatedName = data.getStringExtra("updatedName");
                String updatedNumber = data.getStringExtra("updatedNumber");
                String updatedAddress = data.getStringExtra("updatedAddress");
                int updatedIndex = data.getIntExtra("updatedIndex", 0);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedName", updatedName);
                resultIntent.putExtra("updatedNumber", updatedNumber);
                resultIntent.putExtra("updatedAddress", updatedAddress);
                resultIntent.putExtra("updatedIndex",updatedIndex);
                setResult(RESULT_OK, resultIntent);
                // Update the data in the list view or perform any necessary actions
                // based on the updated data
            }
        }
    }


    public void sendSmsIntent(String number){
        try{
            Uri smsUri = Uri.parse("sms:"+number);
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
            sendIntent.putExtra("sms_body", "");
            startActivity(sendIntent);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}