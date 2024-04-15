package com.example.apptamlinh.TarotFeature;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.apptamlinh.TarotFeature.SliderFragment.Slider1Fragment;
import com.example.apptamlinh.TarotFeature.SliderFragment.Slider2Fragment;
import com.example.apptamlinh.TarotFeature.SliderFragment.Slider3Fragment;

public class SliderAdapter extends FragmentStatePagerAdapter {
    public SliderAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Slider1Fragment();
            case 1:
                return new Slider2Fragment();
            case 2:
                return new Slider3Fragment();
            default:
                return new Slider1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
