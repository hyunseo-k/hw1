package com.example.hw1.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw1.R;
import com.example.hw1.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        TextView cocktailTitle = (TextView) root.findViewById(R.id.cocktailTitle);
        ImageView cocktailImage = (ImageView) root.findViewById(R.id.cocktailImage);
        TextView cocktailMethod = (TextView) root.findViewById(R.id.cocktailMethod);
        RecyclerView cocktailIngreds = (RecyclerView) root.findViewById(R.id.cocktailIngreds);
        EditText cocktailSearch = (EditText) root.findViewById(R.id.cocktailSearch);
        Button buttonSearch = (Button) root.findViewById(R.id.buttonSearch);
        Button buttonRandom = (Button) root.findViewById(R.id.buttonRandom);






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}