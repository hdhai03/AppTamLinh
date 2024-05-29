package com.example.apptamlinh.ProfileFeature.HDGD;

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

import com.example.apptamlinh.CongDongFeature.CongDongDetailActivity;
import com.example.apptamlinh.CongDongFeature.PostModel;
import com.example.apptamlinh.CongDongFeature.RecyclerViewInterface;
import com.example.apptamlinh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class HDGDActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button btnBack;
    RecyclerView mRecyclerView;
    HDGDAdapter hdgdAdapter;
    ArrayList<PostModel> postModels = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hdgdactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        postModels = new ArrayList<PostModel>();
        hdgdAdapter = new HDGDAdapter(HDGDActivity.this, postModels, (RecyclerViewInterface) this);

        mRecyclerView.setAdapter(hdgdAdapter);

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
                                if (Objects.equals(postModel.getPostUserID(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                    postModels.add(postModel);
                                }
                            }
                            hdgdAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        PostModel selectedPost = postModels.get(position);
        String postID = selectedPost.getPostID(); // Assuming PostModel has a method getPostID()
        Intent intentItem = new Intent(HDGDActivity.this, CongDongDetailActivity.class);
        intentItem.putExtra("postID", postID);
        startActivity(intentItem);
    }
}