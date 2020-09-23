package com.example.myconverter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Objects;


public class DisplayFragment extends Fragment {

    MainViewModel mainViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_display, container, false);
        EditText edit = layout.findViewById(R.id.input_edit);
        final Observer<String> inputObserver = new Observer<String>(){
            @Override
            public void onChanged(String newInput) {
                edit.setText(newInput);
            }
        };
        mainViewModel.getInputEditData().observe(getViewLifecycleOwner(), inputObserver);
        return layout;
    }
}