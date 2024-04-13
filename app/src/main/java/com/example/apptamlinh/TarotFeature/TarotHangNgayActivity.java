package com.example.apptamlinh.TarotFeature;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.apptamlinh.R;

public class TarotHangNgayActivity extends AppCompatActivity {

    private Button btnBack_TarotHN;
    private ImageView imageView;
    private TextView textView;
    private boolean isFlipped = false;
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
                // Kiểm tra sự kiện chạm
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Thực hiện animation để flip imageView và đẩy nó lên
                    if (!isFlipped) {
                        flipAndRaiseImageView();
                        isFlipped = true;
                    }
                    // Hiển thị textView từ dưới lên
                    animateTextView();
                }
                translationY.end();
                return true;
            }
        });
    }

    private void flipAndRaiseImageView() {
        ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 180f);
        flipAnimator.setDuration(1000);

        flipAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imageView.setImageResource(R.drawable.p01); // Thay đổi hình ảnh sau khi flip
                // Di chuyển imageView lên trên
                ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", 0f, -200f);
                translationY.setDuration(1000);
                translationY.start();
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });
        flipAnimator.start();
    }

    private void animateTextView() {
        textView.setVisibility(View.VISIBLE);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(textView, "translationY", textView.getHeight(), 0);
        translateY.setDuration(1000);
        translateY.start();
    }
}