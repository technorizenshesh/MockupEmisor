package com.sefueemisor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityCoupansBinding;

public class CoupansAct extends AppCompatActivity {
    ActivityCoupansBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_coupans);
        initViews();
    }


    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());
    }
}
