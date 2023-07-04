package com.example.hw1.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hw1.DetailActivity;
import com.example.hw1.Junbun;
import com.example.hw1.ListViewAdapter;
import com.example.hw1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private ListView listView;
    private ListViewAdapter listViewAdapter;

    Map<Integer, Junbun> items = new HashMap<Integer, Junbun>();
//    private ArrayList<Junbun> items;
    private FloatingActionButton fab;

    private ArrayList<Junbun> filteredItems;

    private static final int REQUEST_CODE_ADD_JUNBUN = 1;

    private ActivityResultLauncher<Intent> addJunbunLauncher;
    private ActivityResultLauncher<Intent> editJunbunLauncher;
    private static final int TEST_TYPE = 1;

    SearchView searchView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the activity result launcher
        addJunbunLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.hasExtra("junbun")) {
                                Junbun junbun = (Junbun) data.getSerializableExtra("junbun");

                                // Use the received Junbun object as needed
                                Log.d("Received Junbun", junbun.getName() + ", " + junbun.getNumber() + ", " + junbun.getAddress());

                                // Add the received Junbun to the list
                                items.add(junbun);
                                filterItems(searchView.getQuery().toString(), filteredItems);
                                listViewAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        editJunbunLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            // Retrieve the updated data from the result intent
                            Intent data = result.getData();
                            String updatedName = data.getStringExtra("updatedName");
                            String updatedNumber = data.getStringExtra("updatedNumber");
                            String updatedAddress = data.getStringExtra("updatedAddress");
                            int updatedIndex = data.getIntExtra("updatedIndex", 0);
                            // Update the data in the list view or perform any necessary actions
                            // based on the updated data
                            Junbun updatedJunbun = new Junbun(updatedName, updatedNumber, updatedAddress);

                            filteredItems.set(updatedIndex, updatedJunbun);
                            Log.d("updatedJunbun", String.valueOf(updatedJunbun.getName()));
                            listViewAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }




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
        fab = root.findViewById(R.id.fab);


        filteredItems = new ArrayList<>(items);

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
                editJunbunLauncher.launch(intent);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddActivity.class);
                addJunbunLauncher.launch(intent);

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

                Junbun junbun = new Junbun(jsonObject.getString("name"),jsonObject.getString("number"),jsonObject.getString("address") );

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
