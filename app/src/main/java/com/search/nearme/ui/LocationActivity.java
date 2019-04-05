package com.search.nearme.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.search.nearme.R;
import com.search.nearme.utils.AppConst;
import com.google.maps.android.PolyUtil;
import java.util.List;


public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private double destLat,destLng;

    private String mode;

    private LocationActivityViewModel activityViewModel;


    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        unbinder = ButterKnife.bind(this);

        if(getIntent()!=null){
            destLat = getIntent().getDoubleExtra("dest_lat",0);
            destLng = getIntent().getDoubleExtra("dest_lng",0);
            mode = getIntent().getStringExtra("mode");
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setUpViewModel();



    }

    private void setUpViewModel() {
        activityViewModel = ViewModelProviders.of(this).get(LocationActivityViewModel.class);

        activityViewModel.polyLine.observe(this,value->{
            List<LatLng> decodedPath = PolyUtil.decode(value);

            mMap.addPolyline(new PolylineOptions().addAll(decodedPath)).setColor(Color.GREEN);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityViewModel.fetchRoute(AppConst.getLocationValue(),String.valueOf(destLat+","+destLng),mode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String[] locatnArry = AppConst.getLocationValue().split(",");
        // Add a marker in Sydney and move the camera
        LatLng currentLocation = new LatLng(Double.parseDouble(locatnArry[0]), Double.parseDouble(locatnArry[1]));
        LatLng destLocation = new LatLng(destLat,destLng);
        mMap.addMarker(new MarkerOptions().position(destLocation).title(getString(R.string.destination)));
        mMap.addMarker(new MarkerOptions().position(currentLocation).title(getString(R.string.current_location))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLocation,16.5f);
        mMap.animateCamera(update, GoogleMap.MAP_TYPE_NORMAL,null);

    }

    public void terrainMap(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    public void statelliteMap(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
}
