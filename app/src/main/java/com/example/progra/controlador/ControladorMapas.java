package com.example.progra.controlador;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.example.progra.R;
import com.example.progra.modelo.Ruta;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ControladorMapas {
    Context context;
    GoogleMap mMap;


    public ControladorMapas(Context context) {
        this.context = context;
    }


    public List<Ruta> obtenerRutas(){
        List<Ruta> listaRuta = new ArrayList<>();
        InputStream input = context.getResources().openRawResource(R.raw.rutas_raw);
        BufferedReader lector = new BufferedReader(new InputStreamReader(input));
        try {

            String cadena = lector.readLine();
            while(cadena != null)
            {
                String[] rutas = cadena.split(";");
                String[] arrayArchivo;
                for (int i = 0; i < rutas.length; i++) {
                    arrayArchivo = rutas[i].split(",");
                    Ruta ruta = new Ruta();
                    ruta.setLat(Double.parseDouble(arrayArchivo[0]));
                    ruta.setLng(Double.parseDouble(arrayArchivo[1]));
                    ruta.setTitulo(arrayArchivo[2]);
                    ruta.setDescripcion(arrayArchivo[3]);
                    ruta.setIcono(arrayArchivo[4]);
                    ruta.setInformacion(arrayArchivo[5]);
                    ruta.setImagenLugar(arrayArchivo[6]);
                    listaRuta.add(ruta);
                }
                cadena = lector.readLine();

            }
            lector.close();
        }catch (Exception ex){
            Log.e("error: ", ex.getMessage());
        }
        return listaRuta;
    }


    public Circle trazarCirculo(LatLng latLng,GoogleMap googleMap){
        mMap=googleMap;
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(50)
                .strokeColor(Color.RED)
                .strokeWidth(4)
                .fillColor(Color.RED));
        return circle;
    }

    public Polyline trazarPolinea(List<LatLng> puntos, GoogleMap googleMap){
        mMap=googleMap;
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .addAll(puntos)
                .width(7)
                .color(Color.BLUE));
        return line;
    }

    public LatLngBounds Zoom(List<LatLng> puntos, GoogleMap googleMap){
            mMap=googleMap;
            LatLngBounds ZOOM = new LatLngBounds(
                puntos.get(0),puntos.get(puntos.size()-1));
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(ZOOM, 0));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ZOOM.getCenter(), 15));
        return ZOOM;
    }
}
