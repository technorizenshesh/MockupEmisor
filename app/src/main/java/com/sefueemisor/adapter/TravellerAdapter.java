package com.sefueemisor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sefueemisor.R;
import com.sefueemisor.TravellerDetailAct;
import com.sefueemisor.databinding.ItemErrandsBinding;
import com.sefueemisor.databinding.ItemTravellerBinding;

public class TravellerAdapter extends RecyclerView.Adapter<TravellerAdapter.MyViewHolder> {

    Context context;


    public TravellerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTravellerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_traveller,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.itemView.setOnClickListener(v -> context.startActivity(new Intent(context, TravellerDetailAct.class)));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemTravellerBinding binding;
        public MyViewHolder(@NonNull ItemTravellerBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;


        }
    }
}
