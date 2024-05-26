package com.example.apptamlinh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhActivity;
import com.example.apptamlinh.CongDongFeature.CongDongActivity;
import com.example.apptamlinh.ProfileFeature.PersonalProfileActivity;
import com.example.apptamlinh.TarotFeature.TarotActivity;
import com.example.apptamlinh.ThanSHFeature.ThanSoHocActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "TarotHangNgayPrefs";
    private static final String KEY_IMAGE_URLS = "imageUrls";

    private static final String DEFAULT_IMAGE_URL = "file:///android_asset/cards/0.png";
    private Button btnProfile_Home;
    private Button btnTarot_Home;
    private Button btnTarot2_Home;
    private Button btnChiemTinh_Home;
    private Button btnChiemTinh2_Home;
    private Button btnThanSH_Home;
    private Button btnThanSH2_Home;
    private Button btnCongDong_Home, btnEnergy_Home;
    DocumentReference dbRef;
    Long userEnergy = null;

    private final int[] buttonIds = {
            R.id.btnCard1, R.id.btnCard2, R.id.btnCard3, R.id.btnCard4,
            R.id.btnCard5, R.id.btnCard6, R.id.btnCard7, R.id.btnCard8,
            R.id.btnCard9, R.id.btnCard10, R.id.btnCard11, R.id.btnCard12
    };
    private final Button[] buttons = new Button[buttonIds.length];

    private List<String> cardImageUrls = new ArrayList<>();

    private void mapButtons() {
        for (int i = 0; i < buttonIds.length; i++) {
            buttons[i] = findViewById(buttonIds[i]);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Lấy dữ liệu từ DocumentSnapshot
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_Home.setText(String.valueOf(userEnergy));
                    for (int i = 0; i < buttonIds.length; i++) {
                        Button button = buttons[i];
                        String imageUrl = cardImageUrls.get(i);
//                        setButtonBackgroundWithGlide(button, imageUrl);
                    }
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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnProfile_Home = findViewById(R.id.btnProfile_Home);
        btnTarot_Home = findViewById(R.id.btnTarot_Home);
        btnTarot2_Home = findViewById(R.id.btnTarot2_Home);
        btnChiemTinh_Home = findViewById(R.id.btnChiemTinh_Home);
        btnChiemTinh2_Home = findViewById(R.id.btnChiemTinh2_Home);
        btnThanSH_Home = findViewById(R.id.btnThanSH_Home);
        btnThanSH2_Home = findViewById(R.id.btnThanSH2_Home);
        btnCongDong_Home = findViewById(R.id.btnCongDong_Home);
        btnEnergy_Home = findViewById(R.id.btnEnergy_Home);

        mapButtons();

        cardImageUrls = getImageUrlsFromSharedPreferences(getApplicationContext());

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseFirestore.getInstance().collection("users").document(userId);

        for (int i = 0; i < buttonIds.length; i++) {
            Button button = buttons[i];
            String imageUrl = cardImageUrls.get(i);
//            setButtonBackgroundWithGlide(button, imageUrl);
        }

        dbRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    userEnergy = documentSnapshot.getLong("userEnergy");
                    btnEnergy_Home.setText(String.valueOf(userEnergy));
                } else {
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
        btnProfile_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProfile = new Intent(HomeActivity.this, PersonalProfileActivity.class);
                startActivity(intentProfile);
            }
        });

        btnTarot_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTarot = new Intent(HomeActivity.this, TarotActivity.class);
                startActivity(intentTarot);
            }
        });

        btnTarot2_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTarot = new Intent(HomeActivity.this, TarotActivity.class);
                startActivity(intentTarot);
            }
        });

        btnChiemTinh_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChiemTinh = new Intent(HomeActivity.this, ChiemTinhActivity.class);
                startActivity(intentChiemTinh);
            }
        });

        btnChiemTinh2_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChiemTinh = new Intent(HomeActivity.this, ChiemTinhActivity.class);
                startActivity(intentChiemTinh);
            }
        });

        btnThanSH_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThanSH = new Intent(HomeActivity.this, ThanSoHocActivity.class);
                startActivity(intentThanSH);
            }
        });

        btnThanSH2_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThanSH = new Intent(HomeActivity.this, ThanSoHocActivity.class);
                startActivity(intentThanSH);
            }
        });

        btnCongDong_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCongDong = new Intent(HomeActivity.this, CongDongActivity.class);
                startActivity(intentCongDong);
            }
        });

    }

//    private void setButtonBackgroundWithGlide(Button button, String imageUrl) {
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            Glide.with(this)
//                    .load(imageUrl)
//                    .into(button);
//        } else {
//            Glide.with(this)
//                    .load(DEFAULT_IMAGE_URL) // Sử dụng hình ảnh mặc định
//                    .into(button);
//        }
//    }

    private List<String> getImageUrlsFromSharedPreferences(Context context) {
        List<String> imageUrls = new ArrayList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_IMAGE_URLS, null);

        // Kiểm tra xem có JSON dữ liệu không
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                // Lặp qua JSON array để lấy các URL ảnh
                for (int i = 0; i < jsonArray.length(); i++) {
                    imageUrls.add(jsonArray.getString(i));
                }

                // Nếu danh sách không đủ 12 phần tử, điền các phần tử trống bằng URL mặc định
                while (imageUrls.size() < 12) {
                    imageUrls.add(DEFAULT_IMAGE_URL);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Nếu không có JSON dữ liệu, điền danh sách với URL mặc định
        while (imageUrls.size() < 12) {
            imageUrls.add(DEFAULT_IMAGE_URL);
        }

        return imageUrls;
    }

}