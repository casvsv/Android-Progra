package com.example.progra.vistas.actividades;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.progra.R;
import com.example.progra.controlador.ControladorMapas;
import com.example.progra.modelo.Ruta;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.List;

public class ActividadMapas extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    ControladorMapas cM;
    private Button botonSatelite,botonTerreno,botonHibrido;
    private GoogleMap mMap;
    List<Ruta> listaRutas=new ArrayList<>();
    List<LatLng> puntos=new ArrayList<>();
    Ruta ruta= new Ruta();
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
        cM=new ControladorMapas(this);
        mMap = googleMap;
        listaRutas=cM.obtenerRutas();

        // Add a marker
        for (Ruta rut:listaRutas) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(rut.getLat(),rut.getLng())).title(rut.getTitulo()).snippet(rut.getDescripcion()).icon(BitmapDescriptorFactory.fromBitmap(obtenerIconos(rut.getIcono()))));
            puntos.add(new LatLng(rut.getLat(),rut.getLng()));
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Dialog dlgDetallesMapa = new Dialog(ActividadMapas.this);
                dlgDetallesMapa.setContentView(R.layout.dlg_descripcion_mapas);
                TextView titulo = dlgDetallesMapa.findViewById(R.id.lblDlgTituloMapa);
                TextView descripcion = dlgDetallesMapa.findViewById(R.id.lblDlgDescripcionMapa);
                ImageView imagen= dlgDetallesMapa.findViewById(R.id.imgDlgMapa);
                for (Ruta rut:listaRutas) {
                    if(marker.getTitle().equals(rut.getTitulo())){
                        titulo.setText(rut.getTitulo());
                        descripcion.setText(rut.getInformacion());
                        int id = getResources().getIdentifier(rut.getImagenLugar(), "drawable", getPackageName());
                        imagen.setImageResource(id);
                    }
                }
                dlgDetallesMapa.show();
                return true;
            }
        });

        LatLngBounds ZOOM = cM.Zoom(puntos,mMap);

        Circle circleInicio = cM.trazarCirculo(puntos.get(0),mMap);

        Circle circleFin = cM.trazarCirculo(puntos.get(puntos.size()-1),mMap);

        Polyline line = cM.trazarPolinea(puntos,mMap);
    }

    public Bitmap obtenerIconos(String iconName){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        return imageBitmap;
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
