package com.example.apptamlinh.ThanSHFeature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;
import com.example.apptamlinh.ThanSHFeature.BieuDoNgaySinh.BieuDoNgaySinhInputActivity;
import com.example.apptamlinh.ThanSHFeature.BieuDoTongHop.BieuDoTongHopInputActivity;

public class ThanSoHocActivity extends AppCompatActivity {
    private Button btnBack_ThanSH;
    private Button btnBDNgaySinh_ThanSH, btnBDTongHop_ThanSH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_than_so_hoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_ThanSH = findViewById(R.id.btnBack_CongDong);
        btnBDNgaySinh_ThanSH = findViewById(R.id.btnBDNgaySinh_ThanSH);
        btnBDTongHop_ThanSH = findViewById(R.id.btnBDTongHop_ThanSH);

        btnBack_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnBDNgaySinh_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBDNgaySinh = new Intent(ThanSoHocActivity.this, BieuDoNgaySinhInputActivity.class);
                startActivity(intentBDNgaySinh);
            }
        });
        btnBDTongHop_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBDTongHop = new Intent(ThanSoHocActivity.this, BieuDoTongHopInputActivity.class);
                startActivity(intentBDTongHop);
            }
        });
    }
}