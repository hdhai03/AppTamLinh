package com.example.apptamlinh.ChiemTinhFeature;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChiemTinhActivity extends AppCompatActivity {
    private Button btnBack_ChiemTinh, btnChiemTinhLaGi_ChiemTinh, btnChiemTinhTQ_ChiemTinh, btnEnergy_ChiemTinh;
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
                    btnEnergy_ChiemTinh.setText(String.valueOf(userEnergy));
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
        setContentView(R.layout.activity_chiem_tinh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_ChiemTinh = findViewById(R.id.btnBack_ChiemTinh);
        btnChiemTinhLaGi_ChiemTinh = findViewById(R.id.btnChiemTinhLaGi_ChiemTinh);
        btnChiemTinhTQ_ChiemTinh = findViewById(R.id.btnChiemTinhTQ_ChiemTinh);
        btnEnergy_ChiemTinh = findViewById(R.id.btnEnergy_ChiemTinh);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_ChiemTinh.setText(String.valueOf(userEnergy));
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
                if (userEnergy >= 1) {
                    Intent intentChiemTinhTQ = new Intent(ChiemTinhActivity.this, ChiemTinhListActivity.class);
                    startActivity(intentChiemTinhTQ);
                    eg.updateEnergy(1, false);
                } else {
                    Toast.makeText(ChiemTinhActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
