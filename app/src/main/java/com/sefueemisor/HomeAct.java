package com.sefueemisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.sefueemisor.databinding.ActivityHomeBinding;
import com.sefueemisor.utils.DataManager;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

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



        binding.dashboradHome.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(this,ErrandSecondAct.class));
        });

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


    @Override
    protected void onResume() {
        super.onResume();
        if(DataManager.getInstance().getUserData(HomeAct.this)!=null){
            View header = binding.navView.getHeaderView(0);
            TextView tvUser = header.findViewById(R.id.tv_Username);
            CircleImageView img = header.findViewById(R.id.profile_image);

            tvUser.setText(DataManager.getInstance().getUserData(HomeAct.this).getResult().getUserName());

            Glide.with(HomeAct.this)
                    .load(DataManager.getInstance().getUserData(HomeAct.this).getResult().getImage())
                    .centerCrop()
                    .error(R.drawable.user_default)
                    .into(img);

        }

    }
}
