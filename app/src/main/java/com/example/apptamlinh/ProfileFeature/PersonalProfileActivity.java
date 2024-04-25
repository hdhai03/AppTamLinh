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

import com.example.apptamlinh.MainActivity;
import com.example.apptamlinh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalProfileActivity extends AppCompatActivity {
    private Button btnBack_Profile, btnDangXuat_Profile, btnChinhSua_Profile;
    TextView txtUserName, txtUserBio;
    private FirebaseAuth mAuth;
    DatabaseReference databaseRef;
    String userName, userBio;

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

        mAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users");

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Lấy dữ liệu từ dataSnapshot
                    userName = userSnapshot.child("userName").getValue(String.class);
                    userBio = userSnapshot.child("userBio").getValue(String.class);
                    txtUserBio.setText(userBio);
                    txtUserName.setText(userName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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