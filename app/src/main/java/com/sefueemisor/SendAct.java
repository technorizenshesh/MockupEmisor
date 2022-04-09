package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivitySendBinding;


public class SendAct extends AppCompatActivity {
    ActivitySendBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_send);
      initViews();
    }

    private void initViews() {
        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(this,HomeAct.class));
            finish();
        });
    }
}
