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

import com.example.apptamlinh.EnergyClass;
import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class NhapMaActivity extends AppCompatActivity {
    EditText edtCode;
    Button btnBack, btnNhanMa;
    EnergyClass eg = new EnergyClass();
    DocumentReference dbRef, dbRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhap_ma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtCode = findViewById(R.id.edtCode);
        btnBack = findViewById(R.id.btnBack);
        btnNhanMa = findViewById(R.id.btnNhanMa);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnNhanMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sCode = String.valueOf(edtCode.getText());
                dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            boolean userInvite = documentSnapshot.getBoolean("userInvite");
                            if (!userInvite) {
                                FirebaseFirestore.getInstance().collection("users") //
                                        .whereEqualTo(FieldPath.documentId(), sCode)
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                // Kiểm tra xem có tài liệu nào trên Firestore có trường dữ liệu trùng khớp với sCode không
                                                if (!queryDocumentSnapshots.isEmpty()) {
                                                    eg.updateEnergy(1, true);
                                                    Map<String, Object> updates = new HashMap<>();
                                                    updates.put("userInvite", true);
                                                    dbRef.update(updates)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                }
                                                            });
                                                    dbRef2 = FirebaseFirestore.getInstance().collection("users").document(sCode);
                                                    Map<String, Object> updates2 = new HashMap<>();
                                                    updates2.put("userEnergy", FieldValue.increment(3)); // Sử dụng FieldValue.increment() để tăng giá trị của trường "userEnergy" thêm 3
                                                    dbRef2.update(updates2)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    // Xử lý khi cập nhật thành công
                                                                    Toast.makeText(NhapMaActivity.this, "Cập nhật userEnergy thành công", Toast.LENGTH_SHORT).show();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    // Xử lý khi cập nhật thất bại
                                                                    Toast.makeText(NhapMaActivity.this, "Lỗi khi cập nhật userEnergy: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                    Toast.makeText(NhapMaActivity.this, "Nhập thành công", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(NhapMaActivity.this, "Mã không chính xác.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Xử lý khi truy vấn thất bại
                                            }
                                        });
                            } else {
                                Toast.makeText(NhapMaActivity.this, "Mỗi tài khoản chỉ được nhập mã giới thiệu 1 lần.", Toast.LENGTH_SHORT).show();
                            }
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
        });
    }
}