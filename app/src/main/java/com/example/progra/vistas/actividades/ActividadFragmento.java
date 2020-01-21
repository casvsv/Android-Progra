package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.progra.R;
import com.example.progra.vistas.fragmentos.FrgDos;
import com.example.progra.vistas.fragmentos.FrgUno;

public class ActividadFragmento extends AppCompatActivity implements View.OnClickListener, FrgUno.OnFragmentInteractionListener, FrgDos.OnFragmentInteractionListener {

    Button botonFrg1,botonFrg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividad_fragmento);
        cargarComponentes();
    }

    public void cargarComponentes() {
        botonFrg1 = findViewById(R.id.btnFrg1);
        botonFrg2 = findViewById(R.id.btnFrg2);
        botonFrg1.setOnClickListener(this);
        botonFrg2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFrg1:
                FrgUno fragmento1 = new FrgUno();
                FragmentTransaction transaccion1 = getSupportFragmentManager().beginTransaction();
                transaccion1.replace(R.id.contenedorFragmentos, fragmento1);
                transaccion1.commit();
                break;
            case R.id.btnFrg2:
                FrgDos fragmento2 = new FrgDos();
                FragmentTransaction transaccion2 = getSupportFragmentManager().beginTransaction();
                transaccion2.replace(R.id.contenedorFragmentos, fragmento2);
                transaccion2.commit();
                break;

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
