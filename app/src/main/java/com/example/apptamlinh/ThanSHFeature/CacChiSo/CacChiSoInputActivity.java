package com.example.apptamlinh.ThanSHFeature.CacChiSo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CacChiSoInputActivity extends AppCompatActivity {
    private Button btnBack, btnContinue;
    private NumberPicker numPickerDay, numPickerMonth, numPickerYear;
    private final String[] months = new String[]{
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
    };
    private EditText edtHoTen;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cac_chi_so_input);
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
                String sName = String.valueOf(edtHoTen.getText());
                validateInput(iDay, iMonth, iYear, sName);
            }
        });
    }

    private void validateInput(int iDay, int iMonth, int iYear, String sName) {
        Pattern pattern = Pattern.compile("^[\\p{L} ]+$");

        String name = edtHoTen.getText().toString();
        String sTen = extractFirstName(sName);

        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            Intent intentDetail = new Intent(CacChiSoInputActivity.this, CacChiSoDetailActivity.class);
            intentDetail.putExtra("dataDay", iDay);
            intentDetail.putExtra("dataMonth", iMonth);
            intentDetail.putExtra("dataYear", iYear);
            intentDetail.putExtra("dataHoTen", sName.toUpperCase());
            intentDetail.putExtra("dataTen", sTen.toUpperCase());
            startActivity(intentDetail);
        } else {
            Toast.makeText(CacChiSoInputActivity.this, "Tên không được chứa số và ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
        }
    }

    private String extractFirstName(String fullName) {
        // Sử dụng phương thức trim() để loại bỏ khoảng trắng ở đầu và cuối chuỗi
        fullName = fullName.trim();

        // Tách tên từ chuỗi đầy đủ chứa họ và tên
        String[] parts = fullName.split("\\s+");

        // Trả về phần tử cuối cùng, giả sử đó là tên
        return parts[parts.length - 1];
    }
}