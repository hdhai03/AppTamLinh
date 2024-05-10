package com.example.apptamlinh.TarotFeature;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class TarotTQDetailActivity extends AppCompatActivity {
    private Button btnBack_TarotTQDetail;
    private ImageView imgView, imgView2, imgView3, imgView4;
    private TextView nameCongViec, nameTinhYeu, nameTaiChinh, nameSucKhoe;
    private TextView txtCongViec, txtTinhYeu, txtTaiChinh, txtSucKhoe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarot_tqdetail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack_TarotTQDetail = findViewById(R.id.btnBack_TarotTQDetail);
        imgView = findViewById(R.id.imgView);
        imgView2 = findViewById(R.id.imgView2);
        imgView3 = findViewById(R.id.imgView3);
        imgView4 = findViewById(R.id.imgView4);
        nameCongViec = findViewById(R.id.nameCongViec);
        nameTinhYeu = findViewById(R.id.nameTinhYeu);
        nameTaiChinh = findViewById(R.id.nameTaiChinh);
        nameSucKhoe = findViewById(R.id.nameSucKhoe);
        txtCongViec = findViewById(R.id.txtCongViec);
        txtTinhYeu = findViewById(R.id.txtTinhYeu);
        txtTaiChinh = findViewById(R.id.txtTaiChinh);
        txtSucKhoe = findViewById(R.id.txtSucKhoe);
        btnBack_TarotTQDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TarotTQDetailActivity.this, TarotActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        Intent intentDetail = getIntent();
        Bundle bundleTarot = intentDetail.getExtras();

        if (bundleTarot != null) {
            setUpImage(bundleTarot.getString("imgCongViec"), imgView);
            setUpImage(bundleTarot.getString("imgTinhYeu"), imgView2);
            setUpImage(bundleTarot.getString("imgTaiChinh"), imgView3);
            setUpImage(bundleTarot.getString("imgSucKhoe"), imgView4);
            setUpTextName(bundleTarot.getString("nameCongViec"), nameCongViec, "Công việc");
            setUpTextName(bundleTarot.getString("nameTinhYeu"), nameTinhYeu, "Tình yêu");
            setUpTextName(bundleTarot.getString("nameTaiChinh"), nameTaiChinh, "Tài chính");
            setUpTextName(bundleTarot.getString("nameSucKhoe"), nameSucKhoe, "Sức khoẻ");
            txtCongViec.setText(bundleTarot.getString("txtCongViec"));
            txtTinhYeu.setText(bundleTarot.getString("txtTinhYeu"));
            txtTaiChinh.setText(bundleTarot.getString("txtTaiChinh"));
            txtSucKhoe.setText(bundleTarot.getString("txtSucKhoe"));
        }

    }

    private void setUpImage(String imgUrl, ImageView view){
        Glide.with(TarotTQDetailActivity.this)
                .load(Uri.parse(imgUrl))
                .into(view);
    }
    private void setUpTextName(String name, TextView view, String category){
        view.setText("Lá bài " + category + " của bạn là " + name);
    }
}