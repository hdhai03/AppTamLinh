package com.example.apptamlinh.ProfileFeature.XemYNghia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptamlinh.CongDongFeature.RecyclerViewInterface;
import com.example.apptamlinh.R;
import com.example.apptamlinh.TarotFeature.TarotHNModel;

import java.util.ArrayList;


public class YNghiaAdapter extends RecyclerView.Adapter<YNghiaAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<TarotHNModel> TarotHNModels;

    public YNghiaAdapter(RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<TarotHNModel> TarotHNModels) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.TarotHNModels = TarotHNModels;
    }

    @NonNull
    @Override
    public YNghiaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_y_nghia, parent, false);
        return new MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull YNghiaAdapter.MyViewHolder holder, int position) {
        TarotHNModel TarotHNModel = TarotHNModels.get(position);
        Glide.with(context)
                .load(TarotHNModel.getImg())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 78;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

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
