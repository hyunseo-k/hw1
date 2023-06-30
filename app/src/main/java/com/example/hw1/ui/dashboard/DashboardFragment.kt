package com.example.hw1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hw1.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var binding: FragmentDashboardBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //DashboardViewModel dashboardViewModel =
        //new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding!!.textDashboard
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}