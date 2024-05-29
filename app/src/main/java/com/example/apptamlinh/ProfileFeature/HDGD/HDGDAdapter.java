package com.example.apptamlinh.ProfileFeature.HDGD;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.CongDongFeature.PostModel;
import com.example.apptamlinh.CongDongFeature.RecyclerViewInterface;
import com.example.apptamlinh.R;

import java.util.ArrayList;


public class HDGDAdapter extends RecyclerView.Adapter<HDGDAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<PostModel> postModelArrayList;

    public HDGDAdapter(Context context, ArrayList<PostModel> postModelArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.postModelArrayList = postModelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HDGDAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);
        return new HDGDAdapter.MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HDGDAdapter.MyViewHolder holder, int position) {
        PostModel postModel = postModelArrayList.get(position);

        long currentTime = System.currentTimeMillis();
        long duration = currentTime - postModel.getPostTime();

        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        String durationString;
        if (hours < 1) {
            if (seconds < 60) {
                durationString = seconds + " giây trước";
            } else {
                durationString = minutes + " phút trước";
            }
        } else if (hours < 24) {
            durationString = hours + " giờ trước";
        } else {
            durationString = days + " ngày trước";
        }

        holder.txtTime.setText(durationString);
        holder.textView.setText(postModel.getPostCauHoi());
        Glide.with(context)
                .load(postModel.getPostImgUrl1())
                .into(holder.imageView1);
        Glide.with(context)
                .load(postModel.getPostImgUrl2())
                .into(holder.imageView2);
        Glide.with(context)
                .load(postModel.getPostImgUrl3())
                .into(holder.imageView3);
        Glide.with(context)
                .load(postModel.getPostImgUrl4())
                .into(holder.imageView4);
    }

    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime, textView;
        ImageView imageView1, imageView2, imageView3, imageView4;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txtTime);
            textView = itemView.findViewById(R.id.textView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView3 = itemView.findViewById(R.id.imageView3);
            imageView4 = itemView.findViewById(R.id.imageView4);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}