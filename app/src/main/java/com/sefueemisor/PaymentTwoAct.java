package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityPaymentMethodTwoBinding;

public class PaymentTwoAct extends AppCompatActivity {
    
    ActivityPaymentMethodTwoBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_payment_method_two);
      initViews();
    }

    private void initViews() {

        binding.layoutVisa.setOnClickListener(v -> startActivity(new Intent(this,CardDetailAct.class)));

        binding.ivBack.setOnClickListener(v -> finish());
    }
}
