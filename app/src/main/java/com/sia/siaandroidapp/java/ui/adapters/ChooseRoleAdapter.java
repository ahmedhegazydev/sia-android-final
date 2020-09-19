package com.sia.siaandroidapp.java.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.interfaces.ItemClickListener;
import com.sia.siaandroidapp.java.model.Role;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChooseRoleAdapter extends RecyclerView.Adapter<ChooseRoleAdapter.VHChooseRole> {

    private Context context;
    private ArrayList<Role> roles;
    private ItemClickListener itemClickListener;

    public ChooseRoleAdapter(Context context, ArrayList<Role> roles) {
        this.context = context;
        this.roles = roles;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public VHChooseRole onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_choose_role, null);
        return new VHChooseRole(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VHChooseRole holder, int position) {

        Role role = roles.get(position);

        holder.tvMainTitle.setText(role.getMainTitle());
        holder.tvSubTitle.setText(role.getSubTitle());
        Picasso.get()
                .load(role.getImage())
                .into(holder.ivRoleImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return roles.size();
    }


    static class VHChooseRole extends RecyclerView.ViewHolder {
        TextView tvMainTitle;
        TextView tvSubTitle;
        ImageView ivRoleImage;

        VHChooseRole(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            tvMainTitle = itemView.findViewById(R.id.tvRoleMain);
            tvSubTitle = itemView.findViewById(R.id.tvRoleSub);
            ivRoleImage = itemView.findViewById(R.id.ivRoleImage);
            itemView.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClicked(position);
                    }
                }
            });
        }
    }
}
