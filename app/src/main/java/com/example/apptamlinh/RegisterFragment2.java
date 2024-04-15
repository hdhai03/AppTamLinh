package com.example.apptamlinh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class RegisterFragment2 extends Fragment {

    private NumberPicker numPickerDay;
    private NumberPicker numPickerMonth;
    private NumberPicker numPickerYear;

    public RegisterFragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_register2, container, false);
        numPickerDay = mView.findViewById(R.id.numPickerDay);
        numPickerMonth = mView.findViewById(R.id.numPickerMonth);
        numPickerYear = mView.findViewById(R.id.numPickerYear);

        numPickerDay.setMinValue(1);
        numPickerDay.setMaxValue(31);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);
        numPickerYear.setMinValue(1900);
        numPickerYear.setMaxValue(2024);
        return mView;

    }

}