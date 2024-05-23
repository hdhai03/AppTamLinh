package com.example.apptamlinh.ProfileFeature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TroGiupActivity extends AppCompatActivity {
    private Button btnBack, btnGui;
    private EditText edtNoiDung, edtTieuDe;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tro_giup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnGui = findViewById(R.id.btnGui);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        edtTieuDe = findViewById(R.id.edtTieuDe);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieuDe = edtTieuDe.getText().toString();
                String noiDung = edtNoiDung.getText().toString();
                addTroGiup(tieuDe, noiDung);
            }
        });
    }

    private void addTroGiup(String tieuDe, String noiDung) {
        String userId = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        long troGiupTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd:MM:yyyy", Locale.getDefault());
        String troGiupTime = sdf.format(new Date(troGiupTimeMillis));

        Map<String, Object> updates = new HashMap<>();
        updates.put("troGiupTieuDe", tieuDe);
        updates.put("troGiupNoiDung", noiDung);
        updates.put("troGiupUID", userId);
        updates.put("troGiupTime", troGiupTime);

        db.collection("trogiup")
                .add(updates)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(TroGiupActivity.this, "Gửi phản hồi thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TroGiupActivity.this, "Gửi phản hồi thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
