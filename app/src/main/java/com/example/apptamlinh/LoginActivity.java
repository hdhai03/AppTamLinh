package com.example.apptamlinh;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class LoginActivity extends AppCompatActivity {
    private EditText edtTenDangNhap, edtMatKhau;
    private Button btnBack;
    private Button btnDangNhap;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);

        mAuth = FirebaseAuth.getInstance();

        btnDangNhap.setOnClickListener(v -> loginUser(edtTenDangNhap.getText().toString(), edtMatKhau.getText().toString()));


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        btnDangNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intentHome);
//            }
//        });

    }

    private void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            // Nếu email hoặc mật khẩu trống, hiển thị thông báo lên Toast
            Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin đăng nhập.", Toast.LENGTH_SHORT).show();
            return; // Rời khỏi phương thức loginUser mà không tiếp tục thực hiện đăng nhập
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                // Đăng nhập thành công
                                Log.d("LoginActivity", "signInWithEmail:success");
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                                Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intentHome);
                            } else {
                                // Đăng nhập thất bại
                                Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                                throw task.getException(); // Ném ngoại lệ để xử lý ở khối catch
                            }
                        } catch (Exception e) {
                            // Xử lý ngoại lệ
                            Log.e("LoginActivity", "signInWithEmail:failure", e);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại",
//                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại" + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}