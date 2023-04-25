package com.example.lab_11_gps_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.lab_11_gps_db.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        DatabaseConnector dbconnector = new DatabaseConnector(MapsActivity.this);
        dbconnector.open();

        Cursor result = dbconnector.getAllEntries();
        result.moveToFirst();
        int x = result.getCount();
        for (int i = 1; i<=x;++i){
            int latiIndex = result.getColumnIndex("LATITUDE");
            int longiIndex = result.getColumnIndex("LONGITUDE");
            int descIndex = result.getColumnIndex("DESCRIPTION");

            LatLng sydney =  new LatLng(Float.valueOf(result.getString(latiIndex)), Float.valueOf(result.getString(longiIndex)));
            String ads = result.getString(descIndex) + "\n";
            mMap.addMarker(new MarkerOptions().position(sydney).title(ads));
            float zoomLevel = 10.0f;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
            result.moveToNext();

        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                DatabaseConnector db = new DatabaseConnector(MapsActivity.this);
                db.open();
                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View viewInflated = inflater.inflate(R.layout.alert_dialog_text_entry, null);
                EditText et_note = (EditText) viewInflated.findViewById(R.id.et_note);
                builder.setCancelable(true);
                builder.setView(viewInflated);
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        db.insertEntry(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude), et_note.getText().toString());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(et_note.getText().toString()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    }

                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });

                builder.create();
                builder.show();


                }
});}
}




