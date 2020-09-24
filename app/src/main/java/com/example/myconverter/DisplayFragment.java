package com.example.myconverter;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;


public class DisplayFragment extends Fragment {

    MainViewModel mainViewModel;
    ClipboardManager clipboard;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_display, container, false);
        EditText editInput = layout.findViewById(R.id.input_edit);
        EditText editOutput = layout.findViewById(R.id.output_edit);
        Spinner spinnerInput = layout.findViewById(R.id.input_spinner);
        Spinner spinnerOutput = layout.findViewById(R.id.output_spinner);
        spinnerInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                mainViewModel.setSpinnerInputLiveData(spinnerInput.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                mainViewModel.setSpinnerOutputLiveData(spinnerOutput.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final Observer<String> inputEditObserver = new Observer<String>(){
            @Override
            public void onChanged(String newInput) {
                editInput.setText(newInput);
            }
        };
        final Observer<String> outputEditObserver = new Observer<String>(){
            @Override
            public void onChanged(String newInput) {
                editOutput.setText(newInput);
            }
        };
        final Observer<String> outputSpinnerObserver = new Observer<String>(){
            @Override
            public void onChanged(String newInput) {
                ArrayAdapter adapter = (ArrayAdapter) spinnerOutput.getAdapter();
                spinnerOutput.setSelection(adapter.getPosition(mainViewModel.getInputSpinnerData().getValue()));
            }
        };

        final Observer<String> inputSpinnerObserver = new Observer<String>(){
            @Override
            public void onChanged(String newInput) {
                ArrayAdapter adapter = (ArrayAdapter) spinnerInput.getAdapter();
                spinnerInput.setSelection(adapter.getPosition(mainViewModel.getOutputSpinnerData().getValue()));
            }
        };

        mainViewModel.getInputEditData().observe(getViewLifecycleOwner(), inputEditObserver);
        mainViewModel.getOutputEditData().observe(getViewLifecycleOwner(), outputEditObserver);
        mainViewModel.getOutputEditData().observe(getViewLifecycleOwner(), inputSpinnerObserver);
        mainViewModel.getOutputEditData().observe(getViewLifecycleOwner(), outputSpinnerObserver);

        clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        layout.findViewById(R.id.exchange_button).setOnClickListener(item -> mainViewModel.exchangeLiveData());
        layout.findViewById(R.id.convert_button).setOnClickListener(item -> mainViewModel.convert());
        layout.findViewById(R.id.save_input_button).setOnClickListener(item -> mainViewModel.saveInBuffer(1, clipboard));
        layout.findViewById(R.id.save_output_button).setOnClickListener(item -> mainViewModel.saveInBuffer(2, clipboard));
        return layout;
    }
}