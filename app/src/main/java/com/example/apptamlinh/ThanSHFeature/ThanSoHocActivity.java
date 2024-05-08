package com.example.apptamlinh.ThanSHFeature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.EnergyClass;
import com.example.apptamlinh.R;
import com.example.apptamlinh.ThanSHFeature.BieuDoNgaySinh.BieuDoNgaySinhInputActivity;
import com.example.apptamlinh.ThanSHFeature.BieuDoTongHop.BieuDoTongHopInputActivity;
import com.example.apptamlinh.ThanSHFeature.CacChiSo.CacChiSoInputActivity;
import com.example.apptamlinh.ThanSHFeature.ChuKyVanSo.ChuKyVanSoInputActivity;
import com.example.apptamlinh.ThanSHFeature.LuanGiaiSDT.LuanGiaiSDTInputActivity;
import com.example.apptamlinh.ThanSHFeature.TuongHopTinhYeu.TuongHopTinhYeuInputActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ThanSoHocActivity extends AppCompatActivity {
    private Button btnBack_ThanSH;
    private Button btnBDNgaySinh_ThanSH, btnBDTongHop_ThanSH, btnChuKyVS_ThanSH, btnTuongHopSDT_ThanSH,
            btnTuongHopTY_ThanSH, btnCacChiSo_ThanSH, btnEnergy_ThanSH;

    DocumentReference dbRef;

    EnergyClass eg = new EnergyClass();

    Long userEnergy = null;

    @Override
    protected void onResume() {
        super.onResume();
        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_ThanSH.setText(String.valueOf(userEnergy));
                } else {
                    // Xử lý khi không có dữ liệu tồn tại
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý khi đọc dữ liệu thất bại
            }
        });
    }

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
        btnChuKyVS_ThanSH = findViewById(R.id.btnChuKyVS_ThanSH);
        btnTuongHopSDT_ThanSH = findViewById(R.id.btnTuongHopSDT_ThanSH);
        btnTuongHopTY_ThanSH = findViewById(R.id.btnTuongHopTY_ThanSH);
        btnCacChiSo_ThanSH = findViewById(R.id.btnCacChiSo_ThanSH);
        btnEnergy_ThanSH = findViewById(R.id.btnEnergy_ThanSH);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_ThanSH.setText(String.valueOf(userEnergy));
                } else {
                    // Xử lý khi không có dữ liệu tồn tại
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý khi đọc dữ liệu thất bại
            }
        });

        btnBack_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnBDNgaySinh_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 3) {
                    Intent intentBDNgaySinh = new Intent(ThanSoHocActivity.this, BieuDoNgaySinhInputActivity.class);
                    startActivity(intentBDNgaySinh);
                    eg.updateEnergy(3, false);
                } else {
                    Toast.makeText(ThanSoHocActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBDTongHop_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 3) {
                    Intent intentBDTongHop = new Intent(ThanSoHocActivity.this, BieuDoTongHopInputActivity.class);
                    startActivity(intentBDTongHop);
                    eg.updateEnergy(3, false);
                } else {
                    Toast.makeText(ThanSoHocActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChuKyVS_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 2) {
                    Intent intentCKVS = new Intent(ThanSoHocActivity.this, ChuKyVanSoInputActivity.class);
                    startActivity(intentCKVS);
                    eg.updateEnergy(2, false);
                } else {
                    Toast.makeText(ThanSoHocActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTuongHopTY_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 1) {
                    Intent intentTHTY = new Intent(ThanSoHocActivity.this, TuongHopTinhYeuInputActivity.class);
                    startActivity(intentTHTY);
                    eg.updateEnergy(1, false);
                } else {
                    Toast.makeText(ThanSoHocActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTuongHopSDT_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 1) {
                    Intent intentSDT = new Intent(ThanSoHocActivity.this, LuanGiaiSDTInputActivity.class);
                    startActivity(intentSDT);
                    eg.updateEnergy(1, false);
                } else {
                    Toast.makeText(ThanSoHocActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCacChiSo_ThanSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 10) {
                    Intent intentCacChiSo = new Intent(ThanSoHocActivity.this, CacChiSoInputActivity.class);
                    startActivity(intentCacChiSo);
                    eg.updateEnergy(10, false);
                } else {
                    Toast.makeText(ThanSoHocActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}