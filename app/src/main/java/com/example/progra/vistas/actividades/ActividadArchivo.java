package com.example.progra.vistas.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.progra.R;
import com.example.progra.vistas.fragmentos.FrgMI;
import com.example.progra.vistas.fragmentos.FrgProgramaArt;
import com.example.progra.vistas.fragmentos.FrgSD;

public class ActividadArchivo extends AppCompatActivity implements  FrgMI.OnFragmentInteractionListener, FrgSD.OnFragmentInteractionListener, FrgProgramaArt.OnFragmentInteractionListener{

    Button botonMI, botonSD, botonPrograma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_archivo);
        cargarComponentes();
    }
    public void cargarComponentes (){

    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ItemMI:
                FrgMI fragmentoMI = new FrgMI();
                FragmentTransaction transaccion1 = getSupportFragmentManager().beginTransaction();
                transaccion1.replace(R.id.ContenedorArtistas, fragmentoMI);
                transaccion1.commit();
                break;
            case R.id.ItemSD:
                FrgSD fragmentoSD = new FrgSD();
                FragmentTransaction transaccion2 = getSupportFragmentManager().beginTransaction();
                transaccion2.replace(R.id.ContenedorArtistas, fragmentoSD);
                transaccion2.commit();
                break;
            case R.id.ItemPrograma:
                FrgProgramaArt fragmentoP = new FrgProgramaArt();
                FragmentTransaction transaccion3 = getSupportFragmentManager().beginTransaction();
                transaccion3.replace(R.id.ContenedorArtistas, fragmentoP);
                transaccion3.commit();
                break;
        }
        return true;
    }
}
