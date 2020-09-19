package com.sia.siaandroidapp.java.ui.adapters.recycler;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.model.Subject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
        implements Filterable {

    private static final String TAG = "MyRecyclerViewAdapter";
    private List<Subject> subjectList;
    private List<Subject> subjectListAll;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Subject> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(subjectListAll);
            } else {
                for (Subject subject : subjectListAll) {
                    if (subject.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(subject);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            subjectList.clear();
            subjectList.addAll((Collection<? extends Subject>) results.values);
            notifyDataSetChanged();

        }
    };
    private Context mContext;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<Subject> data) {
        this.mInflater = LayoutInflater.from(context);
        this.subjectList = data;
        this.subjectListAll = new ArrayList<>(subjectList);
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_item_fav_subject, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.tvSubjectTitle.setText(subject.getTitle());

        if (holder.itemView.getTag() != null) {
            holder.ivSubjectImage.setImageResource(subject.getSelectedImage());
            setColor(holder.tvSubjectTitle, R.color.colorBlue);
        } else {
            holder.ivSubjectImage.setImageResource(subject.getUnSelectedImage());
            setColor(holder.tvSubjectTitle, R.color.dim_gray);
        }

    }

    private void setColor(TextView tvSubjectTitle, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tvSubjectTitle.setTextColor(mContext.getColor(color));
        } else {
            tvSubjectTitle.setTextColor(ContextCompat.getColor(mContext,
                    color));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    // convenience method for getting data at click position
    public Subject getItem(int id) {
        return subjectList.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvSubjectTitle;
        ImageView ivSubjectImage;

        ViewHolder(View itemView) {
            super(itemView);
            tvSubjectTitle = itemView.findViewById(R.id.tvSubjectTitle);
            ivSubjectImage = itemView.findViewById(R.id.ivSubjectImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    mClickListener.onItemClick(view, getAdapterPosition());
                }
            } else {
                Log.e(TAG, "onClick: null");
            }
        }
    }
}