package com.example.apptamlinh.ThanSHFeature.BieuDoNgaySinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;

public class BieuDoNgaySinhInputActivity extends AppCompatActivity {
    private Button btnBack, btnContinue;
    private NumberPicker numPickerDay, numPickerMonth, numPickerYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bieu_do_ngay_sinh_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnContinue = findViewById(R.id.btnContinue);
        numPickerDay = findViewById(R.id.numPickerDay);
        numPickerMonth = findViewById(R.id.numPickerMonth);
        numPickerYear = findViewById(R.id.numPickerYear);
        numPickerDay.setMinValue(1);
        numPickerDay.setMaxValue(31);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);
        numPickerYear.setMinValue(1900);
        numPickerYear.setMaxValue(2024);
        //Default
        numPickerDay.setValue(29);
        numPickerMonth.setValue(12);
        numPickerYear.setValue(2003);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iYear = numPickerYear.getValue();
                int iMonth = numPickerMonth.getValue();
                int iDay = numPickerDay.getValue();

                String selectedValueString = String.valueOf(iYear);

                Intent intentDetailBD = new Intent(BieuDoNgaySinhInputActivity.this, BieuDoNgaySinhDetailActivity.class);
                intentDetailBD.putExtra("data", iYear);
                startActivity(intentDetailBD);
            }
        });
    }
}