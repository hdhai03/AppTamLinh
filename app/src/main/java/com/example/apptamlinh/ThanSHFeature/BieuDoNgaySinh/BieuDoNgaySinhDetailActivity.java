package com.example.apptamlinh.ThanSHFeature.BieuDoNgaySinh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class BieuDoNgaySinhDetailActivity extends AppCompatActivity {
    private Button btnBack;
    TextView[] txtViews = new TextView[9];
    TextView[] txtDetails = new TextView[9];
    TextView[] txtHeaders = new TextView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bieu_do_ngay_sinh_detail);
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

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("txtView" + (i + 1), "id", getPackageName());
            txtViews[i] = findViewById(resId);
        }

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("txt" + (i + 1) + "Header", "id", getPackageName());
            txtHeaders[i] = findViewById(resId);
        }

        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("txt" + (i + 1) + "Detail", "id", getPackageName());
            txtDetails[i] = findViewById(resId);
        }


        Bundle extras = getIntent().getExtras();
        String inputData = extras.getString("data");

        String[] mangChuoi = taoMangChuoi(inputData);

        for (int i = 0; i < txtViews.length; i++) {
            txtViews[i].setText(mangChuoi[i]);
        }
        ArrayList<BDNgaySinhModel> dataList = loadJson();

        for (int i = 0; i < 9; i++) {
            if (mangChuoi[i] != null) {
                switch (mangChuoi[i].length()) {
                    case 1:
                        String mot = dataList.get(i).getMot();
                        String txtHeader = "Bạn có " + mangChuoi[i].length() + " số " + (i + 1) + " trong ngày sinh";
                        txtHeaders[i].setText(txtHeader);
                        txtDetails[i].setText(mot);
                        break;
                    case 2:
                        String hai = dataList.get(i).getHai();
                        String txtHeader2 = "Bạn có " + mangChuoi[i].length() + " số " + (i + 1) + " trong ngày sinh";
                        txtHeaders[i].setText(txtHeader2);
                        txtDetails[i].setText(hai);
                        break;
                    case 3:
                        String ba = dataList.get(i).getBa();
                        String txtHeader3 = "Bạn có " + mangChuoi[i].length() + " số " + (i + 1) + " trong ngày sinh";
                        txtHeaders[i].setText(txtHeader3);
                        txtDetails[i].setText(ba);
                        break;
                    case 4:
                        String bon = dataList.get(i).getBon();
                        String txtHeader4 = "Bạn có " + mangChuoi[i].length() + " số " + (i + 1) + " trong ngày sinh";
                        txtHeaders[i].setText(txtHeader4);
                        txtDetails[i].setText(bon);
                        break;
                    case 5:
                    case 6:
                        String nam = dataList.get(i).getNam();
                        String txtHeader5 = "Bạn có " + mangChuoi[i].length() + " số " + (i + 1) + " trong ngày sinh";
                        txtHeaders[i].setText(txtHeader5);
                        txtDetails[i].setText(nam);
                        break;
                    default:
                        break;
                }
            } else {
                txtDetails[i].setVisibility(View.GONE);
                txtHeaders[i].setVisibility(View.GONE);
            }
        }

    }

    private static String[] taoMangChuoi(String ngaystring) {
        String[] mangChuoi = new String[9];
        for (int i = 0; i < ngaystring.length(); i++) {
            if (Character.isDigit(ngaystring.charAt(i))) {
                switch (ngaystring.charAt(i)) {
                    case '1':
                        mangChuoi[0] = (mangChuoi[0] != null ? mangChuoi[0] : "") + ngaystring.charAt(i);
                        break;
                    case '2':
                        mangChuoi[1] = (mangChuoi[1] != null ? mangChuoi[1] : "") + ngaystring.charAt(i);
                        break;
                    case '3':
                        mangChuoi[2] = (mangChuoi[2] != null ? mangChuoi[2] : "") + ngaystring.charAt(i);
                        break;
                    case '4':
                        mangChuoi[3] = (mangChuoi[3] != null ? mangChuoi[3] : "") + ngaystring.charAt(i);
                        break;
                    case '5':
                        mangChuoi[4] = (mangChuoi[4] != null ? mangChuoi[4] : "") + ngaystring.charAt(i);
                        break;
                    case '6':
                        mangChuoi[5] = (mangChuoi[5] != null ? mangChuoi[5] : "") + ngaystring.charAt(i);
                        break;
                    case '7':
                        mangChuoi[6] = (mangChuoi[6] != null ? mangChuoi[6] : "") + ngaystring.charAt(i);
                        break;
                    case '8':
                        mangChuoi[7] = (mangChuoi[7] != null ? mangChuoi[7] : "") + ngaystring.charAt(i);
                        break;
                    case '9':
                        mangChuoi[8] = (mangChuoi[8] != null ? mangChuoi[8] : "") + ngaystring.charAt(i);
                        break;
                    default:
                        break;
                }
            }
        }

        // Trả về mảng đã tạo
        return mangChuoi;
    }

    private ArrayList<BDNgaySinhModel> loadJson() {
        ArrayList<BDNgaySinhModel> dataList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("bieudongaysinh.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                BDNgaySinhModel data = new BDNgaySinhModel();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                data.setMot(jsonObj.getString("mot"));
                data.setHai(jsonObj.getString("hai"));
                data.setBa(jsonObj.getString("ba"));
                data.setBon(jsonObj.getString("bon"));
                data.setNam(jsonObj.getString("nam"));
                data.setLuuy(jsonObj.getString("luuy"));
                dataList.add(data);
            }

        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return dataList;
    }


}