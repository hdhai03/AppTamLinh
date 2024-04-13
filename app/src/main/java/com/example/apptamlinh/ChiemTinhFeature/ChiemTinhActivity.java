package com.example.apptamlinh.ChiemTinhFeature;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

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

        // Đọc nội dung của tệp JSON
        String json = loadJSONFromAsset("dataChiemTinh.JSON");


        Gson gson = new Gson();
        ChiemTinhModel chiemtinh = gson.fromJson(json, ChiemTinhModel.class);
        for (ChiemTinhModel.Constellation constellation : chiemtinh.getConstellations()) {

            if ("Bạch Dương".equals(constellation.getTen())) {

                Log.d("Constellation", "Tên: " + constellation.getTen());
                Log.d("Constellation", "Nam:");
                printAttributes(constellation.getNam());
                Log.d("Constellation", "Nữ:");
                printAttributes(constellation.getNu());
            }
        }
    }


    private void printAttributes(ChiemTinhModel.GenderAttributes genderAttributes) {
        Log.d("Constellation", "Tổng quan: " + genderAttributes.getTongquan());
        Log.d("Constellation", "Tính cách: " + genderAttributes.getTinhcach());
        Log.d("Constellation", "Điểm mạnh: " + genderAttributes.getDiemmanh());
        Log.d("Constellation", "Điểm yếu: " + genderAttributes.getDiemyeu());
        Log.d("Constellation", "Gia đình: " + genderAttributes.getGiadinh());
        Log.d("Constellation", "Tình yêu: " + genderAttributes.getTinhyeu());
        Log.d("Constellation", "Tình dục: " + genderAttributes.getTinhduc());
        Log.d("Constellation", "Sự nghiệp: " + genderAttributes.getSunghiep());
    }


    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close(); // Đóng luồng sau khi đã đọc xong dữ liệu
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    }
