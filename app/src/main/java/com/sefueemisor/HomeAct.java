package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.navigation.NavigationView;
import com.sefueemisor.databinding.ActivityHomeBinding;

public class HomeAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        initViews();
    }

    private void initViews() {

        binding.navView.setItemIconTintList(null);
      //  LoadNavMenu(R.menu.menu_drawer);

        binding.dashboradHome.drawHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        binding.navView.setNavigationItemSelectedListener(this);


    }

/*
    public void LoadNavMenu(int iMenu) {
        binding.navView.getMenu().clear();
        binding.navView.inflateMenu(iMenu);
        binding.navView.getMenu().setGroupVisible(R.id.SetupGroup, false);
    }*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
       // binding.navView.getMenu().setGroupVisible(R.id.SetupGroup, false);
        switch (item.getItemId()) {

            case R.id.nav_errand:
                startActivity(new Intent(this, ErrandSecondAct.class));
                break;

            case R.id.nav_manados:
                startActivity(new Intent(this, MyErrandsAct.class));
                break;

            case R.id.nav_paymentMethod:
                startActivity(new Intent(this, PaymentAct.class));
                break;

            case R.id.nav_myCoupon:
                startActivity(new Intent(this, CuponsOneAct.class));
                break;

            case R.id.nav_help:
                startActivity(new Intent(this, HelpAct.class));
                break;


            case R.id.nav_profile:
                startActivity(new Intent(this, ProfileAct.class));
                break;

        }


        return false;
    }


}
