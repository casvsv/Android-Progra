package com.example.progra.vistas.actividades;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        artista1.setNombres("John");
        artista1.setApellidos("Lennon");
        artista1.setNombreArtistico("John Lennon");
        artista1.setImgfoto(R.drawable.john);

        Artista artista2 = new Artista();
        artista2.setNombres("Paul");
        artista2.setApellidos("McCartney");
        artista2.setNombreArtistico("Paul McCartney");
        artista2.setImgfoto(R.drawable.paul);

        Artista artista3 = new Artista();
        artista3.setNombres("George");
        artista3.setApellidos("Harrison");
        artista3.setNombreArtistico("George Harrison");
        artista3.setImgfoto(R.drawable.george);

        Artista artista4 = new Artista();
        artista4.setNombres("Ringo");
        artista4.setApellidos("Starr");
        artista4.setNombreArtistico("Ringo Starr");
        artista4.setImgfoto(R.drawable.ringo);


        listaArtistas = new ArrayList<Artista>();
        listaArtistas.add(artista1);
        listaArtistas.add(artista2);
        listaArtistas.add(artista3);
        listaArtistas.add(artista4);

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

