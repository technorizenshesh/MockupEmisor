package com.sefueemisor;

import static com.google.android.gms.maps.model.JointType.ROUND;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.sefueemisor.databinding.ActivityFollowCommandBinding;
import com.sefueemisor.fragment.DetailsBottomSheet;
import com.sefueemisor.map.DrawPollyLine;

import java.util.ArrayList;

public class FollowCommandAct extends AppCompatActivity implements OnMapReadyCallback {
    ActivityFollowCommandBinding binding;
    private MarkerOptions PicUpMarker, DropOffMarker;
    SupportMapFragment mapFragment ;
    private LatLng PickUpLatLng, DropOffLatLng;
    private PolylineOptions lineOptions;
    GoogleMap mMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_follow_command);
     initViews();
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initViews() {

        PicUpMarker = new MarkerOptions().title("Departure Address")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_marker));
        DropOffMarker = new MarkerOptions().title("Arrival Address")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker));

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnNext.setOnClickListener(v -> {dialogValidate();
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        PickUpLatLng = new LatLng(26.214396,78.0508112);
        DropOffLatLng = new LatLng(22.7242284,75.7237618);
        // DrawPolyLine();
    }



    private void DrawPolyLine() {
        DrawPollyLine.get(FollowCommandAct.this).setOrigin(PickUpLatLng)
                .setDestination(DropOffLatLng).execute(new DrawPollyLine.onPolyLineResponse() {
            @Override
            public void Success(ArrayList<LatLng> latLngs) {
                mMap.clear();
                lineOptions = new PolylineOptions();
                lineOptions.addAll(latLngs);
                lineOptions.width(10);
                lineOptions.color(R.color.color_black_light);
                lineOptions.startCap(new SquareCap());
                lineOptions.endCap(new SquareCap());
                lineOptions.jointType(ROUND);
                mMap.addPolyline(lineOptions);
                AddDefaultMarker();
            }
        });
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(17).build();
    }


    public void AddDefaultMarker() {
        if (mMap != null) {
            mMap.clear();
            if (lineOptions != null)
                mMap.addPolyline(lineOptions);
            if (PickUpLatLng != null) {
                PicUpMarker.position(PickUpLatLng);
                mMap.addMarker(PicUpMarker);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(PickUpLatLng)));
            }
            if (DropOffLatLng != null) {
                DropOffMarker.position(DropOffLatLng);
                mMap.addMarker(DropOffMarker);
            }



        }
    }



    public void dialogValidate(){
        Dialog dialog = new Dialog(FollowCommandAct.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_validate);

        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        TextView btnOne = dialog.findViewById(R.id.btnOne);

        ivCancel.setOnClickListener(v -> {dialog.dismiss();});

        btnOne.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(FollowCommandAct.this,OrderTracking.class));

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
