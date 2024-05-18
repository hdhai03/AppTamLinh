package com.example.apptamlinh.CongDongFeature;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CongDongDetailActivity extends AppCompatActivity {
    private TextView txtTime, txtName, txtGioiTinh, txtNgaySinh, txtCauHoi, txtChiTiet;
    private ImageView imageView1, imageView2, imageView3, imageView4;
    private FirebaseFirestore db;
    private Button btnBack_CongDong;
    private List<PostModel> postModels;
    private String postID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cong_dong_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các TextView và ImageView
        txtTime = findViewById(R.id.txtTime);
        txtName = findViewById(R.id.txtName);
        txtGioiTinh = findViewById(R.id.txtGioiTinh);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        txtCauHoi = findViewById(R.id.txtCauHoi);
        txtChiTiet = findViewById(R.id.txtChiTiet);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        btnBack_CongDong = findViewById(R.id.btnBack_CongDong);

        btnBack_CongDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Lấy postID từ Intent
        Intent intent = getIntent();
        postID = intent.getStringExtra("postID");

        if (postID != null) {
            db = FirebaseFirestore.getInstance();
            postModels = new ArrayList<>();
            EventChangeListener();
        } else {
            Toast.makeText(CongDongDetailActivity.this, "Post ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void EventChangeListener() {
        db.collection("post")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        postModels.clear();
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                PostModel postModel = dc.getDocument().toObject(PostModel.class);
                                postModel.setPostID(dc.getDocument().getId());
                                postModels.add(postModel);
                            }
                        }
                        updateUI();
                    }
                });
    }

    private void updateUI() {
        for (PostModel post : postModels) {
            if (post.getPostID().equals(postID)) {

                long currentTime = System.currentTimeMillis();
                long duration = currentTime - post.postTime;

                long seconds = duration / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;

                String durationString;
                if (hours < 1) {
                    if (seconds < 60) {
                        durationString = seconds + " giây trước";
                    } else {
                        durationString = minutes + " phút trước";
                    }
                } else if (hours < 24) {
                    durationString = hours + " giờ trước";
                } else {
                    durationString = days + " ngày trước";
                }
                txtTime.setText(durationString);
                txtName.setText(post.getPostName());
                txtGioiTinh.setText(post.getPostGioiTinh());
                txtNgaySinh.setText(post.getPostNgaySinh());
                txtCauHoi.setText(post.getPostCauHoi());
                txtChiTiet.setText(post.getPostChiTiet());

                // Sử dụng Glide để load ảnh vào ImageView
                Glide.with(this).load(post.getPostImgUrl1()).into(imageView1);
                Glide.with(this).load(post.getPostImgUrl2()).into(imageView2);
                Glide.with(this).load(post.getPostImgUrl3()).into(imageView3);
                Glide.with(this).load(post.getPostImgUrl4()).into(imageView4);

                break;
            }
        }
    }
}
