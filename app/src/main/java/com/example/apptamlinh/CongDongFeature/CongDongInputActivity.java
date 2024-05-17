package com.example.apptamlinh.CongDongFeature;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.R;
import com.example.apptamlinh.TarotFeature.TarotTQModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CongDongInputActivity extends AppCompatActivity {
    private Button btnBack, btnLuu;
    private EditText edtName, edtCauHoi, edtChiTiet;
    private NumberPicker numPickerDay, numPickerMonth, numPickerYear;
    RadioButton rdNu, rdNam, rdKhac;
    private final String[] months = new String[]{
            "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
            "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"
    };

    private class TapAreaState {
        boolean imageChanged = false;
        boolean tapEnabled = true;
    }

    private ImageView imgView;
    private ImageView imgView2;
    private ImageView imgView3;
    private ImageView imgView4;

    private int touchCount = 0;

    private final CongDongInputActivity.TapAreaState tapAreaState1 = new CongDongInputActivity.TapAreaState();
    private final CongDongInputActivity.TapAreaState tapAreaState2 = new CongDongInputActivity.TapAreaState();
    private final CongDongInputActivity.TapAreaState tapAreaState3 = new CongDongInputActivity.TapAreaState();
    private final CongDongInputActivity.TapAreaState tapAreaState4 = new CongDongInputActivity.TapAreaState();
    private final ArrayList<TarotTQModel> tarotModels = new ArrayList<>();

    private FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cong_dong_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnLuu = findViewById(R.id.btnLuu);
        edtName = findViewById(R.id.edtName);
        edtCauHoi = findViewById(R.id.edtCauHoi);
        edtChiTiet = findViewById(R.id.edtChiTiet);
        rdNu = findViewById(R.id.radioButtonNu);
        rdNam = findViewById(R.id.radioButtonNam);
        rdKhac = findViewById(R.id.radioButtonKhac);
        imgView = findViewById(R.id.imgView);
        imgView2 = findViewById(R.id.imgView2);
        imgView3 = findViewById(R.id.imgView3);
        imgView4 = findViewById(R.id.imgView4);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        numPickerDay = findViewById(R.id.numPickerDay);
        numPickerMonth = findViewById(R.id.numPickerMonth);
        numPickerYear = findViewById(R.id.numPickerYear);
        numPickerDay.setMinValue(1);
        numPickerDay.setMaxValue(31);
        numPickerMonth.setMinValue(1);
        numPickerMonth.setMaxValue(12);
        numPickerYear.setMinValue(1900);
        numPickerYear.setMaxValue(2024);
        numPickerMonth.setDisplayedValues(months);
        numPickerDay.setTextColor(Color.WHITE);
        numPickerMonth.setTextColor(Color.WHITE);
        numPickerYear.setTextColor(Color.WHITE);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();

        Random random = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 4) {
            int idTarot = random.nextInt(78);
            if (!randomNumbers.contains(idTarot)) {
                randomNumbers.add(idTarot);
            }
        }

        for (int idTarot : randomNumbers) {
            TarotTQModel dataTarot = loadJsonTarot(idTarot);
            tarotModels.add(dataTarot);
        }

        setupTapAreaView(imgView, tapAreaState1, tarotModels.get(0).getImg());
        setupTapAreaView(imgView2, tapAreaState2, tarotModels.get(1).getImg());
        setupTapAreaView(imgView3, tapAreaState3, tarotModels.get(2).getImg());
        setupTapAreaView(imgView4, tapAreaState4, tarotModels.get(3).getImg());


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (touchCount >= 4) {
                    int iYear = numPickerYear.getValue();
                    int iMonth = numPickerMonth.getValue();
                    int iDay = numPickerDay.getValue();
                    String ngaySinh = String.format("%02d/%02d/%04d", iDay, iMonth, iYear);
                    String gioiTinh = "";
                    if (rdNu.isChecked()) {
                        gioiTinh = rdNu.getText().toString();
                    } else if (rdNam.isChecked()) {
                        gioiTinh = rdNam.getText().toString();
                    } else {
                        gioiTinh = rdKhac.getText().toString();
                    }
                    String name = String.valueOf(edtName.getText());
                    String cauHoi = String.valueOf(edtCauHoi.getText());
                    String chiTiet = String.valueOf(edtChiTiet.getText());

                    addPost(name, ngaySinh, gioiTinh, cauHoi, chiTiet, tarotModels.get(0).getImg(), tarotModels.get(1).getImg(), tarotModels.get(2).getImg(), tarotModels.get(3).getImg());
                    Intent intent = new Intent(CongDongInputActivity.this, CongDongActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CongDongInputActivity.this, "Mở hết 4 lá bài trước khi gửi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addPost(String name, String ngaySinh, String gioiTinh, String cauHoi, String chiTiet, String imgUrl1, String imgUrl2, String imgUrl3, String imgUrl4) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        long postTime = System.currentTimeMillis();

        Map<String, Object> updates = new HashMap<>();
        updates.put("postName", name);
        updates.put("postNgaySinh", ngaySinh);
        updates.put("postGioiTinh", gioiTinh);
        updates.put("postCauHoi", cauHoi);
        updates.put("postChiTiet", chiTiet);
        updates.put("postImgUrl1", imgUrl1);
        updates.put("postImgUrl2", imgUrl2);
        updates.put("postImgUrl3", imgUrl3);
        updates.put("postImgUrl4", imgUrl4);
        updates.put("postUserID", userId);
        updates.put("postTime", postTime);

        db.collection("post")
                .add(updates)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CongDongInputActivity.this, "Gửi câu hỏi thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CongDongInputActivity.this, "Gửi câu hỏi thất bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setupTapAreaView(View tapAreaView, TapAreaState tapAreaState, String imgUrl) {
        tapAreaView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (tapAreaState.tapEnabled && event.getAction() == MotionEvent.ACTION_DOWN) {
                    tapAreaState.tapEnabled = false;
                    ValueAnimator animationView = ValueAnimator.ofFloat(180f, 0f);
                    animationView.setDuration(1000);
                    animationView.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                            float fraction = animation.getAnimatedFraction();
                            if (fraction >= 0.5f && !tapAreaState.imageChanged) {
                                // Set ảnh
                                Glide.with(CongDongInputActivity.this)
                                        .load(Uri.parse(imgUrl))
                                        .into((ImageView) tapAreaView);
                                tapAreaState.imageChanged = true;
                            }

                            float rotationY = (float) animation.getAnimatedValue();
                            tapAreaView.setRotationY(rotationY);
                        }
                    });
                    animationView.start();
                    touchCount++;
                }
                return true;
            }
        });
    }

    private TarotTQModel loadJsonTarot(int i) {
        TarotTQModel tarotData = new TarotTQModel();
        try {
            InputStream inputStream = getAssets().open("dataTarot2.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject tarot = jsonArray.getJSONObject(i);
            JSONObject meaning = tarot.getJSONObject("meaning");

            tarotData.setName(tarot.getString("name"));
            tarotData.setNumber(tarot.getString("number"));
            tarotData.setArcana(tarot.getString("arcana"));
            tarotData.setSuit(tarot.getString("suit"));
            tarotData.setImg(tarot.getString("img"));
            tarotData.setCongviec(meaning.getString("congviec"));
            tarotData.setTinhyeu(meaning.getString("tinhyeu"));
            tarotData.setTaichinh(meaning.getString("taichinh"));
            tarotData.setSuckhoe(meaning.getString("suckhoe"));
        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return tarotData;
    }
}