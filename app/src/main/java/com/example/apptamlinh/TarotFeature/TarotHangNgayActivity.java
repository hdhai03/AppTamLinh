package com.example.apptamlinh.TarotFeature;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TarotHangNgayActivity extends AppCompatActivity {
    private Button btnBack_TarotHN;
    private ImageView imageView;
    private TextView txtTenBai;
    private TextView txtNoiDung;
    private TextView textView1;
    private TextView textView2;
    private ScrollView scroll_view;
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
        scroll_view = findViewById(R.id.scroll_view);

        Random random = new Random();
        int idTarot = random.nextInt(78);

        TarotHNModel dataTarot = new TarotHNModel();
        dataTarot = loadJsonTarot(idTarot);
        String imgUrl = dataTarot.getImg();
        txtNoiDung.setText(dataTarot.getMeaning());
        txtTenBai.setText("Lá bài của bạn là \n " + dataTarot.getName());

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
                    animationView(imgUrl);
                    translationY.end();
                }
                return true;
            }
        });
    }

    private void animationView(String imgUrl) {
        // Sử dụng ValueAnimator để kiểm soát giá trị rotationY
        ValueAnimator animationView = ValueAnimator.ofFloat(180f, 0f);
        animationView.setDuration(1000);

        animationView.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (fraction >= 0.5f && !imageChanged) {
                    // Set anh
                    Glide.with(TarotHangNgayActivity.this)
                            .load(Uri.parse(imgUrl))
                            .into(imageView);
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
                scroll_view.setVisibility(View.VISIBLE);
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

    private TarotHNModel loadJsonTarot(int i) {
        TarotHNModel tarotData = new TarotHNModel();
        try {
            InputStream inputStream = getAssets().open("dataTarot.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject tarot = jsonArray.getJSONObject(i);

            tarotData.setName(tarot.getString("name"));
            tarotData.setNumber(tarot.getString("number"));
            tarotData.setArcana(tarot.getString("arcana"));
            tarotData.setSuit(tarot.getString("suit"));
            tarotData.setImg(tarot.getString("img"));
            tarotData.setMeaning(tarot.getString("meaning"));
        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return tarotData;
    }
}