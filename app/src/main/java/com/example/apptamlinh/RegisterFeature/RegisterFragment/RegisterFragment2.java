package com.example.apptamlinh.RegisterFeature.RegisterFragment;

import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment2 extends Fragment {
    private Button btnContinue;
    private EditText edtName;
    public RegisterFragment2() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register2, container, false);
        btnContinue = view.findViewById(R.id.btnContinue);
        edtName = view.findViewById(R.id.edtTen);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String email = bundle.getString("email");
            String password = bundle.getString("password");

            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateInput(email, password);
                }
            });
        }


        return view;
    }

    private void loadFragment(String name, String email, String password) {
        RegisterFragment3 fragment = new RegisterFragment3();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("password", password);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null); // Thêm Fragment mới vào Backstack để có thể quay lại Fragment trước đó nếu cần
        fragmentTransaction.commit();
    }

    private void validateInput(String email, String password) {
        Pattern pattern = Pattern.compile("^[\\p{L} ]+$");

        String name = edtName.getText().toString();

        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            loadFragment(name, email, password); // Truyền email và password vào loadFragment
        } else {
            Toast.makeText(getContext(), "Tên không được chứa số và ký tự đặc biệt.", Toast.LENGTH_SHORT).show();
        }
    }


}