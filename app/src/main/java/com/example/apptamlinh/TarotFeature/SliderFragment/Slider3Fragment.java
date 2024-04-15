package com.example.apptamlinh.TarotFeature.SliderFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apptamlinh.R;
import com.example.apptamlinh.TarotFeature.SliderDetail.Slider3Detail_Tarot;

public class Slider3Fragment extends Fragment {
    private Button btnSlider3_Tarot;
    public Slider3Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_slider3, container, false);
        btnSlider3_Tarot = mView.findViewById(R.id.btnSlider3_Tarot);
        btnSlider3_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSlider3 = new Intent(getActivity(), Slider3Detail_Tarot.class);
                startActivity(intentSlider3);
            }
        });
        return mView;
    }
}