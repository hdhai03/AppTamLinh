package com.example.apptamlinh.CongDongFeature;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptamlinh.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CongDongActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button btnBack_CongDong, btnGuiCauHoi;
    RecyclerView mRecyclerView;
    CongDongAdapter congDongAdapter;
    ArrayList<PostModel> postModels = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cong_dong);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack_CongDong = findViewById(R.id.btnBack_CongDong);
        btnBack_CongDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnGuiCauHoi = findViewById(R.id.btnGuiCauHoi);
        btnGuiCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGuiCauHoi = new Intent(CongDongActivity.this, CongDongInputActivity.class);
                startActivity(intentGuiCauHoi);
            }
        });

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        postModels = new ArrayList<PostModel>();
        congDongAdapter = new CongDongAdapter(CongDongActivity.this, postModels, this);

        mRecyclerView.setAdapter(congDongAdapter);

        EventChangeListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Clear the current list to avoid duplicates
        postModels.clear();
        // Re-fetch the data from Firestore
        EventChangeListener();
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
                            congDongAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        PostModel selectedPost = postModels.get(position);
        String postID = selectedPost.getPostID(); // Assuming PostModel has a method getPostID()

        Intent intentItem = new Intent(CongDongActivity.this, CongDongDetailActivity.class);
        intentItem.putExtra("postID", postID);
        startActivity(intentItem);
    }
}
