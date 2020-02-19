package com.example.progra.vistas.actividades;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.progra.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class ActividadMapas extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private Button botonSatelite,botonTerreno,botonHibrido;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mapas);
        cargarComponentes();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void cargarComponentes(){
        botonSatelite=findViewById(R.id.btnSatelite);
        botonTerreno=findViewById(R.id.btnTerreno);
        botonHibrido=findViewById(R.id.btnHibrido);
        botonSatelite.setOnClickListener(this);
        botonTerreno.setOnClickListener(this);
        botonHibrido.setOnClickListener(this);
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

        // Add a marker in UNL
        LatLng UNL = new LatLng(-4.030283, -79.199477);
        LatLng Casa = new LatLng(-4.033393, -79.205809);
        LatLng Direccion2 = new LatLng(-4.034894, -79.205294);

        LatLngBounds CASA = new LatLngBounds(
                Casa,UNL);

        mMap.addMarker(new MarkerOptions().position(UNL).title("UNL"));
        mMap.addMarker(new MarkerOptions().position(Casa).title("Mi Casa"));
        mMap.addMarker(new MarkerOptions().position(Direccion2).title("Direccion2"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(Casa));
        mMap.setBuildingsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(CASA, 0));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CASA.getCenter(), 15));
        /*Polyline line = mMap.addPolyline(new PolylineOptions()
               .add(Casa,Direccion2,UNL)
               .width(5)
               .color(Color.BLUE));
        */

        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(UNL,Casa,Direccion2)
                .strokeColor(Color.BLACK)
                .fillColor(Color.GREEN));

        /*
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(Casa)
                .radius(1000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLACK));
        */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSatelite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.btnTerreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;

            case R.id.btnHibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
        }
    }
}
