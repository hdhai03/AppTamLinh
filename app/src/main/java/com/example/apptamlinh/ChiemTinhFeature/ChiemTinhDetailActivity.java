package com.example.apptamlinh.ChiemTinhFeature;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment.NamFragment;
import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment.NuFragment;
import com.example.apptamlinh.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
public class ChiemTinhDetailActivity extends AppCompatActivity {
    // Khai bao Button
    public Button btnBack_ChiemTinhDetail;

    // Khai bao Layout
    public TabLayout  mTabLayout;
    public ViewPager mViewPager;
    // Khai bao View
    public TextView txtHeader_ChiemTinhDetail;
    public ImageView imgChiemTinh_ChiemTinhDetail;

    int[] chiemTinhImages = {R.drawable.png_ma_ket, R.drawable.png_bao_binh, R.drawable.png_bach_duong, R.drawable.png_ma_ket, R.drawable.png_bao_binh};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chiem_tinh_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Anh xa View
        btnBack_ChiemTinhDetail = findViewById(R.id.btnBack_ChiemTinhDetail);
        mTabLayout = findViewById(R.id.tabLayout_ChiemTinhDetail);
        mViewPager = findViewById(R.id.viewPager_ChiemTinhDetail);

        txtHeader_ChiemTinhDetail = findViewById(R.id.txtHeader_ChiemTinhDetail);
        imgChiemTinh_ChiemTinhDetail = findViewById(R.id.imgChiemTinh_ChiemTinhDetail);

        // Back Button
        btnBack_ChiemTinhDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        //Lấy ID từ danh sách 12 cung hoàng đạo r trả về các thông tin
        Bundle extras = getIntent().getExtras();
        int idChiemTinh1 = 0;
        String receivedData = extras.getString("data");
        idChiemTinh1 = Integer.parseInt(receivedData); //ID chiem tinh

        ChiemTinhModel chiemTinhNam = new ChiemTinhModel();
        ChiemTinhModel chiemTinhNu = new ChiemTinhModel();
        chiemTinhNam = loadJsonNam(idChiemTinh1);
        chiemTinhNu = loadJsonNu(idChiemTinh1);
        
        txtHeader_ChiemTinhDetail.setText(chiemTinhNam.chiemTinhName);
        imgChiemTinh_ChiemTinhDetail.setImageResource(chiemTinhNam.chiemTinhImages);
        //Truyền dữ liệu vào fragment
        Bundle bundle = new Bundle();
        bundle.putString("tongQuan", chiemTinhNam.chiemTinhTongQuan);
        bundle.putString("tinhCach", chiemTinhNam.chiemTinhTinhCach);
        bundle.putString("diemManh", chiemTinhNam.chiemTinhDiemManh);
        bundle.putString("diemYeu", chiemTinhNam.chiemTinhDiemYeu);
        bundle.putString("giaDinh", chiemTinhNam.chiemTinhGiaDinh);
        bundle.putString("tinhYeu", chiemTinhNam.chiemTinhTinhYeu);
        bundle.putString("suNghiep", chiemTinhNam.chiemTinhSuNghiep);

        NamFragment namFragment = (NamFragment) viewPagerAdapter.instantiateItem(mViewPager, mViewPager.getCurrentItem());
        namFragment.setArguments(bundle);

        Bundle bundleNu = new Bundle();
        bundleNu.putString("tongQuanNu", chiemTinhNu.chiemTinhTongQuan);
        bundleNu.putString("tinhCachNu", chiemTinhNu.chiemTinhTinhCach);
        bundleNu.putString("diemManhNu", chiemTinhNu.chiemTinhDiemManh);
        bundleNu.putString("diemYeuNu", chiemTinhNu.chiemTinhDiemYeu);
        bundleNu.putString("giaDinhNu", chiemTinhNu.chiemTinhGiaDinh);
        bundleNu.putString("tinhYeuNu", chiemTinhNu.chiemTinhTinhYeu);
        bundleNu.putString("suNghiepNu", chiemTinhNu.chiemTinhSuNghiep);

        NuFragment nuFragment = (NuFragment) viewPagerAdapter.instantiateItem(mViewPager, 1); //Vì mViewPager.getCurrentItem() trả về là 0 nên phải tự nhập
        nuFragment.setArguments(bundleNu);

    }

    private ChiemTinhModel loadJsonNam(int i) {
        ChiemTinhModel chiemTinhNam = new ChiemTinhModel();
        try {
            InputStream inputStream = getAssets().open("dataChiemTinh.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String ten = jsonObject.getString("ten"); // Retrieve the name

            // Now you can access the nested objects for "nam" and "nu" if needed
            JSONObject nam = jsonObject.getJSONObject("nam");
//            JSONObject nu = jsonObject.getJSONObject("nu");
            chiemTinhNam.setChiemTinhName(jsonObject.getString("ten"));
            chiemTinhNam.setChiemTinhTongQuan(nam.getString("tongquan"));
            chiemTinhNam.setChiemTinhTinhCach(nam.getString("tinhcach"));
            chiemTinhNam.setChiemTinhDiemManh(nam.getString("diemmanh"));
            chiemTinhNam.setChiemTinhDiemYeu(nam.getString("diemyeu"));
            chiemTinhNam.setChiemTinhGiaDinh(nam.getString("giadinh"));
            chiemTinhNam.setChiemTinhSuNghiep(nam.getString("sunghiep"));
            chiemTinhNam.setChiemTinhTinhYeu(nam.getString("tinhyeu"));
            chiemTinhNam.setChiemTinhImages(chiemTinhImages[i]);

        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return chiemTinhNam;
    }
    private ChiemTinhModel loadJsonNu(int i) {
        ChiemTinhModel chiemTinhNu = new ChiemTinhModel();
        try {
            InputStream inputStream = getAssets().open("dataChiemTinh.JSON");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String ten = jsonObject.getString("ten"); // Retrieve the name

            // Now you can access the nested objects for "nam" and "nu" if needed
            JSONObject nu = jsonObject.getJSONObject("nu");
            chiemTinhNu.setChiemTinhName(jsonObject.getString("ten"));
            chiemTinhNu.setChiemTinhTongQuan(nu.getString("tongquan"));
            chiemTinhNu.setChiemTinhTinhCach(nu.getString("tinhcach"));
            chiemTinhNu.setChiemTinhDiemManh(nu.getString("diemmanh"));
            chiemTinhNu.setChiemTinhDiemYeu(nu.getString("diemyeu"));
            chiemTinhNu.setChiemTinhGiaDinh(nu.getString("giadinh"));
            chiemTinhNu.setChiemTinhSuNghiep(nu.getString("sunghiep"));
            chiemTinhNu.setChiemTinhTinhYeu(nu.getString("tinhyeu"));
            chiemTinhNu.setChiemTinhImages(chiemTinhImages[i]);

        } catch (Exception e) {
            Log.e("TAG", "loadJson: error", e);
        }
        return chiemTinhNu;
    }
}