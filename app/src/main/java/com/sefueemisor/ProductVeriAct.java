package com.sefueemisor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActvityOrderVerifyBinding;

public class ProductVeriAct extends AppCompatActivity {
    ActvityOrderVerifyBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.actvity_order_verify);
     initViews();
    }

    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnVerify.setOnClickListener(v -> {});
    }
}
