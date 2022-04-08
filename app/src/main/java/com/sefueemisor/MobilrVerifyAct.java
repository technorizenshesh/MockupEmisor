package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityMobileVerifyBinding;


public class MobilrVerifyAct extends AppCompatActivity {
    ActivityMobileVerifyBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mobile_verify);
        initViews();
    }

    private void initViews() {
        binding.btnCountinue.setOnClickListener(v -> {
            startActivity(new Intent(this,PasswordAct.class));
            finish();
        });
    }
}
