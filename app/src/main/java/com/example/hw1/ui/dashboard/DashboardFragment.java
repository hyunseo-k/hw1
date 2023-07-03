package com.example.hw1.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hw1.R;
import com.example.hw1.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ListView listView;
    private MyGallaryAdapter myGallaryAdapter;
    private ArrayList<MyData> dataList;

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.d("popup", "MainActivity로 돌아왔다.");
                    }
                }
            });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        //DashboardViewModel dashboardViewModel =
//        //new ViewModelProvider(this).get(DashboardViewModel.class);
//
//        //binding = FragmentDashboardBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textDashboard;
//        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("갤러리");
            }
        }

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listView = root.findViewById(R.id.gallary_list);

        try {
            this.initializeData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        myGallaryAdapter = new MyGallaryAdapter(this.getContext(), dataList);
        listView.setAdapter(myGallaryAdapter);

        // listView의 아이템 클릭 시 해당 사진을 확대하는 팝업을 띄운다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(getActivity(), "클릭되었습니다", Toast.LENGTH_SHORT);
                toast.show();
                
                // 팝업 액티비티 호출하기

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable) ((ImageView) view.findViewById(R.id.gallary_img)).getDrawable()).getBitmap();
                float scale = (float) (1024/(float)bitmap.getWidth());
                int image_w = (int) (bitmap.getWidth() * scale);
                int image_h = (int) (bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent intent = new Intent(getContext(), GallaryPopup.class);
                intent.putExtra("imgName", ((TextView) view.findViewById(R.id.gallary_text)).getText()); // text를 넘긴다.
                intent.putExtra("imgView", byteArray); // 이미지를 넘긴다.
                startActivityResult.launch(intent);

                
            }
        });

        return root;


    }

    public void initializeData() throws JSONException {
        dataList = new ArrayList<MyData>();

        String json = "";

        try {
            InputStream inputStream = requireContext().getAssets().open("images.json");
            int fileSize = inputStream.available();

            byte[] buffer = new byte[fileSize];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");
            Log.d("json", json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                MyData myData = new MyData(null, null);
                myData.setSrc(jsonObject.getString("src"));
                myData.setText(jsonObject.getString("text"));
                dataList.add(myData);
            }
            //listView.setAdapter(myGallaryAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}