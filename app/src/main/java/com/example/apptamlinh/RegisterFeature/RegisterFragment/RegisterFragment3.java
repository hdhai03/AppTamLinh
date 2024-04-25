package com.example.apptamlinh.RegisterFeature.RegisterFragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apptamlinh.R;

import java.util.Calendar;

public class RegisterFragment3 extends Fragment {
    private Button btnContinue, btnBack;
    private NumberPicker numPickerDay, numPickerMonth, numPickerYear;
    private final String[] months = new String[]{
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
    };


    public RegisterFragment3() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register3, container, false);
        btnContinue = view.findViewById(R.id.btnContinue);


        numPickerDay = view.findViewById(R.id.numPickerDay);
        numPickerMonth = view.findViewById(R.id.numPickerMonth);
        numPickerYear = view.findViewById(R.id.numPickerYear);
        numPickerDay.setMinValue(1);
        numPickerDay.setMaxValue(31);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);
        numPickerYear.setMinValue(1900);
        numPickerYear.setMaxValue(2024);
        numPickerMonth.setDisplayedValues(months);
        numPickerDay.setTextColor(Color.WHITE);
        numPickerMonth.setTextColor(Color.WHITE);
        numPickerYear.setTextColor(Color.WHITE);


        //Default
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Vì tháng bắt đầu từ 0
        int currentYear = calendar.get(Calendar.YEAR);

// Đặt giá trị cho NumberPicker
        numPickerDay.setValue(currentDay);
        numPickerMonth.setValue(currentMonth);
        numPickerYear.setValue(currentYear);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("name");
            String email = bundle.getString("email");
            String password = bundle.getString("password");
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int iYear = numPickerYear.getValue();
                    int iMonth = numPickerMonth.getValue();
                    int iDay = numPickerDay.getValue();

                    String ngaySinh = String.format("%02d/%02d/%04d", iDay, iMonth, iYear);
                    loadFragment(name, email, password, ngaySinh);
                }
            });
        }



        return view;
    }

    private void loadFragment(String name, String email, String password, String ngaySinh) {
        RegisterFragment4 fragment = new RegisterFragment4();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("password", password);
        bundle.putString("ngaySinh", ngaySinh);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}