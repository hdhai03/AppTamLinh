package com.example.apptamlinh.TarotFeature;

import android.animation.ValueAnimator;
import android.os.Bundle;
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
    private TextView textView;
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
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", -25f, 25f); //Biên độ nè
        translationY.setDuration(1200); // Tốc độ nè
        translationY.setRepeatCount(ObjectAnimator.INFINITE);
        translationY.setRepeatMode(ObjectAnimator.REVERSE);
        translationY.start(); // Bắt đầu animation

        // Bắt sự kiện chạm vào bất kỳ nơi nào trên màn hình
        View tapAreaView = findViewById(R.id.main);
        tapAreaView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tapEnabled && event.getAction() == MotionEvent.ACTION_DOWN) {
                    tapEnabled = false; // Tắt khả năng chạm sau khi đã kích hoạt một lần

                    // Thực hiện animation và hiển thị textView
                    flipAndRaiseImageView();
                    animateTextView();
                    translationY.end();
                }
                return true;
            }
        });
    }

    private void flipAndRaiseImageView() {
        // Sử dụng ValueAnimator để kiểm soát giá trị rotationY
        ValueAnimator flipAnimator = ValueAnimator.ofFloat(180f, 0f);
        flipAnimator.setDuration(1000);

        flipAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                // Lấy giá trị của fraction
                float fraction = animation.getAnimatedFraction();

                // Thay đổi hình ảnh khi animation đã hoàn thành 50%
                if (fraction >= 0.5f && !imageChanged) {
                    imageView.setImageResource(R.drawable.p01);
                    imageChanged = true;
                }

                // Lấy giá trị rotationY hiện tại
                float rotationY = (float) animation.getAnimatedValue();
                imageView.setRotationY(rotationY);

                // Di chuyển imageView lên trên
                float translationYValue = -300f * fraction;
                imageView.setTranslationY(translationYValue);
            }
        });
        flipAnimator.start();
    }

    private void animateTextView() {
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(textView, "translationY", textView.getHeight(), 0);
        translateY.setDuration(1000);
        translateY.start();
    }
}