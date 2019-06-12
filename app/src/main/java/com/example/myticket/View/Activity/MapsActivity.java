package com.example.myticket.View.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.myticket.Model.Network.DataModel.MapModel.Geometry;
import com.example.myticket.Model.Network.DataModel.MapModel.NearByFullModel;
import com.example.myticket.Model.Network.DataModel.MapModel.Result;
import com.example.myticket.Model.Network.Retrofit.ApiClient;
import com.example.myticket.Model.Network.Retrofit.onResponceInterface;
import com.example.myticket.R;
import com.example.myticket.helper.MapsHelper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, onResponceInterface {

    private GoogleMap mMap;
    private MapsHelper mapsHelper;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionGranted = false;
    private static final int LOCATION_REQUEST_CODE = 1111;
    private FusedLocationProviderClient mLocationClient;
    private LatLng currentLatLng;
    private static final float DEFAULT_ZOOM = 15f;
    private static final int RADIUS = 10000;
    private static final String PLACE_CINEMA = "movie_theater";
    private static final String KEYWORD_CINEMA = "cinema";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapsHelper = new MapsHelper(this);
        if (mapsHelper.isServicesOk(this)) {
            getLocationPermission();
        }


    }


    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_REQUEST_CODE);
        }
    }
    private void intializePlaces(){
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getResources().getString(R.string.google_maps_key));
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        if (autocompleteFragment != null) {
            autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(@NonNull Place place) {
                    Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());
                    if (place.getLatLng() != null && place.getName() != null)
                        moveCamera(place.getLatLng(),DEFAULT_ZOOM,place.getName());
                    else {
                        Log.e("PLACES_TAG","An error occurred in places");
                    }
                }

                @Override
                public void onError(Status status) {
                    Log.i("TAG", "An error occurred: " + status);
                    Toast.makeText(MapsActivity.this,"An error occurred, please try again.",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            Toast.makeText(this, "Please grant the requested permissions so the app can work correctly", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    initMap();
                }
            }


        }

    }

    private void getDeviceLocation() {
        mLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionGranted) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    String lat = String.valueOf(location.getLatitude());
                                    String lon = String.valueOf(location.getLongitude());
                                    currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                                    moveCamera(currentLatLng, DEFAULT_ZOOM, "You're Here!");
                                    sendNetworkRequest(lat +","+lon,RADIUS,PLACE_CINEMA,KEYWORD_CINEMA,
                                            getResources().getString(R.string.google_maps_key));
                                } else {
                                    Toast.makeText(MapsActivity.this, "Unable to locate current location," +
                                            " Please make sure that the location is activated in your settings.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        } catch (SecurityException e) {
            Log.e("MapsException", e.toString());
        }
    }

    private void sendNetworkRequest(String latLng, int radius, String placeCinema, String keywordCinema, String apiKey) {
        ApiClient apiClient = new ApiClient(latLng,radius,placeCinema,keywordCinema,apiKey,this,this);
       // apiClient.initializeClient();

    }

    private void moveCamera(LatLng latLng, float zoom, String line) {
        if (!line.equals("You're Here!"))
            mMap.addMarker(new MarkerOptions().position(latLng).title(line));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intializePlaces();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
    }



    @Override
    public void onSuccess(Object responce) {
        NearByFullModel nearByFullModel = (NearByFullModel) responce;
        locateNearByCinemas(nearByFullModel);

    }

    private void locateNearByCinemas(NearByFullModel nearByFullModel) {
        List<Result> results = nearByFullModel.getResults();
        for (int i = 0 ; i < results.size() ; i++){
            Result result = results.get(i);
            Geometry geometry = result.getGeometry();
            com.example.myticket.Model.Network.DataModel.MapModel.Location location = geometry.getLocation();
            LatLng latLng = new LatLng(location.getLat(),location.getLng());
            mMap.addMarker(new MarkerOptions().position(latLng).title(result.getName()));
        }

    }

    @Override
    public void onFail(Object responce) {

    }
}
