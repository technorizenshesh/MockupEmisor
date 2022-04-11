package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityCuponesOneBinding;

public class CuponsOneAct extends AppCompatActivity {
    ActivityCuponesOneBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cupones_one);
        initViews();
    }

    private void initViews() {

        binding.ivDetail.setOnClickListener(v -> startActivity(new Intent(this,CuponsTwoAct.class)));

        binding.ivBack.setOnClickListener(v -> finish());
    }
}
