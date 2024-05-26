package com.example.apptamlinh.ProfileFeature.BXH;

import android.content.Intent;
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

import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDetailActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView txtName, txtBio, txtThamGia, txtScore;

    DocumentReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack = findViewById(R.id.btnBack);
        txtName = findViewById(R.id.txtUserName);
        txtBio = findViewById(R.id.txtUserBio);
        txtThamGia = findViewById(R.id.txtThamGia);
        txtScore = findViewById(R.id.txtScore);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("selectedUserID")) {
            String selectedUserID = intent.getStringExtra("selectedUserID");
            dbRef = FirebaseFirestore.getInstance().collection("users").document(selectedUserID);
        }

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String userName = documentSnapshot.getString("userName");
                    String userBio = documentSnapshot.getString("userBio");
                    long userScore = documentSnapshot.getLong("userScore");
                    String userThamGia = documentSnapshot.getString("userThamGia");
                    txtName.setText(userName);
                    txtBio.setText(userBio);
                    txtScore.setText(String.valueOf(userScore));
                    txtThamGia.setText(userThamGia);
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
}