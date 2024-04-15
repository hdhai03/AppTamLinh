package com.example.apptamlinh.TarotFeature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhActivity;
import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhListActivity;
import com.example.apptamlinh.ChiemTinhFeature.ViewPagerAdapter;
import com.example.apptamlinh.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class TarotActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private Button btnBack_Tarot;
    private Button btnTarotHN_Tarot;
    private Button btnTarotTQ_Tarot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mViewPager = findViewById(R.id.view_pager_Tarot);
        mCircleIndicator = findViewById(R.id.circle_indicator_Tarot);

        SliderAdapter sliderAdapter = new SliderAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(sliderAdapter);
        mCircleIndicator.setViewPager(mViewPager);

        btnBack_Tarot = findViewById(R.id.btnBack_Tarot);
        btnTarotHN_Tarot = findViewById(R.id.btnTarotHN_Tarot);
        btnTarotTQ_Tarot = findViewById(R.id.btnTarotTQ_Tarot);

        btnBack_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnTarotHN_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTarotHN = new Intent(TarotActivity.this, TarotHangNgayActivity.class);
                startActivity(intentTarotHN);
            }
        });

        btnTarotTQ_Tarot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTarotTQ = new Intent(TarotActivity.this, TarotTongQuanActivity.class);
                startActivity(intentTarotTQ);
            }
        });

    }
}