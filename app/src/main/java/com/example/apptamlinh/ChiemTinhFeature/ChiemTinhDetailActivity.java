package com.example.apptamlinh.ChiemTinhFeature;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment.NamFragment;
import com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment.NuFragment;
import com.example.apptamlinh.R;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChiemTinhDetailActivity extends AppCompatActivity {
    // Khai bao Button
    public Button btnBack_ChiemTinhDetail;

    // Khai bao Layout
    public TabLayout  mTabLayout;
    public ViewPager mViewPager;

    // Khai bao View
    public TextView txtHeader_ChiemTinhDetail;
    public ImageView imgChiemTinh_ChiemTinhDetail;

    // Du lieu
    ArrayList<ChiemTinhModel> chiemTinhModels = new ArrayList<>();
    int[] chiemTinhImages = {R.drawable.png_ma_ket, R.drawable.png_bao_binh, R.drawable.png_bach_duong};

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


        // Fragment Nam - Nu
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        // Setup dữ liệu
        setUpChiemTinhModels();

        //Lấy ID từ danh sách 12 cung hoàng đạo r trả về các thông tin
        Bundle extras = getIntent().getExtras();
        int idChiemTinh = 0;
        if (extras != null) {
            String receivedData = extras.getString("data");
            if (receivedData != null) {
                idChiemTinh = Integer.parseInt(receivedData); //ID chiem tinh
                //SetText và SetImage cho View
                txtHeader_ChiemTinhDetail.setText(chiemTinhModels.get(idChiemTinh).chiemTinhName);
                imgChiemTinh_ChiemTinhDetail.setImageResource(chiemTinhModels.get(idChiemTinh).chiemTinhImages);
            }
        }

        //Truyền dữ liệu vào fragment
        Bundle bundle = new Bundle();
        bundle.putString("tongQuan", chiemTinhModels.get(idChiemTinh).chiemTinhTongQuan);
        bundle.putString("tinhCach", chiemTinhModels.get(idChiemTinh).chiemTinhTinhCach);
        bundle.putString("diemManh", chiemTinhModels.get(idChiemTinh).chiemTinhDiemManh);
        bundle.putString("diemYeu", chiemTinhModels.get(idChiemTinh).chiemTinhDiemYeu);
        bundle.putString("giaDinh", chiemTinhModels.get(idChiemTinh).chiemTinhGiaDinh);
        bundle.putString("tinhYeu", chiemTinhModels.get(idChiemTinh).chiemTinhTinhYeu);
        bundle.putString("tinhDuc", chiemTinhModels.get(idChiemTinh).chiemTinhTinhDuc);
        bundle.putString("suNghiep", chiemTinhModels.get(idChiemTinh).chiemTinhSuNghiep);

        NamFragment namFragment = (NamFragment) viewPagerAdapter.instantiateItem(mViewPager, mViewPager.getCurrentItem());
        namFragment.setArguments(bundle);



        Bundle bundleNu = new Bundle();
        bundleNu.putString("tongQuanNu", chiemTinhModels.get(idChiemTinh+1).chiemTinhTongQuan);
        bundleNu.putString("tinhCachNu", chiemTinhModels.get(idChiemTinh).chiemTinhTinhCach);
        bundleNu.putString("diemManhNu", chiemTinhModels.get(idChiemTinh).chiemTinhDiemManh);
        bundleNu.putString("diemYeuNu", chiemTinhModels.get(idChiemTinh).chiemTinhDiemYeu);
        bundleNu.putString("giaDinhNu", chiemTinhModels.get(idChiemTinh).chiemTinhGiaDinh);
        bundleNu.putString("tinhYeuNu", chiemTinhModels.get(idChiemTinh).chiemTinhTinhYeu);
        bundleNu.putString("tinhDucNu", chiemTinhModels.get(idChiemTinh).chiemTinhTinhDuc);
        bundleNu.putString("suNghiepNu", chiemTinhModels.get(idChiemTinh).chiemTinhSuNghiep);

        NuFragment nuFragment = (NuFragment) viewPagerAdapter.instantiateItem(mViewPager, mViewPager.getCurrentItem());
        nuFragment.setArguments(bundleNu);

    }

    private void setUpChiemTinhModels() {
        String[] chiemTinhNames = {"Ma Kết", "Bảo Bình", "asdasd", "asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhTongQuan = {"Hair", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhTinhCach = {"tongqaunasd", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhDiemManh = {"tongqaunasd", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhDiemYeu = {"tongqaunasd", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhGiaDinh = {"tongqaunasd", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhTinhYeu = {"tongqaunasd", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhTinhDuc = {"asdasdhaiyeuem", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};
        String[] chiemTinhSuNghiep = {"tongqaunasd", "asdasdasd", "asdasdasdas","asdasd", "Ma Kết", "Bảo Bình", "asdasd","asdasd"};

        for(int i = 0; i < chiemTinhNames.length; i++){
            chiemTinhModels.add(new ChiemTinhModel(
                    chiemTinhNames[i],
                    chiemTinhTongQuan[i],
                    chiemTinhTinhCach[i],
                    chiemTinhDiemManh[i],
                    chiemTinhDiemYeu[i],
                    chiemTinhGiaDinh[i],
                    chiemTinhTinhYeu[i],
                    chiemTinhTinhDuc[i],
                    chiemTinhSuNghiep[i],
                    chiemTinhImages[i]
                    ));
        }
    }


}