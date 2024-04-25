package com.example.apptamlinh.RegisterFeature.RegisterFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apptamlinh.R;

import java.util.regex.Pattern;

public class RegisterFragment1 extends Fragment {
    private Button btnContinue, btnBack;
    private EditText edtEmail, edtMatKhau;
    public RegisterFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register1, container, false);

        btnContinue = view.findViewById(R.id.btnContinue);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMatKhau = view.findViewById(R.id.edtMatKhau);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
        return view;
    }

    private void loadFragment(String email, String password) {
        RegisterFragment2 fragment = new RegisterFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        bundle.putString("password", password);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void validateInput() {
        String email = edtEmail.getText().toString().trim();
        String password = edtMatKhau.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Vui lòng nhập địa chỉ email.", Toast.LENGTH_SHORT).show();
            // Dừng phương thức nếu có lỗi
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Địa chỉ email không hợp lệ.", Toast.LENGTH_SHORT).show();
            // Dừng phương thức nếu có lỗi
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Vui lòng nhập mật khẩu.", Toast.LENGTH_SHORT).show();
            // Dừng phương thức nếu có lỗi
        } else if (password.length() < 8) {
            Toast.makeText(getContext(), "Mật khẩu phải chứa ít nhất 8 ký tự.", Toast.LENGTH_SHORT).show();
            // Dừng phương thức nếu có lỗi
        } else if (!Pattern.matches("[a-zA-Z0-9]+", password)) {
            Toast.makeText(getContext(), "Mật khẩu không được chứa ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
            // Dừng phương thức nếu có lỗi
        } else {
            loadFragment(email, password);
        }
    }
}