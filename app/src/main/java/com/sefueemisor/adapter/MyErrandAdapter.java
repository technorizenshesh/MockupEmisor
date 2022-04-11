package com.sefueemisor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sefueemisor.R;
import com.sefueemisor.databinding.ItemErrandsBinding;

public class MyErrandAdapter extends RecyclerView.Adapter<MyErrandAdapter.MyViewHolder> {

    Context context;


    public MyErrandAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemErrandsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_errands,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemErrandsBinding binding;
        public MyViewHolder(@NonNull ItemErrandsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
