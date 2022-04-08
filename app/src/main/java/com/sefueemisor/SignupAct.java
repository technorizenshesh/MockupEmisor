package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivitySignupBinding;


public class SignupAct extends AppCompatActivity {
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        initViews();
    }

    private void initViews() {
        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(this,MobileRisAct.class));
            finish();
        });
    }
}
