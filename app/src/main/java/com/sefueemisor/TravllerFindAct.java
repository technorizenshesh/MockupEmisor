package com.sefueemisor;

import static com.google.android.gms.maps.model.JointType.ROUND;

import android.os.Bundle;

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
import com.sefueemisor.databinding.ActivityFindTravellerBinding;
import com.sefueemisor.fragment.DetailsBottomSheet;
import com.sefueemisor.map.DrawPollyLine;

import java.util.ArrayList;

public class TravllerFindAct extends AppCompatActivity implements OnMapReadyCallback {
    ActivityFindTravellerBinding binding;
    private MarkerOptions PicUpMarker, DropOffMarker;
    SupportMapFragment mapFragment ;
    private LatLng PickUpLatLng, DropOffLatLng;
    private PolylineOptions lineOptions;
    GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_find_traveller);
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

        binding.layoutBottom.setOnClickListener(v -> {
            new DetailsBottomSheet().show(getSupportFragmentManager(),"");
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
        DrawPollyLine.get(TravllerFindAct.this).setOrigin(PickUpLatLng)
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


}
