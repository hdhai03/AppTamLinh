package com.example.apptamlinh.ChiemTinhFeature.ChiemTinhFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apptamlinh.R;

public class NuFragment extends Fragment {
    public TextView txtTongQuan_Nu_ChiemTinhDetail;
    public TextView txtTinhCach_Nu_ChiemTinhDetail;
    public TextView txtDiemManh_Nu_ChiemTinhDetail;
    public TextView txtDiemYeu_Nu_ChiemTinhDetail;
    public TextView txtGiaDinh_Nu_ChiemTinhDetail;
    public TextView txtTinhYeu_Nu_ChiemTinhDetail;
    public TextView txtTinhDuc_Nu_ChiemTinhDetail;
    public TextView txtSuNghiep_Nu_ChiemTinhDetail;
    public NuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_nu, container, false);

        txtTongQuan_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtTongQuan_Nu_ChiemTinhDetail);
        txtTinhCach_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtTinhCach_Nu_ChiemTinhDetail);
        txtDiemManh_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtDiemManh_Nu_ChiemTinhDetail);
        txtDiemYeu_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtDiemYeu_Nu_ChiemTinhDetail);
        txtGiaDinh_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtGiaDinh_Nu_ChiemTinhDetail);
        txtTinhYeu_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtTinhYeu_Nu_ChiemTinhDetail);
        txtTinhDuc_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtTinhDuc_Nu_ChiemTinhDetail);
        txtSuNghiep_Nu_ChiemTinhDetail = mView.findViewById(R.id.txtSuNghiep_Nu_ChiemTinhDetail);

        Bundle bundleNu = getArguments();
        if (bundleNu != null) {
            String tongQuanNu = bundleNu.getString("tongQuanNu");
            String tinhCachNu = bundleNu.getString("tinhCachNu");
            String diemManhNu = bundleNu.getString("diemManhNu");
            String diemYeuNu = bundleNu.getString("diemYeuNu");
            String giaDinhNu = bundleNu.getString("giaDinhNu");
            String tinhYeuNu = bundleNu.getString("tinhYeuNu");
            String tinhDucNu = bundleNu.getString("tinhDucNu");
            String suNghiepNu = bundleNu.getString("suNghiepNu");

            // Lấy dữ liệu cho các trường khác từ bundle

            // Gán dữ liệu vào TextView
            txtTongQuan_Nu_ChiemTinhDetail.setText(tongQuanNu);
            txtTinhCach_Nu_ChiemTinhDetail.setText(tinhCachNu);
            txtDiemManh_Nu_ChiemTinhDetail.setText(diemManhNu);
            txtDiemYeu_Nu_ChiemTinhDetail.setText(diemYeuNu);
            txtGiaDinh_Nu_ChiemTinhDetail.setText(giaDinhNu);
            txtTinhYeu_Nu_ChiemTinhDetail.setText(tinhYeuNu);
            txtTinhDuc_Nu_ChiemTinhDetail.setText(tinhDucNu);
            txtSuNghiep_Nu_ChiemTinhDetail.setText(suNghiepNu);
        }
        return mView;
    }
}
