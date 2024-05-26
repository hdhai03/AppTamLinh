package com.example.apptamlinh.CongDongFeature;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CongDongDetailActivity extends AppCompatActivity {
    private TextView txtTime, txtName, txtGioiTinh, txtNgaySinh, txtCauHoi, txtChiTiet;
    private ImageView imageView1, imageView2, imageView3, imageView4;
    private EditText edtCMT;

    private FirebaseFirestore db;
    private Button btnBack_CongDong, btnSend;
    private List<PostModel> postModels;
    private String postID;

    RecyclerView mRecyclerView_CMT;
    CommentAdapter commentAdapter;
    ArrayList<CommentModel> commentModels = new ArrayList<>();


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
        edtCMT = findViewById(R.id.edtCMT);
        btnSend = findViewById(R.id.btnSend);

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

        mRecyclerView_CMT = findViewById(R.id.mRecyclerView_CMT);
        mRecyclerView_CMT.setHasFixedSize(true);
        mRecyclerView_CMT.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        commentModels = new ArrayList<CommentModel>();
        commentAdapter = new CommentAdapter(CongDongDetailActivity.this, commentModels);

        mRecyclerView_CMT.setAdapter(commentAdapter);

        EventChangeListener2();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentDetail = edtCMT.getText().toString();
                String commentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String commentPostID = postID;
                long commentTime = System.currentTimeMillis();
                long commentLikeCount = 0;

                CommentModel commentModel = new CommentModel(commentDetail, commentUserID, commentPostID, commentTime, commentLikeCount);

                addComment(commentModel);
            }
        });

    }

    private void EventChangeListener2() {
        db.collection("comment")
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
                                CommentModel commentModel = dc.getDocument().toObject(CommentModel.class);
                                commentModel.setCommentID(dc.getDocument().getId());

                                if (commentModel.getCommentPostID().equals(postID)) {
                                    commentModels.add(commentModel);
                                }
                            }
                            Collections.sort(commentModels, new Comparator<CommentModel>() {
                                @Override
                                public int compare(CommentModel o1, CommentModel o2) {
                                    return Long.compare(o2.getCommentLikeCount(), o1.getCommentLikeCount());
                                }
                            });
                            commentAdapter.notifyDataSetChanged();
                        }
                    }
                });
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

                Glide.with(this).load(post.getPostImgUrl1()).into(imageView1);
                Glide.with(this).load(post.getPostImgUrl2()).into(imageView2);
                Glide.with(this).load(post.getPostImgUrl3()).into(imageView3);
                Glide.with(this).load(post.getPostImgUrl4()).into(imageView4);

                break;
            }
        }
    }

    private void addComment(CommentModel commentModel) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> updates = new HashMap<>();
        updates.put("commentDetail", commentModel.getCommentDetail());
        updates.put("commentUserID", commentModel.getCommentUserID());
        updates.put("commentPostID", commentModel.getCommentPostID());
        updates.put("commentLikeCount", 0);

        db.collection("comment")
                .add(updates)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String commentID = documentReference.getId();
                        updateCommentIDsForUser(commentID);
                        updateScore();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    private void updateCommentIDsForUser(String commentID) {
        DocumentReference userRef = FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        userRef.update("commentedIDs", FieldValue.arrayUnion(commentID))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Comments", "Comment ID successfully added to user's list");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Comments", "Failed to add comment ID to user's list", e);
                    }
                });
    }

    public void updateScore() {
        DocumentReference userRef = FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Map<String, Object> updatedData = new HashMap<>();
        int newEnergyValue = 10;
        updatedData.put("userScore", FieldValue.increment(newEnergyValue));

        userRef.update(updatedData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Update User Energy", "Cập nhật năng lượng thành công");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Update User Energy", "Lỗi khi cập nhật năng lượng: " + e.getMessage());
                    }
                });
    }
}

