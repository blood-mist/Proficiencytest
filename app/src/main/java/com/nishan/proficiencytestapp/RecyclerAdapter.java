package com.nishan.proficiencytestapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nishan.proficiencytestapp.models.RowsItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private List<RowsItem> results = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        RowsItem item = results.get(position);

        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());
            Glide.with(holder.itemView)
                    .load(item.getImageHref())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC) // NONE if you load from sdcard
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .centerCrop()
                    .into(holder.smallThumbnailImageView);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<RowsItem> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView smallThumbnailImageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txt_title);
            descriptionTextView = itemView.findViewById(R.id.desciption);
            smallThumbnailImageView = itemView.findViewById(R.id.imageView);
        }
    }
}