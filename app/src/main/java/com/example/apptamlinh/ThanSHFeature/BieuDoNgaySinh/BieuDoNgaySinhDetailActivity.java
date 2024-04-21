package com.example.apptamlinh.ThanSHFeature.BieuDoNgaySinh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;

public class BieuDoNgaySinhDetailActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView txtTongQuan_Nu_ChiemTinhDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bieu_do_ngay_sinh_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle extras = getIntent().getExtras();
        int receivedData = extras.getInt("data");
        String data = String.valueOf(receivedData);

        txtTongQuan_Nu_ChiemTinhDetail = findViewById(R.id.txtTongQuan_Nu_ChiemTinhDetail);
        txtTongQuan_Nu_ChiemTinhDetail.setText(data);
    }
}