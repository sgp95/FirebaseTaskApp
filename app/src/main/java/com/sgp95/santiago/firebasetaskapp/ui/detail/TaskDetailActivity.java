package com.sgp95.santiago.firebasetaskapp.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sgp95.santiago.firebasetaskapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskDetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    private double lat, lng;

    @BindView(R.id.detailTaskEdtTitle)
    EditText edtTitle;

    @BindView(R.id.detailTaskEdtDetail)
    EditText edtDetail;

    @BindView(R.id.detailTaskEdtPlace)
    EditText edtPlace;

    @BindView(R.id.detailTaskTxtDate)
    TextView txtDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);
        ButterKnife.bind(this);
        iniUI(getIntent());
        iniMap();

    }

    private void iniUI(Intent intent){
        edtTitle.setText(intent.getStringExtra("Title"));
        edtDetail.setText(intent.getStringExtra("Detail"));
        edtPlace.setText(intent.getStringExtra("Place"));
        txtDate.setText(intent.getStringExtra("Date")+" a las "+intent.getStringExtra("Hour"));
        lat = Double.parseDouble(intent.getStringExtra("Latitude"));
        lng = Double.parseDouble(intent.getStringExtra("Longitude"));

    }

    private void iniMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng event = new LatLng(lat, lng);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(event,16));
        googleMap.addMarker(new MarkerOptions()
                .title("Evento")
                .snippet("Aqui sera el evento")
                .position(event));
    }
}
