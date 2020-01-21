package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;

import com.example.progra.R;
import com.example.progra.modelo.Comunicador;
import com.example.progra.vistas.fragmentos.FrgEnviar;
import com.example.progra.vistas.fragmentos.FrgRecibir;

public class ActividadEscucharFragmento extends AppCompatActivity implements Comunicador, FrgRecibir.OnFragmentInteractionListener, FrgEnviar.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_escuchar_fragmento);
    }

    @Override
    public void responder(String datos) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FrgRecibir frg =(FrgRecibir) fragmentManager.findFragmentById(R.id.FrgRecibir);
        frg.cambiarTexto(datos);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
