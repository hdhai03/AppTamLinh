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

        long currentTime = System.currentTimeMillis();
        long duration = currentTime - postModel.postTime;

        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        String durationString;
        if (hours < 1) {
            durationString = minutes + " phút trước";
        } else if (hours < 24) {
            durationString = hours + " giờ trước";
        } else {
            durationString = days + " ngày trước";
        }

        holder.txtTime.setText(durationString);
        holder.textView.setText(postModel.postCauHoi);
        Glide.with(context)
                .load(postModel.postImgUrl1)
                .into(holder.imageView1);
        Glide.with(context)
                .load(postModel.postImgUrl2)
                .into(holder.imageView2);
        Glide.with(context)
                .load(postModel.postImgUrl3)
                .into(holder.imageView3);
        Glide.with(context)
                .load(postModel.postImgUrl4)
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
