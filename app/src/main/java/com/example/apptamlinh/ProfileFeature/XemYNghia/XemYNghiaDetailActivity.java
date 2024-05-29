package com.example.apptamlinh.ProfileFeature.XemYNghia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.R;
import com.example.apptamlinh.TarotFeature.TarotHNModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class XemYNghiaDetailActivity extends AppCompatActivity {
    private Button btnBack;

    private ImageView imageView;
    private TextView txtTenBai;
    private TextView txtNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_ynghia_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        imageView = findViewById(R.id.imgView);
        txtTenBai = findViewById(R.id.txtTenBai);
        txtNoiDung = findViewById(R.id.txtNoiDung);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        int imgID = intent.getIntExtra("selectedCard", 0);

        TarotHNModel dataTarot = new TarotHNModel();
        dataTarot = loadJsonTarot(imgID);
        String imgUrl = dataTarot.getImg();
        txtNoiDung.setText(dataTarot.getMeaning());
        txtTenBai.setText("Lá bài có tên \n " + dataTarot.getName());
        Glide.with(XemYNghiaDetailActivity.this)
                .load(Uri.parse(imgUrl))
                .into(imageView);
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