package com.example.apptamlinh.ThanSHFeature.TuongHopTinhYeu;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptamlinh.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TuongHopTinhYeuDetailActivity extends AppCompatActivity {
    TextView txtNumber, txtHeader2, txtHeader3, txtDetail2, txtDetail3, txtDetail4, txtDetail5, txtDetail6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tuong_hop_tinh_yeu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNumber = findViewById(R.id.txtNumber);
        txtHeader2 = findViewById(R.id.txtHeader2);
        txtHeader3 = findViewById(R.id.txtHeader3);
        txtDetail2 = findViewById(R.id.txtDetail2);
        txtDetail3 = findViewById(R.id.txtDetail3);
        txtDetail4 = findViewById(R.id.txtDetail4);
        txtDetail5 = findViewById(R.id.txtDetail5);
        txtDetail6 = findViewById(R.id.txtDetail6);


        Bundle extras = getIntent().getExtras();
        String sDate = extras.getString("dataDate");
        String sDate2 = extras.getString("dataDate2");

        String ban = sumDigitsToOne(sDate);
        String nguoiAy = sumDigitsToOne(sDate2);

        String number = ban + " - " + nguoiAy;
        txtNumber.setText(number);
        String s = "";
        switch (Integer.valueOf(nguoiAy) - 1) {
            case 0:
                s = "mot";
                break;
            case 1:
                s = "hai";
                break;

            case 2:
                s = "ba";
                break;
            case 3:
                s = "bon";
                break;
            case 4:
                s = "nam";
                break;
            case 5:
                s = "sau";
                break;
            case 6:
                s = "bay";
                break;
            case 7:
                s = "tam";
                break;
            case 8:
                s = "chin";
                break;
        }
        ArrayList<String> jsonData = loadJson(Integer.valueOf(ban) - 1, Integer.valueOf(nguoiAy) - 1, s);
        txtHeader2.setText("Số chủ đạo của bạn là " + ban);
        txtHeader3.setText("Số chủ đạo của người ấy là " + nguoiAy);
        txtDetail2.setText("Tích cực: " + jsonData.get(0));
        txtDetail3.setText("Tiêu cực: " + jsonData.get(1));
        txtDetail4.setText("Tích cực: " + jsonData.get(2));
        txtDetail5.setText("Tiêu cực: " + jsonData.get(3));
        txtDetail6.setText(jsonData.get(4));

    }

    public static String sumDigitsToOne(String str) {
        while (str.length() > 1) {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                sum += Character.getNumericValue(str.charAt(i));
            }
            str = Integer.toString(sum);
        }
        return str;
    }

    private ArrayList<String> loadJson(int i, int j, String s) {
        ArrayList<String> data = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("tuonghoptinhyeu.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject ban = jsonArray.getJSONObject(i);
            JSONObject nguoiAy = jsonArray.getJSONObject(j);

            String tichCucBan = ban.getString("tichcuc");
            String tieuCucBan = ban.getString("tieucuc");
            String tichCucNguoiAy = nguoiAy.getString("tichcuc");
            String tieuCucNguoiAy = nguoiAy.getString("tieucuc");

            String meaning = ban.getString(s);

            data.add(tichCucBan);
            data.add(tieuCucBan);
            data.add(tichCucNguoiAy);
            data.add(tieuCucNguoiAy);
            data.add(meaning);

        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return data;
    }
}