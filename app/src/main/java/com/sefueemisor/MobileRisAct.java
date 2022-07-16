package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityMobileBinding;


public class MobileRisAct extends AppCompatActivity {
    ActivityMobileBinding binding;
    String email = "", userName = "", password = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile);
        initViews();
    }

    private void initViews() {
        if (getIntent() != null) {
            email = getIntent().getStringExtra("email");
            userName = getIntent().getStringExtra("userName");
            password = getIntent().getStringExtra("password");


        }


        binding.btnNext.setOnClickListener(v -> {

            if (binding.etMobile.getText().toString().equals("")) {
                Toast.makeText(MobileRisAct.this, getString(R.string.please_enter_phone_number), Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, MobilrVerifyAct.class)
                        .putExtra("email", email)
                        .putExtra("userName", userName)
                        .putExtra("password", password)
                        .putExtra("phone", binding.etMobile.getText().toString())
                        .putExtra("countryCode", binding.ccp.getSelectedCountryCode()));
                // finish();
            }


        });

    }
}
