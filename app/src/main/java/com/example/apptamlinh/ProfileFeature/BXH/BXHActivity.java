package com.example.apptamlinh.ProfileFeature.BXH;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptamlinh.CongDongFeature.RecyclerViewInterface;
import com.example.apptamlinh.R;
import com.example.apptamlinh.UserModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BXHActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button btnBack;
    RecyclerView mRecyclerView;
    BxhAdapter bxhAdapter;
    ArrayList<UserModel> userModels = new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bxhactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userModels = new ArrayList<UserModel>();
        bxhAdapter = new BxhAdapter(BXHActivity.this, userModels, (RecyclerViewInterface) this);

        mRecyclerView.setAdapter(bxhAdapter);

        EventChangeListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userModels.clear();
        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        userModels.clear();
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                String userID = dc.getDocument().getId();
                                String userName = dc.getDocument().getString("userName");
                                long userScore = dc.getDocument().getLong("userScore");
                                UserModel userSummary = new UserModel(userID, userName, userScore);
                                userModels.add(userSummary);
                            }
                        }
                        bxhAdapter.notifyDataSetChanged();

                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        UserModel selectedUser = userModels.get(position);
        String selectedUserID = selectedUser.getUserID(); // Assuming PostModel has a method getPostID()

        Intent intentItem = new Intent(BXHActivity.this, UserDetailActivity.class);
        intentItem.putExtra("selectedUserID", selectedUserID);
        startActivity(intentItem);
    }
}