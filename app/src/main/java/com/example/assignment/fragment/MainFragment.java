package com.example.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.assignment.adapter.RecyclerViewAdapter;
import com.example.assignment.databinding.FragmentMainBinding;
import com.example.assignment.model.Data;
import com.example.assignment.viewmodel.MainViewModel;

import java.util.List;


public class MainFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;
    RecyclerViewAdapter recyclerViewAdapter;
    MainViewModel mainViewModel;
    MainFragment context;
    Observer<List<Data>> dataListUpdateObserver = new Observer<List<Data>>() {
        @Override
        public void onChanged(List<Data> dataList) {
            recyclerViewAdapter.updateDataList(dataList);
            requireActivity().setTitle(mainViewModel.title);

        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(getLayoutInflater(), container, false);

        context = this;
        mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(MainViewModel.class);
        mainViewModel.getMutableLiveData().observe(requireActivity(), dataListUpdateObserver);

        recyclerViewAdapter = new RecyclerViewAdapter(requireActivity());
        fragmentMainBinding.recyclerviewData.setAdapter(recyclerViewAdapter);

        fragmentMainBinding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //refreshing data
                fragmentMainBinding.swipeLayout.setRefreshing(false);
                mainViewModel.getMutableLiveData().observe(requireActivity(), dataListUpdateObserver);               // recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        return fragmentMainBinding.getRoot();

    }


}