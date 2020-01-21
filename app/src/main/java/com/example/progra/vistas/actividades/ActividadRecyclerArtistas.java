package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActividadRecyclerArtistas extends AppCompatActivity  {

    Button botonDatos;
    RecyclerView recyclerViewArtistas;
    ArtistaAdapter adapter;
    List<Artista> listaArtistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activivdad_recycler_artistas);
        tomarControl();
        cargarRecycler();
    }


    private void tomarControl(){
        recyclerViewArtistas = findViewById(R.id.recyclerArtista);
    }
    private void cargarRecycler(){
        Artista artista1 = new Artista();
        artista1.setNombres("Luis");
        artista1.setApellidos("Miguel");
        artista1.setNombreArtistico("luismiguel");
        artista1.setImgfoto(R.drawable.gato);

        Artista artista2 = new Artista();
        artista2.setNombres("Don");
        artista2.setApellidos("Medardo");
        artista2.setNombreArtistico("donmedardo");
        artista2.setImgfoto(R.drawable.perro);

        Artista artista3 = new Artista();
        artista3.setNombres("Enrique");
        artista3.setApellidos("Bumbury");
        artista3.setNombreArtistico("enriquebumbury");
        artista3.setImgfoto(R.drawable.aguila);


        listaArtistas = new ArrayList<Artista>();
        listaArtistas.add(artista1);
        listaArtistas.add(artista2);
        listaArtistas.add(artista3);

        adapter = new ArtistaAdapter(listaArtistas);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetallesArtistas(v);
            }
        });
        recyclerViewArtistas.setAdapter(adapter);

    }
    public void DetallesArtistas(View view){
        Dialog dlgDetalles = new Dialog(ActividadRecyclerArtistas.this);
        dlgDetalles.setContentView(R.layout.dlg_datos_artistas);
        TextView nombresDetalles = dlgDetalles.findViewById(R.id.lblDatos1);
        TextView apellidosDetalles = dlgDetalles.findViewById(R.id.lblDatos2);
        ImageView fotoDetalles= dlgDetalles.findViewById(R.id.imgDlgDetalles);
        nombresDetalles.setText("Nombres: "+ listaArtistas.get(recyclerViewArtistas.getChildAdapterPosition(view)).getNombres());
        apellidosDetalles.setText("Apellidos: "+ listaArtistas.get(recyclerViewArtistas.getChildAdapterPosition(view)).getApellidos());
        fotoDetalles.setImageResource(listaArtistas.get(recyclerViewArtistas.getChildAdapterPosition(view)).getImgfoto());
        dlgDetalles.show();
    }
}

