package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sefueemisor.databinding.ActivityProfileBinding;
import com.sefueemisor.utils.SessionManager;

public class ProfileAct extends AppCompatActivity {
    ActivityProfileBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        initViews();
    }

    private void initViews() {

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnLogout.setOnClickListener(v -> SessionManager.clear(ProfileAct.this,""));

        binding.btnTerms.setOnClickListener(v -> startActivity(new Intent(this,WebViewAct.class)
                .putExtra("title","Términos y condiciones").putExtra("url","https://www.sefue.com.co/wp-content/uploads/2021/06/TERMINOS-Y-CONDICIONES-REGLAMENTO-DE-USE-EMISOR.pdf")));

        binding.btnAbout.setOnClickListener(v -> startActivity(new Intent(this,WebViewAct.class)
        .putExtra("title","Acerca de Sefue").putExtra("url","https://www.sefue.com.co/")));


        binding.layoutProfile.setOnClickListener(v -> startActivity(new Intent(ProfileAct.this,AccountUpdateAct.class)));


    }
}
