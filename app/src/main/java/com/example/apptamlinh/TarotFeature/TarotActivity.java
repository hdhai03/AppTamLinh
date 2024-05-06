package com.example.apptamlinh.TarotFeature;

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
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.apptamlinh.EnergyClass;
import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import me.relex.circleindicator.CircleIndicator;

public class TarotActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private Button btnBack_Tarot;
    private Button btnTarotHN_Tarot;
    private Button btnTarotTQ_Tarot, btnEnergy_Tarot;
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
                    btnEnergy_Tarot.setText(String.valueOf(userEnergy));
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
        setContentView(R.layout.activity_tarot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mViewPager = findViewById(R.id.view_pager_Tarot);
        mCircleIndicator = findViewById(R.id.circle_indicator_Tarot);

        SliderAdapter sliderAdapter = new SliderAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(sliderAdapter);
        mCircleIndicator.setViewPager(mViewPager);

        btnBack_Tarot = findViewById(R.id.btnBack_Tarot);
        btnTarotHN_Tarot = findViewById(R.id.btnTarotHN_Tarot);
        btnTarotTQ_Tarot = findViewById(R.id.btnTarotTQ_Tarot);
        btnEnergy_Tarot = findViewById(R.id.btnEnergy_Tarot);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_Tarot.setText(String.valueOf(userEnergy));
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

        btnBack_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnTarotHN_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 1) {
                    Intent intentTarotHN = new Intent(TarotActivity.this, TarotHangNgayActivity.class);
                    startActivity(intentTarotHN);
                    eg.updateEnergy(1, false);
                } else {
                    Toast.makeText(TarotActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTarotTQ_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEnergy >= 2) {
                    Intent intentTarotTQ = new Intent(TarotActivity.this, TarotTongQuanActivity.class);
                    startActivity(intentTarotTQ);
                    eg.updateEnergy(2, false);
                } else {
                    Toast.makeText(TarotActivity.this, "Năng lượng không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}