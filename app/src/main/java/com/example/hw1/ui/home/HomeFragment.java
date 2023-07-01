package com.example.hw1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hw1.DetailActivity;
import com.example.hw1.Junbun;
import com.example.hw1.ListViewAdapter;
import com.example.hw1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private ArrayList<Junbun> items;

    SearchView searchView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("전화번호부");
            }
        }

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = root.findViewById(R.id.listView);
        searchView = root.findViewById(R.id.searchView);

        items = new ArrayList<>();


        final ArrayList<Junbun> filteredItems = new ArrayList<>(items);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext(), filteredItems.get(i).address, Toast.LENGTH_SHORT).show();

                Junbun selectedJunbun = filteredItems.get(i);

                Intent intent = new Intent(requireContext(), DetailActivity.class);
                intent.putExtra("name", selectedJunbun.getName());
                intent.putExtra("number", selectedJunbun.getNumber());
                intent.putExtra("address", selectedJunbun.getAddress());

                // Start the DetailActivity
                startActivity(intent);
            }
        });


        listViewAdapter = new ListViewAdapter(filteredItems, requireContext());
        listView.setAdapter(listViewAdapter);

        String json = "";

        try {
            InputStream inputStream = requireContext().getAssets().open("junbun.json");
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

                Junbun junbun = new Junbun();
                junbun.setName(jsonObject.getString("name"));
                junbun.setNumber(jsonObject.getString("number"));
                junbun.setAddress(jsonObject.getString("address"));

                items.add(junbun);

            }

            filterItems("", filteredItems);
            listViewAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterItems(query, filteredItems);
                listViewAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterItems(s, filteredItems);
                listViewAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return root;
    }

    private void filterItems(String query, ArrayList<Junbun> filteredItems) {
        filteredItems.clear();

        if (query.isEmpty()) {
            filteredItems.addAll(items);
        } else {
            String lowerCaseQuery = query.toLowerCase();

            for (Junbun junbun : items) {
                if (junbun.getName().toLowerCase().contains(lowerCaseQuery)) {
                    filteredItems.add(junbun);
                }
            }
        }
    }

}
