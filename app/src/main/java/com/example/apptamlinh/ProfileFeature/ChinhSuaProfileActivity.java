package com.example.apptamlinh.ProfileFeature;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChinhSuaProfileActivity extends AppCompatActivity {
    private Button btnBack, btnLuu;
    private EditText edtBio, edtName;
    private NumberPicker numPickerDay, numPickerMonth, numPickerYear;
    RadioButton rdNu, rdNam, rdKhac;
    private final String[] months = new String[]{
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
    };

    private FirebaseAuth mAuth;
    DocumentReference dbRef;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chinh_sua_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnLuu = findViewById(R.id.btnLuu);
        edtBio = findViewById(R.id.edtBio);
        edtName = findViewById(R.id.edtName);
        rdNu = findViewById(R.id.radioButtonNu);
        rdNam = findViewById(R.id.radioButtonNam);
        rdKhac = findViewById(R.id.radioButtonKhac);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        numPickerDay = findViewById(R.id.numPickerDay);
        numPickerMonth = findViewById(R.id.numPickerMonth);
        numPickerYear = findViewById(R.id.numPickerYear);
        numPickerDay.setMinValue(1);
        numPickerDay.setMaxValue(31);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);
        numPickerYear.setMinValue(1900);
        numPickerYear.setMaxValue(2024);
        numPickerMonth.setDisplayedValues(months);
        numPickerDay.setTextColor(Color.WHITE);
        numPickerMonth.setTextColor(Color.WHITE);
        numPickerYear.setTextColor(Color.WHITE);

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    String userName = documentSnapshot.getString("userName");
                    String userBio = documentSnapshot.getString("userBio");
                    String userNgaySinh = documentSnapshot.getString("userNgaySinh");
                    String userGioiTinh = documentSnapshot.getString("userGioiTinh");

                    // Xử lý ngày sinh
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = dateFormat.parse(userNgaySinh);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int iDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int iMonth = calendar.get(Calendar.MONTH) + 1;
                    int iYear = calendar.get(Calendar.YEAR);

                    // Cập nhật giao diện người dùng
                    numPickerDay.setValue(iDay);
                    numPickerMonth.setValue(iMonth);
                    numPickerYear.setValue(iYear);
                    edtBio.setText(userBio);
                    edtName.setText(userName);

                    if (userGioiTinh.equals("Nam")) {
                        rdNam.setChecked(true);
                    } else if (userGioiTinh.equals("Nu")) {
                        rdNu.setChecked(true);
                    } else {
                        rdKhac.setChecked(true);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iYear = numPickerYear.getValue();
                int iMonth = numPickerMonth.getValue();
                int iDay = numPickerDay.getValue();
                String ngaySinh = String.format("%02d/%02d/%04d", iDay, iMonth, iYear);
                String gioiTinh = "";
                if (rdNu.isChecked()) {
                    gioiTinh = rdNu.getText().toString();
                } else if (rdNam.isChecked()) {
                    gioiTinh = rdNam.getText().toString();
                } else {
                    gioiTinh = rdKhac.getText().toString();
                }
                String bio = String.valueOf(edtBio.getText());
                String name = String.valueOf(edtName.getText());
                updateUser(bio, name, ngaySinh, gioiTinh);
                Intent intent = new Intent(ChinhSuaProfileActivity.this, PersonalProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateUser(String bio, String name, String date, String gioiTinh) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("users").document(userId);

        Map<String, Object> updates = new HashMap<>();
        updates.put("userBio", bio);
        updates.put("userName", name);
        updates.put("userNgaySinh", date);
        updates.put("userGioiTinh", gioiTinh);

        userRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ChinhSuaProfileActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChinhSuaProfileActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}