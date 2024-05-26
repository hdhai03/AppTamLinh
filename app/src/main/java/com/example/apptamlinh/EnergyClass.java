package com.example.apptamlinh;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EnergyClass {

    private final FirebaseFirestore db;
    private String userId;
    private DocumentReference userRef;

    public EnergyClass() {
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
            userRef = db.collection("users").document(userId);
        }
    }

    public void updateEnergy(int energyChange, boolean increase) {
        if (userId != null) {
            Map<String, Object> updatedData = new HashMap<>();
            int newEnergyValue = increase ? energyChange : -energyChange;
            updatedData.put("userEnergy", FieldValue.increment(newEnergyValue));

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
}