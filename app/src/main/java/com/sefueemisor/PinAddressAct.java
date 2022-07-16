package com.sefueemisor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.sefueemisor.databinding.ActivityPinAddressBinding;
import com.sefueemisor.retrofit.Constant;
import com.sefueemisor.utils.DataManager;
import com.sefueemisor.utils.GPSTracker;
import com.sefueemisor.utils.SessionManager;


public class PinAddressAct extends AppCompatActivity {
    ActivityPinAddressBinding binding;
    double latitude = 0.0, longitude = 0.0;
    int AUTOCOMPLETE_REQUEST_CODE_ADDRESS = 101;
    String address="",city="";
    GPSTracker gpsTracker;
    GoogleMap mMap;
    int PERMISSION_ID = 44;
    private Animation myAnim;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_pin_address);
        initViews();
        bindMap();
    }

    private void initViews() {
        initLocation();

        binding.btnAddAddress.setOnClickListener(v -> {
            if(!address.equals(""))
          //  AddAddressFragment.tvArea.setVisibility(View.VISIBLE);
           // AddAddressFragment.tv1.setVisibility(View.VISIBLE);
          //  AddAddressFragment.v1.setVisibility(View.VISIBLE);
            ErrandSecondAct.etCity.setText(city);
            ErrandSecondAct.etAddress.setText(address);
            finish();
        });
    }


    private void initLocation() {
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        gpsTracker = new GPSTracker(PinAddressAct.this);
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                setCurrentLoc();
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        } else {
            requestPermissions();
        }
    }

    private void bindMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frg);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                mMap = map;
                mMap.clear();
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                if (gpsTracker != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()), 17.0f));
                }
                mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                    @Override
                    public void onCameraIdle() {
                        latitude = mMap.getCameraPosition().target.latitude;
                        longitude = mMap.getCameraPosition().target.longitude;
                        address = DataManager.getInstance().getAddress(PinAddressAct.this, latitude, longitude);
                        city = DataManager.getInstance().getAddressCity(PinAddressAct.this, latitude, longitude);
                        binding.tvAddress.setText(address);
                        binding.imgMarker.startAnimation(myAnim);
                        SessionManager.writeString(PinAddressAct.this, Constant.lat,String.valueOf(latitude));
                        SessionManager.writeString(PinAddressAct.this, Constant.lon,String.valueOf(longitude));
                        SessionManager.writeString(PinAddressAct.this, Constant.address,address);
                    }
                });
            }
        });
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setCurrentLoc();
            }
        }
    }


    private void setCurrentLoc() {
        if (gpsTracker != null) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
            binding.tvAddress.setText(DataManager.getInstance().getAddress(this, gpsTracker.getLatitude(), gpsTracker.getLongitude()));
        }
    }

}
