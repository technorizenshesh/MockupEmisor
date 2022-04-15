package com.sefueemisor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityGiveRateBinding;

public class GiveRattingAct extends AppCompatActivity {
    ActivityGiveRateBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_give_rate);
     initViews();
    }

    private void initViews() {

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnNext.setOnClickListener(v -> {dialogNews();});
    }


    public void dialogNews(){
        Dialog dialog = new Dialog(GiveRattingAct.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_news);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        TextView btnOne = dialog.findViewById(R.id.btnOne);

        ivCancel.setOnClickListener(v -> {dialog.dismiss();});

        btnOne.setOnClickListener(v -> {
            dialog.dismiss();


        });
        ivCancel.setOnClickListener(v -> {
            dialog.dismiss();

        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.show();
    }


}
