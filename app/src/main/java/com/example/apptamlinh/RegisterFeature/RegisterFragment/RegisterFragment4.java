package com.example.apptamlinh.RegisterFeature.RegisterFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.apptamlinh.HomeActivity;
import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterFragment4 extends Fragment {
    private Button btnBack, btnContinue;

    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private String gioiTinh;

    public RegisterFragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register4, container, false);

        btnContinue = view.findViewById(R.id.btnContinue);

        RadioButton rdNu = view.findViewById(R.id.radioButtonNu);
        RadioButton rdNam = view.findViewById(R.id.radioButtonNam);
        RadioButton rdKhac = view.findViewById(R.id.radioButtonKhac);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("name");
            String email = bundle.getString("email");
            String password = bundle.getString("password");
            String ngaySinh = bundle.getString("ngaySinh");
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rdNu.isChecked()) {
                        gioiTinh = rdNu.getText().toString();
                    } else if (rdNam.isChecked()) {
                        gioiTinh = rdNam.getText().toString();
                    } else {
                        gioiTinh = rdKhac.getText().toString();
                    }
                    registerFirebase(email, password, name, ngaySinh, gioiTinh);
                }
            });

        }

        return view;
    }

    private void registerFirebase(String email, String password, String name, String ngaySinh, String gioiTinh) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng ký thành công
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();

                            Map<String, Object> newData = new HashMap<>();
                            newData.put("userName", name);
                            newData.put("userNgaySinh", ngaySinh);
                            newData.put("userGioiTinh", gioiTinh);
                            newData.put("userBio", "Bio");
                            newData.put("userEnergy", 5);
                            newData.put("userInvite", false);

                            DocumentReference userRef = db.collection("users").document(userId);
                            userRef.set(newData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            saveLoginLocal(email, FirebaseAuth.getInstance().getCurrentUser().getUid());
                                            Intent intentHome = new Intent(getContext(), HomeActivity.class);
                                            startActivity(intentHome);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(), "Lỗi khi lưu dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(getContext(), "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveLoginLocal(String email, String uid) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("login_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("uid", uid);
        editor.apply();
    }
}