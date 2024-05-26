package com.example.apptamlinh.ProfileFeature.BXH;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptamlinh.CongDongFeature.RecyclerViewInterface;
import com.example.apptamlinh.R;
import com.example.apptamlinh.UserModel;

import java.util.ArrayList;

public class BxhAdapter extends RecyclerView.Adapter<BxhAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserModel> userModelArrayList;
    private final RecyclerViewInterface recyclerViewInterface;

    public BxhAdapter(Context context, ArrayList<UserModel> userModelArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.userModelArrayList = userModelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public BxhAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_bxh, parent, false);
        return new MyViewHolder(v, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull BxhAdapter.MyViewHolder holder, int position) {
        UserModel userModel = userModelArrayList.get(position);
        String number = String.valueOf(position + 1);
        holder.txtName.setText(userModel.getUserName());
        holder.txtNumber.setText(number);
        String score = String.valueOf(userModel.getUserScore());
        holder.txtScore.setText(score);
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtScore, txtNumber;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtScore = itemView.findViewById(R.id.txtScore);
            txtNumber = itemView.findViewById(R.id.txtNumber);
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
