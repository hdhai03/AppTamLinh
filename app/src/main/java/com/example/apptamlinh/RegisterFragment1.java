package com.example.apptamlinh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RegisterFragment1 extends Fragment {
    private Button btnTiepTuc;

    public RegisterFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_register1, container, false);

        btnTiepTuc = mView.findViewById(R.id.btnContinue);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterFragment2();
            }
        });

        return mView;
    }

    private void gotoRegisterFragment2() {
        RegisterFragment2 registerFragment2 = new RegisterFragment2();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, registerFragment2, "RegisterFragment2");
        fragmentTransaction.commit();
    }
}