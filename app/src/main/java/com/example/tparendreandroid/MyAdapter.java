package com.example.tparendreandroid;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<Item> itemList;
    private final ItemClickListener itemClickListener;

    public MyAdapter(List<Item> itemList, ItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item currentItem = itemList.get(position);

        if (currentItem.getImageUriString() != null) {
            Glide.with(holder.imageView.getContext())
                    .load(currentItem.getImageUriString())
                    .placeholder(R.drawable.bonhomme)
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.bonhomme);
        }

        holder.doubleTextView.setText(String.valueOf(currentItem.getDoubleValue()));
        holder.stringTextView.setText(currentItem.getStringValue());
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView imageView;
        public final TextView doubleTextView;
        public final TextView stringTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.image_view);
            doubleTextView = itemView.findViewById(R.id.double_view);
            stringTextView = itemView.findViewById(R.id.string_view);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Item item = itemList.get(position);
                itemClickListener.onItemClick(item, position);
            }
        }
    }
    public interface ItemClickListener {
        void onItemClick(Item item, int position);
    }

}
