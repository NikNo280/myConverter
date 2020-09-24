package com.example.myconverter;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<String> spinnerInputLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> spinnerOutputLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> inputEditLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> outputEditLiveData = new MutableLiveData<>("");

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setSpinnerInputLiveData(String item)
    {
        spinnerInputLiveData.setValue(item);
    }

    public void setSpinnerOutputLiveData(String item)
    {
        spinnerOutputLiveData.setValue(item);
    }

    public void exchangeLiveData()
    {
        String temp = inputEditLiveData.getValue();
        inputEditLiveData.setValue(outputEditLiveData.getValue());
        outputEditLiveData.setValue(temp);
        temp = spinnerInputLiveData.getValue();
        spinnerInputLiveData.setValue(spinnerOutputLiveData.getValue());
        spinnerOutputLiveData.setValue(temp);
    }

    public void addValue(String str)
    {
        inputEditLiveData.setValue(inputEditLiveData.getValue() + str);
    }

    public void addPoint()
    {
        if(Objects.requireNonNull(inputEditLiveData.getValue()).length() > 0 && inputEditLiveData.getValue().indexOf(".") < 1)
        {
            inputEditLiveData.setValue(inputEditLiveData.getValue() + ".");
        }
    }

    public void clearData(){
        inputEditLiveData.setValue("");
    }
    public MutableLiveData<String> getInputEditData()
    {
        return inputEditLiveData;
    }

    public MutableLiveData<String> getOutputEditData()
    {
        return outputEditLiveData;
    }

    public MutableLiveData<String> getInputSpinnerData()
    {
        return spinnerInputLiveData;
    }

    public MutableLiveData<String> getOutputSpinnerData()
    {
        return spinnerOutputLiveData;
    }

    public void convert()
    {
        double inputCoefficient = 1.0;
        double outputCoefficient = 1.0;

        switch (Objects.requireNonNull(spinnerInputLiveData.getValue())) {
            case "Meters":
            case "Grams":
            case "Rubles" :
                inputCoefficient = 1.0;
                break;
            case "Kilometers":
            case "Kilograms" :
                inputCoefficient = 1000.0;
                break;
            case "Miles" :
                inputCoefficient = 1609.64;
                break;
            case "Pounds" :
                inputCoefficient = 453.592;
                break;
            case "Dollars" :
                inputCoefficient = 2.62;
                break;
            case "Euro" :
                inputCoefficient = 3.09;
                break;
            default:
                inputCoefficient = 1.00;
                break;
        }

        switch (Objects.requireNonNull(spinnerOutputLiveData.getValue())) {
            case "Meters":
            case "Grams":
            case "Rubles" :
                outputCoefficient = 1.0;
                break;
            case "Kilometers":
            case "Kilograms" :
                outputCoefficient = 1000.0;
                break;
            case "Miles" :
                outputCoefficient = 1609.64;
                break;
            case "Pounds" :
                outputCoefficient = 453.592;
                break;
            case "Dollars" :
                outputCoefficient = 2.62;
                break;
            case "Euro" :
                outputCoefficient = 3.09;
                break;
            default:
                outputCoefficient = 1.00;
                break;
        }
        outputEditLiveData.setValue(String.valueOf(String.format("%.2f", (inputCoefficient / outputCoefficient) * Double.parseDouble(Objects.requireNonNull(inputEditLiveData.getValue())))).replace(",", "."));
    }

    public void saveInBuffer(int field, ClipboardManager clipboardManager) {
        switch (field) {
            case 1:
                ClipData clipData = ClipData.newPlainText("text", inputEditLiveData.getValue());
                clipboardManager.setPrimaryClip(clipData);
                break;
            case 2:
                clipData = ClipData.newPlainText("text", outputEditLiveData.getValue());
                clipboardManager.setPrimaryClip(clipData);
                break;
        }
        Toast toast = Toast.makeText(getApplication(), "Save", Toast.LENGTH_SHORT);
        toast.show();
    }
}
