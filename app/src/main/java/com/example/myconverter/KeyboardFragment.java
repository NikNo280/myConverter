package com.example.myconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class KeyboardFragment extends Fragment {

    MainViewModel mainViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_keyboard, container, false);

        layout.findViewById(R.id.zero_button).setOnClickListener(item -> mainViewModel.addValue("0"));
        layout.findViewById(R.id.one_button).setOnClickListener(item -> mainViewModel.addValue("1"));
        layout.findViewById(R.id.two_button).setOnClickListener(item -> mainViewModel.addValue("2"));
        return layout;
    }
}