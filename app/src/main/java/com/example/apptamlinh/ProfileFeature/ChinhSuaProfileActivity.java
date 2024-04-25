package com.example.apptamlinh.ProfileFeature;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    DatabaseReference databaseRef;
    String userName, userBio, userNgaySinh, userGioiTinh;

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
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users");

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

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Lấy dữ liệu từ dataSnapshot
                    userName = userSnapshot.child("userName").getValue(String.class);
                    userBio = userSnapshot.child("userBio").getValue(String.class);
                    userNgaySinh = userSnapshot.child("userNgaySinh").getValue(String.class);
                    userGioiTinh = userSnapshot.child("userGioiTinh").getValue(String.class);

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
                Toast.makeText(ChinhSuaProfileActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateUser(String bio, String name, String date, String gioiTinh) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        userRef.child("userBio").setValue(bio);
        userRef.child("userName").setValue(name);
        userRef.child("userNgaySinh").setValue(date);
        userRef.child("userGioiTinh").setValue(gioiTinh);

    }
}