package com.example.myconverter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData<String> liveInputItem = new MutableLiveData<>("");
    private final MutableLiveData<String> liveOutputItem = new MutableLiveData<>("");
    private final MutableLiveData<String> inputEditLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> outputEditLiveData = new MutableLiveData<>("");

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void addValue(String str)
    {
        inputEditLiveData.setValue(inputEditLiveData.getValue() + str);
    }

    public MutableLiveData<String> getInputEditData()
    {
        return inputEditLiveData;
    }

    public void getResult()
    {
        double inputCoefficient;
        double outputCoefficient;

        switch (Objects.requireNonNull(liveInputItem.getValue())) {
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

        switch (Objects.requireNonNull(liveInputItem.getValue())) {
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
        outputEditLiveData.setValue("1337");
    }
}
