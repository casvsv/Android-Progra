package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActividadMemoriaPrograma extends AppCompatActivity implements View.OnClickListener {

    TextView datos;
    Button boton;
    InputStream input;
    BufferedReader lector;
    ArtistaAdapter adapter;
    RecyclerView recyclerArtista;
    List<Artista> listaArtista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_programa);
        tomarControl();

    }

    private void tomarControl() {
        input = getResources().openRawResource(R.raw.archivo_raw);
        lector = new BufferedReader(new InputStreamReader(input));
        datos = findViewById(R.id.lblMemoriaPrograma);
        boton = findViewById(R.id.btnMemoriaPrograma);
        recyclerArtista = findViewById(R.id.RecyclerMemoriaPrograma);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.btnMemoriaPrograma):
                try {
                    listaArtista = new ArrayList<Artista>();
                    String cadena = lector.readLine();
                    String[] artis = cadena.split(";");
                    String[] arrayArchivo;
                    for (int i = 0; i < artis.length; i++) {
                        arrayArchivo = artis[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(arrayArchivo[0]);
                        artista.setApellidos(arrayArchivo[1]);
                        artista.setNombreArtistico(arrayArchivo[2]);
                        //artista.setImgfoto(Integer.parseInt(arrayArchivo[3]));
                        listaArtista.add(artista);
                    }
                    adapter = new ArtistaAdapter(listaArtista);
                    recyclerArtista.setLayoutManager(new LinearLayoutManager(ActividadMemoriaPrograma.this));
                    recyclerArtista.setAdapter(adapter);
                    lector.close();
                }catch (Exception ex){
                    Log.e("error: ", ex.getMessage());
                }
                break;
        }
    }
}
