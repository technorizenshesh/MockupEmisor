package com.sefueemisor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityCommandBinding;

public class CommandCreateAct extends AppCompatActivity {
    ActivityCommandBinding binding;
    public static int FIND_TRAVELLER_TIME_OUT = 3000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding =  DataBindingUtil.setContentView(this,R.layout.activity_command);
       initViews();
    }

    private void initViews() {

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnNext.setOnClickListener(v -> dialogOne());
    }


    public void dialogOne(){
        Dialog dialog = new Dialog(CommandCreateAct.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_one);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        TextView btnOne = dialog.findViewById(R.id.btnOne);

        ivCancel.setOnClickListener(v -> {dialog.dismiss();});

        ivCancel.setOnClickListener(v -> {
            dialog.dismiss();
            dialogTwo();
        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.show();
    }


    public void dialogTwo(){
        Dialog dialog = new Dialog(CommandCreateAct.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_two);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        ivCancel.setOnClickListener(v -> {});



         processNextActivity();



        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.show();
    }



    private void processNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(CommandCreateAct.this, TravllerFindAct.class));
                finish();
            }


        }, FIND_TRAVELLER_TIME_OUT);
    }


}
