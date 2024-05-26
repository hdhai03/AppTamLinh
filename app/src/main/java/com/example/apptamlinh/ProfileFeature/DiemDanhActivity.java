package com.example.apptamlinh.ProfileFeature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.util.Calendar;
import java.util.List;

public class DiemDanhActivity extends AppCompatActivity {
    private boolean currentUserAttendanceStatus = false;
    List<Boolean> userDiemDanh;
    private Button btnBack;
    TextView txtEnergy;
    private FirebaseAuth mAuth;
    private Button btnDiemDanh, btnDiemDanh1, btnDiemDanh2, btnDiemDanh3, btnDiemDanh4, btnDiemDanh5,
            btnDiemDanh6, btnDiemDanh7, btnMoiBanBe_diemDanh;
    DocumentReference dbRef;
    EnergyClass eg = new EnergyClass();

    Long userEnergy = null;

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diem_danh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        txtEnergy = findViewById(R.id.txtEnergy);
        btnDiemDanh1 = findViewById(R.id.btnDiemDanh1);
        btnDiemDanh2 = findViewById(R.id.btnDiemDanh2);
        btnDiemDanh3 = findViewById(R.id.btnDiemDanh3);
        btnDiemDanh4 = findViewById(R.id.btnDiemDanh4);
        btnDiemDanh5 = findViewById(R.id.btnDiemDanh5);
        btnDiemDanh6 = findViewById(R.id.btnDiemDanh6);
        btnDiemDanh7 = findViewById(R.id.btnDiemDanh7);
        btnDiemDanh = findViewById(R.id.btnDiemDanh);
        btnMoiBanBe_diemDanh = findViewById(R.id.btnMoiBanBe_diemDanh);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);
        loadUserData();
        btnMoiBanBe_diemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMoi = new Intent(DiemDanhActivity.this, MoiBanBeActivity.class);
                startActivity(intentMoi);
            }
        });

    }

    private void loadUserData() {
        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    txtEnergy.setText(String.valueOf(userEnergy));

                    // Lấy dữ liệu điểm danh
                    userDiemDanh = (List<Boolean>) documentSnapshot.get("userDiemDanh");
                    if (userDiemDanh != null && userDiemDanh.size() == 7) {
                        updateAttendanceButtons(userDiemDanh);
                    }
                } else {
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    private void updateAttendanceButtons(List<Boolean> userDiemDanh) {
        Button[] buttons = {btnDiemDanh1, btnDiemDanh2, btnDiemDanh3, btnDiemDanh4, btnDiemDanh5, btnDiemDanh6, btnDiemDanh7};
        int[] iconsNormal = {R.drawable.icon_diem_danh_0, R.drawable.icon_diem_danh_0, R.drawable.icon_diem_danh_0, R.drawable.icon_diem_danh_0, R.drawable.icon_diem_danh_0, R.drawable.icon_diem_danh_0, R.drawable.icon_diem_danh_0};
        int[] iconsAttended = {R.drawable.icon_diem_danh_1, R.drawable.icon_diem_danh_1, R.drawable.icon_diem_danh_1, R.drawable.icon_diem_danh_1, R.drawable.icon_diem_danh_1, R.drawable.icon_diem_danh_1, R.drawable.icon_diem_danh_1};

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < userDiemDanh.size(); i++) {
            if (userDiemDanh.get(i)) {
                buttons[i].setBackgroundResource(iconsAttended[i]);
            } else {
                buttons[i].setBackgroundResource(iconsNormal[i]);
            }
        }

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                if (userDiemDanh.get(1)) {
                    btnDiemDanh2.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh2.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
            case Calendar.TUESDAY:
                if (userDiemDanh.get(2)) {
                    btnDiemDanh3.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh3.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
            case Calendar.WEDNESDAY:
                if (userDiemDanh.get(3)) {
                    btnDiemDanh4.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh4.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
            case Calendar.THURSDAY:
                if (userDiemDanh.get(4)) {
                    btnDiemDanh5.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh5.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
            case Calendar.FRIDAY:
                if (userDiemDanh.get(5)) {
                    btnDiemDanh6.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh6.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
            case Calendar.SATURDAY:
                if (userDiemDanh.get(6)) {
                    btnDiemDanh7.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh7.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
            case Calendar.SUNDAY:
                if (userDiemDanh.get(0)) {
                    btnDiemDanh1.setBackgroundResource(R.drawable.icon_diem_danh_3);
                } else {
                    btnDiemDanh1.setBackgroundResource(R.drawable.icon_diem_danh_2);
                }
                break;
        }

        setupAttendanceButton(btnDiemDanh, dayOfWeek);
    }

    private void setupAttendanceButton(Button button, int dayOfWeek) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getId() == R.id.btnDiemDanh && !currentUserAttendanceStatus) {
                    int index = (dayOfWeek - 1) % 7;
                    boolean attendanceStatus = userDiemDanh.get(index);
                    if (!attendanceStatus) {
                        userDiemDanh.set(index, true);
                        dbRef.update("userDiemDanh", userDiemDanh).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DiemDanhActivity.this, "Điểm danh thành công", Toast.LENGTH_SHORT).show();
                                currentUserAttendanceStatus = true;
                                eg.updateEnergy(1, true);
                                updateAttendanceButtons(userDiemDanh);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xử lý khi cập nhật thất bại
                            }
                        });
                    } else {
                        Toast.makeText(DiemDanhActivity.this, "Bạn đã điểm danh cho ngày này rồi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
