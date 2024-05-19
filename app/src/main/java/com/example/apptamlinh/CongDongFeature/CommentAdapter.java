package com.example.apptamlinh.CongDongFeature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptamlinh.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private final Context context;
    private final FirebaseFirestore db;
    private final ArrayList<CommentModel> commentModelArrayList;

    public CommentAdapter(Context context, ArrayList<CommentModel> commentModelArrayList) {
        this.context = context;
        this.commentModelArrayList = commentModelArrayList;
        this.db = FirebaseFirestore.getInstance(); // Khởi tạo FirebaseFirestore
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.comment_view_row, parent, false);
        return new CommentAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
        CommentModel commentModel = commentModelArrayList.get(position);
        DocumentReference dbRef = db.collection("users").document(commentModel.getCommentUserID());
        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    String userName = documentSnapshot.getString("userName");
                    holder.txtName.setText(userName);
                } else {
                    // Xử lý khi không có dữ liệu tồn tại
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Xử lý khi đọc dữ liệu thất bại
            }
        });
        holder.txtChiTiet.setText(commentModel.getCommentDetail());
        DocumentReference commentRef = db.collection("comment").document(commentModel.getCommentID());
        commentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    boolean isLiked = documentSnapshot.contains("likedBy") && ((ArrayList<String>) documentSnapshot.get("likedBy")).contains(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    if (isLiked) {
                        holder.likeButton.setBackgroundResource(R.drawable.ic_liked_button);
                    } else {
                        holder.likeButton.setBackgroundResource(R.drawable.ic_like_button);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle error retrieving comment data
                e.printStackTrace();
                Toast.makeText(context, "Error updating like count", Toast.LENGTH_SHORT).show();
            }
        });
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLikeButtonClick(commentModel.getCommentID(), holder.likeButton, holder.likeCount);
            }
        });

    }

    private void handleLikeButtonClick(String commentID, ImageButton likeButton, int likeCount) {
        DocumentReference commentRef = db.collection("comment").document(commentID);
        commentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    boolean isLiked = documentSnapshot.contains("likedBy") && ((ArrayList<String>) documentSnapshot.get("likedBy")).contains(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    if (isLiked) {
                        unlikeComment(commentID, likeButton);
                    } else {
                        likeComment(commentID, likeButton);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle error retrieving comment data
                e.printStackTrace();
                Toast.makeText(context, "Error updating like count", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void likeComment(String commentID, ImageButton likeButton) {
        DocumentReference commentRef = db.collection("comment").document(commentID);
        commentRef.update("commentLikeCount", FieldValue.increment(1), "likedBy", FieldValue.arrayUnion(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        likeButton.setBackgroundResource(R.drawable.ic_liked_button);
                        addCommentToUserLikedComments(commentID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle error updating like count
                        Toast.makeText(context, "Error updating like count", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void unlikeComment(String commentID, ImageButton likeButton) {
        DocumentReference commentRef = db.collection("comment").document(commentID);
        commentRef.update("commentLikeCount", FieldValue.increment(-1), "likedBy", FieldValue.arrayRemove(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        likeButton.setBackgroundResource(R.drawable.ic_like_button);
                        removeCommentFromUserLikedComments(commentID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle error updating like count
                        Toast.makeText(context, "Error updating like count", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addCommentToUserLikedComments(String commentID) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userRef = db.collection("users").document(userID);
        userRef.update("likedComments", FieldValue.arrayUnion(commentID))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Handle successful addition of comment to user's likedComments
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to add comment to user's likedComments
                        Toast.makeText(context, "Error updating user's liked comments", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void removeCommentFromUserLikedComments(String commentID) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference userRef = db.collection("users").document(userID);
        userRef.update("likedComments", FieldValue.arrayRemove(commentID))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Handle successful removal of comment from user's likedComments
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure to remove comment from user's likedComments
                        Toast.makeText(context, "Error updating user's liked comments", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return commentModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtChiTiet;
        ImageButton likeButton;
        int likeCount = 0;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtChiTiet = itemView.findViewById(R.id.txtChiTiet);
            likeButton = itemView.findViewById(R.id.like_button);
        }
    }
}
