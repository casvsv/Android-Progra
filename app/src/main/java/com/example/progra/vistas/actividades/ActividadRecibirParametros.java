package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.progra.R;

public class ActividadRecibirParametros extends AppCompatActivity  {

    TextView nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_recibir_parametros);
        RecibirParametros();
    }
    private void RecibirParametros( ){

        nombre = findViewById(R.id.lblNombreRecibirParametro);
        apellido = findViewById(R.id.lblApellidoRecibirParametro);

        Bundle bundle = this.getIntent().getExtras();
        nombre.setText(bundle.getString("Nombre"));
        apellido.setText(bundle.getString("Apellido"));

    }


}
