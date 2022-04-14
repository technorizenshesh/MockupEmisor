package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityOrderTrackBinding;

public class OrderTracking extends AppCompatActivity {
    ActivityOrderTrackBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_track);
        initViews();
    }

    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnDetail.setOnClickListener(v -> startActivity(new Intent(this,ProductVeriAct.class)));

    }
}
