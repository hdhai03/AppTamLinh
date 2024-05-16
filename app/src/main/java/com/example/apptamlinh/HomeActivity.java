package com.example.apptamlinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhActivity;
import com.example.apptamlinh.CongDongFeature.CongDongActivity;
import com.example.apptamlinh.ProfileFeature.PersonalProfileActivity;
import com.example.apptamlinh.TarotFeature.TarotActivity;
import com.example.apptamlinh.ThanSHFeature.ThanSoHocActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {
    private Button btnProfile_Home;
    private Button btnTarot_Home;
    private Button btnTarot2_Home;
    private Button btnChiemTinh_Home;
    private Button btnChiemTinh2_Home;
    private Button btnThanSH_Home;
    private Button btnThanSH2_Home;
    private Button btnCongDong_Home, btnEnergy_Home;
    DocumentReference dbRef;
    Long userEnergy = null;

    private final int[] buttonIds = {
            R.id.btnCard1, R.id.btnCard2, R.id.btnCard3, R.id.btnCard4,
            R.id.btnCard5, R.id.btnCard6, R.id.btnCard7, R.id.btnCard8,
            R.id.btnCard9, R.id.btnCard10, R.id.btnCard11, R.id.btnCard12
    };
    private final Button[] buttons = new Button[buttonIds.length];

    // Ánh xạ các nút Button từ id và lưu vào mảng buttons
    private void mapButtons() {
        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = findViewById(buttonIds[i]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_Home.setText(String.valueOf(userEnergy));
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
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnProfile_Home = findViewById(R.id.btnProfile_Home);
        btnTarot_Home = findViewById(R.id.btnTarot_Home);
        btnTarot2_Home = findViewById(R.id.btnTarot2_Home);
        btnChiemTinh_Home = findViewById(R.id.btnChiemTinh_Home);
        btnChiemTinh2_Home = findViewById(R.id.btnChiemTinh2_Home);
        btnThanSH_Home = findViewById(R.id.btnThanSH_Home);
        btnThanSH2_Home = findViewById(R.id.btnThanSH2_Home);
        btnCongDong_Home = findViewById(R.id.btnCongDong_Home);
        btnEnergy_Home = findViewById(R.id.btnEnergy_Home);


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_Home.setText(String.valueOf(userEnergy));
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
        btnProfile_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(HomeActivity.this, PersonalProfileActivity.class);
                startActivity(intentProfile);
            }
        });

        btnTarot_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTarot = new Intent(HomeActivity.this, TarotActivity.class);
                startActivity(intentTarot);
            }
        });

        btnTarot2_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTarot = new Intent(HomeActivity.this, TarotActivity.class);
                startActivity(intentTarot);
            }
        });

        btnChiemTinh_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChiemTinh = new Intent(HomeActivity.this, ChiemTinhActivity.class);
                startActivity(intentChiemTinh);
            }
        });

        btnChiemTinh2_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChiemTinh = new Intent(HomeActivity.this, ChiemTinhActivity.class);
                startActivity(intentChiemTinh);
            }
        });

        btnThanSH_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThanSH = new Intent(HomeActivity.this, ThanSoHocActivity.class);
                startActivity(intentThanSH);
            }
        });

        btnThanSH2_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThanSH = new Intent(HomeActivity.this, ThanSoHocActivity.class);
                startActivity(intentThanSH);
            }
        });

        btnCongDong_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCongDong = new Intent(HomeActivity.this, CongDongActivity.class);
                startActivity(intentCongDong);
            }
        });

    }
}