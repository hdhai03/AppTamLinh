package com.example.apptamlinh.ProfileFeature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.EnergyClass;
import com.example.apptamlinh.MainActivity;
import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PersonalProfileActivity extends AppCompatActivity {
    private Button btnBack_Profile, btnEnergy_Profile, btnDangXuat_Profile, btnChinhSua_Profile, btnMoiBanBe_Profile, btnNhapMa_Profile;
    TextView txtUserName, txtUserBio;
    private FirebaseAuth mAuth;
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
                    String userName = documentSnapshot.getString("userName");
                    String userBio = documentSnapshot.getString("userBio");
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    txtUserBio.setText(userBio);
                    txtUserName.setText(userName);
                    btnEnergy_Profile.setText(String.valueOf(userEnergy));
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
        setContentView(R.layout.activity_personal_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_Profile = findViewById(R.id.btnBack_Profile);
        btnDangXuat_Profile = findViewById(R.id.btnDangXuat_Profile);
        txtUserBio = findViewById(R.id.txtUserBio);
        txtUserName = findViewById(R.id.txtUserName);
        btnChinhSua_Profile = findViewById(R.id.btnChinhSua_Profile);
        btnMoiBanBe_Profile = findViewById(R.id.btnMoiBanBe_Profile);
        btnEnergy_Profile = findViewById(R.id.btnEnergy_Profile);
        btnNhapMa_Profile = findViewById(R.id.btnNhapMa_Profile);

        mAuth = FirebaseAuth.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    String userName = documentSnapshot.getString("userName");
                    String userBio = documentSnapshot.getString("userBio");
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    txtUserBio.setText(userBio);
                    txtUserName.setText(userName);
                    btnEnergy_Profile.setText(String.valueOf(userEnergy));
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
        btnChinhSua_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChinhSua = new Intent(PersonalProfileActivity.this, ChinhSuaProfileActivity.class);
                startActivity(intentChinhSua);
            }
        });

        btnMoiBanBe_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMoiBanBe = new Intent(PersonalProfileActivity.this, MoiBanBeActivity.class);
                startActivity(intentMoiBanBe);
            }
        });
        btnNhapMa_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNhapMa = new Intent(PersonalProfileActivity.this, NhapMaActivity.class);
                startActivity(intentNhapMa);
            }
        });
        btnDangXuat_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        btnBack_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void logoutUser() {
        mAuth.signOut();
        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        startActivity(new Intent(PersonalProfileActivity.this, MainActivity.class));
        finish();
    }
}