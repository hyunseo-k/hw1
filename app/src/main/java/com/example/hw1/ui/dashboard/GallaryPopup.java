package com.example.hw1.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hw1.R;

public class GallaryPopup extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gallary_popup_activity);

        // View들 가져오기
        TextView imgName = (TextView) findViewById(R.id.imgName);
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        Button closeButton = (Button) findViewById(R.id.closeButton);

        // main Activity에서 데이터 가져오기
        Intent intent = getIntent();

        imgName.setText(intent.getStringExtra("imgName"));
//        int imgId = intent.getIntExtra("imgView", R.drawable.person);
//        byte[] byteArray = getIntent().getByteArrayExtra("imgView");
//        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        imgView.setImageBitmap(bitmap);
        imgView.setImageResource(intent.getIntExtra("imgId", -1));

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backMainActivity();
            }
        });
    }

    private void backMainActivity() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
