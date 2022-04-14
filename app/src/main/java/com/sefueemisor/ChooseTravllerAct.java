package com.sefueemisor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.adapter.TravellerAdapter;
import com.sefueemisor.databinding.ActivityChooseTravllerBinding;

public class ChooseTravllerAct extends AppCompatActivity {
    ActivityChooseTravllerBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_choose_travller);
      initViews();
    }

    private void initViews() {
        binding.ivBack.setOnClickListener(v -> finish());

        binding.rvTraveller.setAdapter(new TravellerAdapter(ChooseTravllerAct.this));
    }
}
