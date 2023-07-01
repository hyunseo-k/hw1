package com.example.hw1.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hw1.R;
import com.example.hw1.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ListView listView;
    private MyGallaryAdapter myGallaryAdapter;
    private ArrayList<MyData> dataList;

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
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listView = root.findViewById(R.id.gallary_list);

        try {
            this.initializeData();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        myGallaryAdapter = new MyGallaryAdapter(this.getContext(), dataList);
        listView.setAdapter(myGallaryAdapter);

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