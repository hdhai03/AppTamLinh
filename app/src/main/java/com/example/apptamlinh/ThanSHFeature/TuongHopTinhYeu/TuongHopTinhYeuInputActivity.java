package com.example.apptamlinh.ThanSHFeature.TuongHopTinhYeu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;

public class TuongHopTinhYeuInputActivity extends AppCompatActivity {
    private Button btnBack, btnContinue;
    private NumberPicker numPickerDay, numPickerMonth, numPickerYear, numPickerDay2, numPickerMonth2, numPickerYear2;
    private final String[] months = new String[]{
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
    };
    private EditText edtHoTen, edtHoTen2;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tuong_hop_tinh_yeu_input);
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
        edtHoTen = findViewById(R.id.edtHoTen);
        numPickerDay2 = findViewById(R.id.numPickerDay2);
        numPickerMonth2 = findViewById(R.id.numPickerMonth2);
        numPickerYear2 = findViewById(R.id.numPickerYear2);
        edtHoTen2 = findViewById(R.id.edtHoTen2);

        numPickerDay.setMinValue(1);
        numPickerDay.setMaxValue(31);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);
        numPickerYear.setMinValue(1900);
        numPickerYear.setMaxValue(2024);
        numPickerDay2.setMinValue(1);
        numPickerDay2.setMaxValue(31);
        numPickerMonth2.setMinValue(1);
        numPickerMonth2.setMaxValue(12);
        numPickerYear2.setMinValue(1900);
        numPickerYear2.setMaxValue(2024);

        numPickerMonth.setDisplayedValues(months);
        numPickerDay.setTextColor(Color.WHITE);
        numPickerMonth.setTextColor(Color.WHITE);
        numPickerYear.setTextColor(Color.WHITE);
        numPickerMonth2.setDisplayedValues(months);
        numPickerDay2.setTextColor(Color.WHITE);
        numPickerMonth2.setTextColor(Color.WHITE);
        numPickerYear2.setTextColor(Color.WHITE);
        //Default
        numPickerDay.setValue(29);
        numPickerMonth.setValue(12);
        numPickerYear.setValue(2003);
        numPickerDay2.setValue(29);
        numPickerMonth2.setValue(12);
        numPickerYear2.setValue(2003);
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
                String sName = String.valueOf(edtHoTen.getText());
                String sDate = "" + iDay + iMonth + iYear;

                int iYear2 = numPickerYear2.getValue();
                int iMonth2 = numPickerMonth2.getValue();
                int iDay2 = numPickerDay2.getValue();
                String sName2 = String.valueOf(edtHoTen2.getText());
                String sDate2 = "" + iDay2 + iMonth2 + iYear2;

                Intent intentDetail = new Intent(TuongHopTinhYeuInputActivity.this, TuongHopTinhYeuDetailActivity.class);
                intentDetail.putExtra("dataDate", sDate);
                intentDetail.putExtra("dataDate2", sDate2);
                startActivity(intentDetail);
            }
        });
    }
}