package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityCreateErrandBinding;

public class CreateErrandAct extends AppCompatActivity {
    ActivityCreateErrandBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_errand);
        initViews();
    }

    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());

       // binding.btnNext.setOnClickListener(v -> startActivity(new Intent(this,ErrandSecondAct.class)));
    }
}
