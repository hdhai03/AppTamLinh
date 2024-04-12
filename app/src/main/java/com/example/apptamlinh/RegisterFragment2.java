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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RegisterFragment2() {
    }

    public static RegisterFragment2 newInstance(String param1, String param2) {
        RegisterFragment2 fragment = new RegisterFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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