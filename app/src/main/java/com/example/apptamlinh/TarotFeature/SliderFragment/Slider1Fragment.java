package com.example.apptamlinh.TarotFeature.SliderFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apptamlinh.R;
import com.example.apptamlinh.TarotFeature.SliderDetail.Slider1Detail_Tarot;
import com.example.apptamlinh.TarotFeature.TarotActivity;
import com.example.apptamlinh.TarotFeature.TarotHangNgayActivity;

public class Slider1Fragment extends Fragment {
    private Button btnSlider1_Tarot;
    public Slider1Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_slider1, container, false);

        btnSlider1_Tarot = mView.findViewById(R.id.btnSlider1_Tarot);
        btnSlider1_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSlider1 = new Intent(getActivity(), Slider1Detail_Tarot.class);
                startActivity(intentSlider1);
            }
        });
        return mView;
    }

}