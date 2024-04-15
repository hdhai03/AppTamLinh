package com.example.apptamlinh.TarotFeature;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.apptamlinh.R;

public class TarotHangNgayActivity extends AppCompatActivity {

    private Button btnBack_TarotHN;
    private ImageView imageView;
    private TextView txtTenBai;
    private TextView txtNoiDung;
    private TextView textView1;
    private TextView textView2;
    private boolean imageChanged = false;
    private boolean tapEnabled = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarot_hang_ngay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_TarotHN = findViewById(R.id.btnBack_TarotHN);
        btnBack_TarotHN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageView = findViewById(R.id.imgView);
        txtTenBai = findViewById(R.id.txtTenBai);
        txtNoiDung = findViewById(R.id.txtNoiDung);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", -25f, 25f); //Biên độ nè
        translationY.setDuration(1200); // Tốc độ nè
        translationY.setRepeatCount(ObjectAnimator.INFINITE);
        translationY.setRepeatMode(ObjectAnimator.REVERSE);
        translationY.start();

        View tapAreaView = findViewById(R.id.main);
        tapAreaView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tapEnabled && event.getAction() == MotionEvent.ACTION_DOWN) {
                    tapEnabled = false;
                    animationView();
                    translationY.end();
                }
                return true;
            }
        });
    }

    private void animationView() {
        // Sử dụng ValueAnimator để kiểm soát giá trị rotationY
        ValueAnimator animationView = ValueAnimator.ofFloat(180f, 0f);
        animationView.setDuration(1000);

        animationView.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();

                if (fraction >= 0.5f && !imageChanged) {
                    imageView.setImageResource(R.drawable.p01);  //Set ảnh
                    imageChanged = true;
                }

                float rotationY = (float) animation.getAnimatedValue();
                imageView.setRotationY(rotationY);

                float translationYValue = -400f * fraction;
                imageView.setTranslationY(translationYValue);

                // Thu nhỏ hình ảnh lại
                float scaleValue = 1.0f - fraction * 0.2f;
                imageView.setScaleX(scaleValue);
                imageView.setScaleY(scaleValue);
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
            }
        });
        animationView.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtTenBai.setVisibility(View.VISIBLE);
                        txtNoiDung.setVisibility(View.VISIBLE);
                    }
                }, 1000);

                ObjectAnimator txtAnimation = ObjectAnimator.ofFloat(txtTenBai, "translationY", 30, 0);
                txtAnimation.setDuration(1000);
                txtAnimation.start();

                ObjectAnimator txtAnimation2 = ObjectAnimator.ofFloat(txtNoiDung, "translationY", 30, 0);
                txtAnimation2.setDuration(1000);
                txtAnimation2.start();
            }
        });
        animationView.start();
    }
}