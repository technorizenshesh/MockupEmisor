package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityPasswordBinding;


public class PasswordAct extends AppCompatActivity {
    ActivityPasswordBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_password);
        initViews();
    }

    private void initViews() {
        binding.btnCountinue.setOnClickListener(v -> {
            startActivity(new Intent(this,SendAct.class));
            finish();
        });
    }
}
