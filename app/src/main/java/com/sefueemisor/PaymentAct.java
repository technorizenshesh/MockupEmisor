package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActvityPaymentMethodBinding;

public class PaymentAct extends AppCompatActivity {
    ActvityPaymentMethodBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.actvity_payment_method);
      initViews();
    }

    private void initViews() {
        binding.btnAdd.setOnClickListener(v -> startActivity(new Intent(this,PaymentTwoAct.class)));

        binding.ivBack.setOnClickListener(v -> finish());
    }
}
