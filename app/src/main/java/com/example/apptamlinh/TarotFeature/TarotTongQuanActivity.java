package com.example.apptamlinh.TarotFeature;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.Random;

public class TarotTongQuanActivity extends AppCompatActivity {
    private class TapAreaState {
        boolean imageChanged = false;
        boolean tapEnabled = true;
    }

    private Button btnBack_TarotTQ;
    private ImageView imgView;
    private ImageView imgView2;
    private ImageView imgView3;
    private ImageView imgView4;
    private TextView textView5;

    private int touchCount = 0;

    private TapAreaState tapAreaState1 = new TapAreaState();
    private TapAreaState tapAreaState2 = new TapAreaState();
    private TapAreaState tapAreaState3 = new TapAreaState();
    private TapAreaState tapAreaState4 = new TapAreaState();
    private ArrayList<TarotTQModel> tarotModels = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarot_tong_quan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack_TarotTQ = findViewById(R.id.btnBack_TarotTQ);
        btnBack_TarotTQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgView = findViewById(R.id.imgView);
        imgView2 = findViewById(R.id.imgView2);
        imgView3 = findViewById(R.id.imgView3);
        imgView4 = findViewById(R.id.imgView4);
        textView5 = findViewById(R.id.textView5);

        Random random = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 4) {
            int idTarot = random.nextInt(78);
            if (!randomNumbers.contains(idTarot)) {
                randomNumbers.add(idTarot);
            }
        }

        for (int idTarot : randomNumbers) {
            TarotTQModel dataTarot = loadJsonTarot(idTarot);
            tarotModels.add(dataTarot);
        }


        Bundle bundleTarot = new Bundle();
        bundleTarot.putString("imgCongViec", tarotModels.get(0).getImg());
        bundleTarot.putString("imgTinhYeu", tarotModels.get(1).getImg());
        bundleTarot.putString("imgTaiChinh", tarotModels.get(2).getImg());
        bundleTarot.putString("imgSucKhoe", tarotModels.get(3).getImg());
        bundleTarot.putString("nameCongViec", tarotModels.get(0).getName());
        bundleTarot.putString("nameTinhYeu", tarotModels.get(1).getName());
        bundleTarot.putString("nameTaiChinh", tarotModels.get(2).getName());
        bundleTarot.putString("nameSucKhoe", tarotModels.get(3).getName());
        bundleTarot.putString("txtCongViec", tarotModels.get(0).getCongviec());
        bundleTarot.putString("txtTinhYeu", tarotModels.get(1).getTinhyeu());
        bundleTarot.putString("txtTaiChinh", tarotModels.get(2).getTaichinh());
        bundleTarot.putString("txtSucKhoe", tarotModels.get(3).getSuckhoe());



        setupTapAreaView(imgView, tapAreaState1, tarotModels.get(0).getImg(), bundleTarot);
        setupTapAreaView(imgView2, tapAreaState2, tarotModels.get(1).getImg() , bundleTarot);
        setupTapAreaView(imgView3, tapAreaState3, tarotModels.get(2).getImg(), bundleTarot);
        setupTapAreaView(imgView4, tapAreaState4, tarotModels.get(3).getImg(), bundleTarot);

        floatAnimation(textView5);

    }

    private void floatAnimation(View view){
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", -10f, 10f); //Biên độ nè
        translationY.setDuration(1200); // Tốc độ nè
        translationY.setRepeatCount(ObjectAnimator.INFINITE);
        translationY.setRepeatMode(ObjectAnimator.REVERSE);
        translationY.start();
    }
    private void checkTouchCounts(Bundle bundleTarot) {
        if (touchCount >= 4) {
            textView5.setVisibility(View.VISIBLE);
            findViewById(R.id.main).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Intent intentDetail = new Intent(TarotTongQuanActivity.this, TarotTQDetailActivity.class);
                    intentDetail.putExtras(bundleTarot);
                    startActivity(intentDetail);
                    return true;
                }
            });
        }
    }
    private void setupTapAreaView(View tapAreaView, TapAreaState tapAreaState, String imgUrl, Bundle bundleTarot) {
        tapAreaView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tapAreaState.tapEnabled && event.getAction() == MotionEvent.ACTION_DOWN) {
                    tapAreaState.tapEnabled = false;
                    ValueAnimator animationView = ValueAnimator.ofFloat(180f, 0f);
                    animationView.setDuration(1000);

                    animationView.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                            float fraction = animation.getAnimatedFraction();
                            if (fraction >= 0.5f && !tapAreaState.imageChanged) {
                                // Set ảnh
                                Glide.with(TarotTongQuanActivity.this)
                                        .load(Uri.parse(imgUrl))
                                        .into((ImageView) tapAreaView);
                                tapAreaState.imageChanged = true;
                            }

                            float rotationY = (float) animation.getAnimatedValue();
                            tapAreaView.setRotationY(rotationY);
                        }
                    });
                    animationView.start();
                    touchCount++;
                    checkTouchCounts(bundleTarot);
                }
                return true;
            }
        });
    }
    private TarotTQModel loadJsonTarot(int i) {
        TarotTQModel tarotData = new TarotTQModel();
        try {
            InputStream inputStream = getAssets().open("dataTarot2.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject tarot = jsonArray.getJSONObject(i);
            JSONObject meaning = tarot.getJSONObject("meaning");

            tarotData.setName(tarot.getString("name"));
            tarotData.setNumber(tarot.getString("number"));
            tarotData.setArcana(tarot.getString("arcana"));
            tarotData.setSuit(tarot.getString("suit"));
            tarotData.setImg(tarot.getString("img"));
            tarotData.setCongviec(meaning.getString("congviec"));
            tarotData.setTinhyeu(meaning.getString("tinhyeu"));
            tarotData.setTaichinh(meaning.getString("taichinh"));
            tarotData.setSuckhoe(meaning.getString("suckhoe"));
        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return tarotData;
    }
}