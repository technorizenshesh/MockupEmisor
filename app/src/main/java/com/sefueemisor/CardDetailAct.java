package com.sefueemisor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityCardBinding;

public class CardDetailAct extends AppCompatActivity {
    ActivityCardBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_card);
        initViews();
    }

    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());

    }
}
