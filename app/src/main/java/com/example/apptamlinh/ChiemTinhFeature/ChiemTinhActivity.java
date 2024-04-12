package com.example.apptamlinh.ChiemTinhFeature;

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

public class ChiemTinhActivity extends AppCompatActivity {
    private Button btnBack_ChiemTinh;
    private Button btnChiemTinhLaGi_ChiemTinh;
    private Button btnChiemTinhTQ_ChiemTinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chiem_tinh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_ChiemTinh = findViewById(R.id.btnBack_ChiemTinh);
        btnChiemTinhLaGi_ChiemTinh = findViewById(R.id.btnChiemTinhLaGi_ChiemTinh);
        btnChiemTinhTQ_ChiemTinh = findViewById(R.id.btnChiemTinhTQ_ChiemTinh);

        btnBack_ChiemTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnChiemTinhLaGi_ChiemTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetailChiemTinh = new Intent(ChiemTinhActivity.this, ChiemTinhLaGiActivity.class);
                startActivity(intentDetailChiemTinh);
            }
        });

        btnChiemTinhTQ_ChiemTinh.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intentChiemTinhTQ = new Intent(ChiemTinhActivity.this, ChiemTinhListActivity.class);
                startActivity(intentChiemTinhTQ);
            }
        });
    }
}