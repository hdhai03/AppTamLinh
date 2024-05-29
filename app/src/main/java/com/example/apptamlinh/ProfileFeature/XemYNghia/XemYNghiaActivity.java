package com.example.apptamlinh.ProfileFeature.XemYNghia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptamlinh.CongDongFeature.RecyclerViewInterface;
import com.example.apptamlinh.R;
import com.example.apptamlinh.TarotFeature.TarotHNModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class XemYNghiaActivity extends AppCompatActivity implements RecyclerViewInterface {
    private Button btnBack;
    RecyclerView mRecyclerView;
    YNghiaAdapter yNghiaAdapter;
    ArrayList<TarotHNModel> tarotHNModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_y_nghia);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        tarotHNModels = new ArrayList<>();
        yNghiaAdapter = new YNghiaAdapter((RecyclerViewInterface) XemYNghiaActivity.this, this, tarotHNModels);

        mRecyclerView.setAdapter(yNghiaAdapter);

        EventChangeListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tarotHNModels.clear();
        EventChangeListener();
    }

    private void EventChangeListener() {
        for (int i = 0; i < 78; i++) {
            TarotHNModel tarotHNModel = loadJsonTarot(i);
            tarotHNModels.add(tarotHNModel);
        }
        yNghiaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intentItem = new Intent(XemYNghiaActivity.this, XemYNghiaDetailActivity.class);
        intentItem.putExtra("selectedCard", position);
        startActivity(intentItem);
    }

    private TarotHNModel loadJsonTarot(int i) {
        TarotHNModel tarotData = new TarotHNModel();
        try {
            InputStream inputStream = getAssets().open("dataTarot.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject tarot = jsonArray.getJSONObject(i);

            tarotData.setName(tarot.getString("name"));
            tarotData.setNumber(tarot.getString("number"));
            tarotData.setArcana(tarot.getString("arcana"));
            tarotData.setSuit(tarot.getString("suit"));
            tarotData.setImg(tarot.getString("img"));
            tarotData.setMeaning(tarot.getString("meaning"));
        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return tarotData;
    }
}