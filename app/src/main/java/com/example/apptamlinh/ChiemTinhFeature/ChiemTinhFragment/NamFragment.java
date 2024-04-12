package com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptamlinh.R;

public class NamFragment extends Fragment {
    public TextView txtTongQuan_Nam_ChiemTinhDetail;
    public TextView txtTinhCach_Nam_ChiemTinhDetail;
    public TextView txtDiemManh_Nam_ChiemTinhDetail;
    public TextView txtDiemYeu_Nam_ChiemTinhDetail;
    public TextView txtGiaDinh_Nam_ChiemTinhDetail;
    public TextView txtTinhYeu_Nam_ChiemTinhDetail;
    public TextView txtTinhDuc_Nam_ChiemTinhDetail;
    public TextView txtSuNghiep_Nam_ChiemTinhDetail;

    public NamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_nam, container, false);

        txtTongQuan_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtTongQuan_Nam_ChiemTinhDetail);
        txtTinhCach_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtTinhCach_Nam_ChiemTinhDetail);
        txtDiemManh_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtDiemManh_Nam_ChiemTinhDetail);
        txtDiemYeu_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtDiemYeu_Nam_ChiemTinhDetail);
        txtGiaDinh_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtGiaDinh_Nam_ChiemTinhDetail);
        txtTinhYeu_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtTinhYeu_Nam_ChiemTinhDetail);
        txtTinhDuc_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtTinhDuc_Nam_ChiemTinhDetail);
        txtSuNghiep_Nam_ChiemTinhDetail = mView.findViewById(R.id.txtSuNghiep_Nam_ChiemTinhDetail);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String tongQuan = bundle.getString("tongQuan");
            String tinhCach = bundle.getString("tinhCach");
            String diemManh = bundle.getString("diemManh");
            String diemYeu = bundle.getString("diemYeu");
            String giaDinh = bundle.getString("giaDinh");
            String tinhYeu = bundle.getString("tinhYeu");
            String tinhDuc = bundle.getString("tinhDuc");
            String suNghiep = bundle.getString("suNghiep");

            // Lấy dữ liệu cho các trường khác từ bundle

            // Gán dữ liệu vào TextView
            txtTongQuan_Nam_ChiemTinhDetail.setText(tongQuan);
            txtTinhCach_Nam_ChiemTinhDetail.setText(tinhCach);
            txtDiemManh_Nam_ChiemTinhDetail.setText(diemManh);
            txtDiemYeu_Nam_ChiemTinhDetail.setText(diemYeu);
            txtGiaDinh_Nam_ChiemTinhDetail.setText(giaDinh);
            txtTinhYeu_Nam_ChiemTinhDetail.setText(tinhYeu);
            txtTinhDuc_Nam_ChiemTinhDetail.setText(tinhDuc);
            txtSuNghiep_Nam_ChiemTinhDetail.setText(suNghiep);
        }
        return mView;
    }

}