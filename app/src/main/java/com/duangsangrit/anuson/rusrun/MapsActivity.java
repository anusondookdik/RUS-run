package com.duangsangrit.anuson.rusrun;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
// รักติจูกกะลองติจูต
    private GoogleMap mMap;
    private double latRusADouble = 13.858919;
    private double lngRusADouble = 100.481719;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latUserADouble, lngUserADouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Setup Location Service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    } // Main Method


    @Override
    protected void onResume() {
        super.onResume();

        locationManager.removeUpdates(locationListener);
        latUserADouble = latRusADouble;
        lngUserADouble = lngRusADouble;

        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);

        if (networkLocation != null) {
            latUserADouble = networkLocation.getLatitude();
            lngUserADouble = networkLocation.getLongitude();
        }

        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latUserADouble = gpsLocation.getLatitude();
            lngUserADouble = gpsLocation.getLongitude();

        }



    } //หยุดแอพไว้ชั่วคราว

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);

    }

    public Location myFindLocation(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {
            locationManager.requestLocationUpdates(strProvider, 1000 , 10 ,locationListener);
            location = locationManager.getLastKnownLocation(strProvider);

        } else {
            Log.d("RusV2","Cannot Find Location");

        }

        return location;
    }



    //ซ้อนคลาส
    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latUserADouble = location.getLatitude();
            lngUserADouble = location.getLongitude();

        } //onLocationChange

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //เซ็กอัพ ศูนย์กลาง แผนที่

        LatLng latLng = new LatLng(latRusADouble,lngRusADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));

        //ลูป
        myLoop();


    } //onMapReady

    private void myLoop() {

        //To Do
        Log.d("RusV3", "latUser ==> " + latUserADouble);
        Log.d("RunV3", "lngUser ==> " + lngUserADouble);


        //Edit Lat,lng,on Server

        editLatLngOnServer();


        //Delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myLoop();
            }
        }, 3000);


    } //My Loop

    private void editLatLngOnServer() {

        String urlPHP = "http://swiftcodingthai.com/rus/edit_location_master.php";

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("id", "lest")
                .add("Lat", Double.toString(latUserADouble))
                .add("Lng",Double.toString(lngUserADouble))
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });

    }

}   // Main Class
