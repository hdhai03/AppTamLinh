package com.example.apptamlinh.TarotFeature.SliderFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.apptamlinh.ProfileFeature.XemYNghia.XemYNghiaActivity;
import com.example.apptamlinh.R;

public class Slider2Fragment extends Fragment {
    private Button btnSlider2_Tarot;
    public Slider2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_slider2, container, false);

        btnSlider2_Tarot = mView.findViewById(R.id.btnSlider2_Tarot);
        btnSlider2_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSlider1 = new Intent(getActivity(), XemYNghiaActivity.class);
                startActivity(intentSlider1);
            }
        });
        return mView;
    }
}