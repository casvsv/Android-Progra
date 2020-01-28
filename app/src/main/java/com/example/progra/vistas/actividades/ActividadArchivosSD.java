package com.example.progra.vistas.actividades;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

public class ActividadArchivosSD extends AppCompatActivity implements View.OnClickListener {

    TextView datos;
    EditText cajaNombres, cajaApellidos,cajaNombreArtistico;
    Button botonEscribir, botonLeer;
    RecyclerView reciclerSD;
    ArtistaAdapter adapter;
    List<Artista> listaArtistas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_archivos_sd);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaNombres = findViewById(R.id.txtNombresSD);
        cajaApellidos= findViewById(R.id.txtApellidosSD);
        cajaNombreArtistico=findViewById(R.id.txtNombreArtisticoSD);
        botonEscribir = findViewById(R.id.btnAgregarSD);
        botonLeer = findViewById(R.id.btnListarSD);
        reciclerSD = findViewById(R.id.recyclerViewSD);


        botonEscribir.setOnClickListener(this);
        botonLeer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregarSD:
                BufferedWriter bufferedWriter = null;
                FileWriter fileWriter = null;
                try {
                    File file = Environment.getExternalStorageDirectory(); // ruta del SD
                    File ruta = new File(file.getAbsoluteFile(), "archivoSD.txt");
                    fileWriter = new FileWriter(ruta.getAbsoluteFile(), true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(cajaNombres.getText().toString()+","+cajaApellidos.getText().toString()+","+cajaNombreArtistico.getText().toString()+";");
                    bufferedWriter.close();
                    Toast.makeText(this,"Se ha guardado correctamente",Toast.LENGTH_SHORT);

                    //OutputStreamWriter escritor = new OutputStreamWriter(new FileOutputStream(file));
                }catch (Exception ex){
                    Log.e("Error SD", ex.getMessage());
                }
                break;
            case R.id.btnListarSD:
                try {
                    File ruta = Environment.getExternalStorageDirectory(); // ruta del SD
                    File file = new File(ruta.getAbsoluteFile(), "archivoSD.txt");
                    BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    datos.setText(lector.readLine());
                    adapter = new ArtistaAdapter(listaArtistas);
                    reciclerSD.setLayoutManager(new LinearLayoutManager(ActividadArchivosSD.this));
                    reciclerSD.setAdapter(adapter);
                    lector.close();

                }catch (Exception ex){
                    Log.e("Error SD", ex.getMessage());
                }
                break;
        }
    }
}
