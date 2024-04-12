package com.example.apptamlinh.ChiemTinhFeature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment.NamFragment;
import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment.NuFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NamFragment();
            case 1:
                return new NuFragment();

            default:
                return new NamFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Nam";
                break;
            case 1:
                title = "Nữ";
                break;
        }
        return title;
    }
}
