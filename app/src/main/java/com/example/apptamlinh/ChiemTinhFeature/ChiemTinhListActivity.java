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

public class ChiemTinhListActivity extends AppCompatActivity {
    private Button btnBack_ChiemTinhList;

    private Button btnMaKet;
    private Button btnBaoBinh;
    private Button btnSongNgu;
    private Button btnBachDuong;
    private Button btnKimNguu;
    private Button btnSongTu;
    private Button btnCuGiai;
    private Button btnSuTu;
    private Button btnXuNu;
    private Button btnThienBinh;
    private Button btnBoCap;
    private Button btnNhanMa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chiem_tinh_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_ChiemTinhList = findViewById(R.id.btnBack_ChiemTinhList);
        btnMaKet = findViewById(R.id.btnMaKet);
        btnBaoBinh = findViewById(R.id.btnBaoBinh);
        btnSongNgu = findViewById(R.id.btnSongNgu);
        btnBachDuong = findViewById(R.id.btnBachDuong);
        btnKimNguu = findViewById(R.id.btnKimNguu);
        btnSongTu = findViewById(R.id.btnSongTu);
        btnCuGiai = findViewById(R.id.btnCuGiai);
        btnSuTu = findViewById(R.id.btnSuTu);
        btnXuNu = findViewById(R.id.btnXuNu);
        btnThienBinh = findViewById(R.id.btnThienBinh);
        btnBoCap = findViewById(R.id.btnBoCap);
        btnNhanMa = findViewById(R.id.btnNhanMa);

        btnBack_ChiemTinhList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnMaKet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChiemTinhDetail = new Intent(ChiemTinhListActivity.this, ChiemTinhDetailActivity.class);

                intentChiemTinhDetail.putExtra("data", "0");

                startActivity(intentChiemTinhDetail);
            }
        });

        btnBaoBinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChiemTinhDetail = new Intent(ChiemTinhListActivity.this, ChiemTinhDetailActivity.class);

                intentChiemTinhDetail.putExtra("data", "1");

                startActivity(intentChiemTinhDetail);
            }
        });
    }
}