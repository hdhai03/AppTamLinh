package com.example.apptamlinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class PersonalProfileActivity extends AppCompatActivity {
    private Button btnDangXuat_Profile;
    private Button btnBack_Profile;
    private FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();

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
        // Chuyển sang màn hình đăng nhập hoặc thực hiện các thao tác khác sau khi đăng xuất
        // Ví dụ: Chuyển về màn hình đăng nhập
        startActivity(new Intent(PersonalProfileActivity.this, MainActivity.class));
        finish(); // Đóng Activity hiện tại
    }
}