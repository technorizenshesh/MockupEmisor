package com.sefueemisor.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sefueemisor.ChooseTravllerAct;
import com.sefueemisor.CommandCreateAct;
import com.sefueemisor.R;
import com.sefueemisor.databinding.FragmentDetailsBinding;

public class DetailsBottomSheet extends BottomSheetDialogFragment {
    public String TAG = "DetailsBottomSheet";
    FragmentDetailsBinding binding;
    BottomSheetDialog dialog;
    private BottomSheetBehavior<View> mBehavior;


    public DetailsBottomSheet() {
    }

/*
    public DetailsBottomSheet callBack(InfoListener listener) {
        this.listener = listener;
        return this;
    }*/


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_details, null, false);
        dialog.setContentView(binding.getRoot());
        mBehavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        initViews();
        return  dialog;
    }

    private void initViews() {
         binding.btnNext.setOnClickListener(v -> dialogNotFound());
    }


    public void dialogNotFound(){
        Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_service_not_found);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        TextView btnOne = dialog.findViewById(R.id.btnOne);

        ivCancel.setOnClickListener(v -> {dialog.dismiss();});

        ivCancel.setOnClickListener(v -> {
            dialog.dismiss();

        });

        btnOne.setOnClickListener(v -> {
            dialog.dismiss();
            dialog();

        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.show();
    }


    public void dialog(){
        Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_waiting_list);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        TextView btnOne = dialog.findViewById(R.id.btnOne);

        ivCancel.setOnClickListener(v -> {dialog.dismiss();});

        ivCancel.setOnClickListener(v -> {
            dialog.dismiss();

        });

        btnOne.setOnClickListener(v -> {
            dialog.dismiss();
            dialogCancelService();

        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.show();
    }



    public void dialogCancelService(){
        Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cancel_service);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        TextView btnOne = dialog.findViewById(R.id.btnOne);

        ivCancel.setOnClickListener(v -> {dialog.dismiss();});

        ivCancel.setOnClickListener(v -> {
            dialog.dismiss();

        });

        btnOne.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(getActivity(), ChooseTravllerAct.class));

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
