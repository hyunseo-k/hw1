package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    Intent intent;
    String name;
    String number;
    String address;
    Button button2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent = getIntent();
        name = intent.getStringExtra("name");
        number = intent.getStringExtra("number");
        address = intent.getStringExtra("address");
        getSupportActionBar().setTitle(name+" Info");

        TextView nameText = findViewById(R.id.nameText);
        nameText.setText(name);
        TextView numberText = findViewById(R.id.numberText);
        numberText.setText(number);
        TextView addressText = findViewById(R.id.addressText);
        addressText.setText(address);


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