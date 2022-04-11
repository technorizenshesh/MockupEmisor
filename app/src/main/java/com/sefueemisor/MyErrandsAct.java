package com.sefueemisor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.adapter.MyErrandAdapter;
import com.sefueemisor.databinding.ActvityMyErrandBinding;

public class MyErrandsAct extends AppCompatActivity {
    ActvityMyErrandBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.actvity_my_errand);
        initViews();
    }

    private void initViews() {

        binding.ivBack.setOnClickListener(v -> finish());

        binding.rvErrand.setAdapter(new MyErrandAdapter(MyErrandsAct.this));
    }
}
