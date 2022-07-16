package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityLandingBinding;
import com.sefueemisor.databinding.ActivityMobileVerifyBinding;

public class LandingAct extends AppCompatActivity {
    ActivityLandingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_landing);
       initViews();
    }

    private void initViews() {
        binding.btnLogin.setOnClickListener(v -> startActivity(new Intent(this,LoginAct.class)));

        binding.btnSignup.setOnClickListener(v -> startActivity(new Intent(this,SignupAct.class)));


    }
}
