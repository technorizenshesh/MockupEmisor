package com.sefueemisor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityProDetailBinding;

public class ProDetailAct extends AppCompatActivity {
    ActivityProDetailBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pro_detail);
        initViews();
    }

    private void initViews() {

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnNext.setOnClickListener(v -> startActivity(new Intent(this,CommandCreateAct.class)));
    }






}
