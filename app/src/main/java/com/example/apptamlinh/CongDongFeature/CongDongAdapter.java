package com.example.apptamlinh.CongDongFeature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.R;

import java.util.ArrayList;

public class CongDongAdapter extends RecyclerView.Adapter<CongDongAdapter.MyViewHolder> {

    Context context;
    ArrayList<PostModel> postModelArrayList;

    public CongDongAdapter(Context context, ArrayList<PostModel> postModelArrayList) {
        this.context = context;
        this.postModelArrayList = postModelArrayList;
    }

    @NonNull
    @Override
    public CongDongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CongDongAdapter.MyViewHolder holder, int position) {
        PostModel postModel = postModelArrayList.get(position);

        holder.txtTime.setText(postModel.postTime);
        holder.textView.setText(postModel.postDetail);
        Glide.with(context)
                .load(postModel.postImg1) // Thay imageURL1 bằng URL của ảnh hoặc đường dẫn tệp ảnh
                .into(holder.imageView1);
        Glide.with(context)
                .load(postModel.postImg2)
                .into(holder.imageView2);
        Glide.with(context)
                .load(postModel.postImg3)
                .into(holder.imageView3);
        Glide.with(context)
                .load(postModel.postImg4)
                .into(holder.imageView4);
    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime, textView;
        ImageView imageView1, imageView2, imageView3, imageView4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txtTime);
            textView = itemView.findViewById(R.id.textView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView3 = itemView.findViewById(R.id.imageView3);
            imageView4 = itemView.findViewById(R.id.imageView4);

        }
    }
}
