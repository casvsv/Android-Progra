package com.example.progra.vistas.actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.progra.R;
import com.example.progra.modelo.Artista;
import com.example.progra.vistas.adapter.ArtistaAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ActividadMemoriaInterna extends AppCompatActivity implements View.OnClickListener {

    String informacion;
    Button botonguardar, botonBuscar, botonCargarImagen;
    EditText cajaNombres, cajaApellidos, cajaNombreArtistico;
    TextView datos, cajaFotoPath;
    RecyclerView recyclerArtistas;
    ArtistaAdapter adapter;
    List<Artista> listaArtistas;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_memoria_interna);
        tomarControl();
    }

    public void tomarControl() {


        imagen = (ImageView) findViewById(R.id.imgFotoSeleccionada);
        botonguardar = findViewById(R.id.btnGuardar);
        botonBuscar = findViewById(R.id.btnBuscar);
        botonCargarImagen = findViewById(R.id.btnCargarImg);

        cajaNombres = findViewById(R.id.txtNombres);
        cajaApellidos = findViewById(R.id.txtApellidos);
        cajaNombreArtistico = findViewById(R.id.txtNombreArtistico);
        datos = findViewById(R.id.lblDatosMI);
        recyclerArtistas = findViewById(R.id.RecyclerListado);

        cajaFotoPath = findViewById(R.id.lblpathdeFoto);

        botonCargarImagen.setOnClickListener(this);
        botonguardar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCargarImg:
                cargarImg();
                break;
            case R.id.btnGuardar:
                try {
                    Artista artista = new Artista();
                    artista.setNombres(cajaNombres.getText().toString());
                    artista.setApellidos(cajaApellidos.getText().toString());
                    artista.setNombreArtistico(cajaNombreArtistico.getText().toString());
                    artista.setImgfoto(R.id.imgFotoSeleccionada);
                    artista.setPathFoto(cajaFotoPath.getText().toString());
                    OutputStreamWriter escritor = new OutputStreamWriter(openFileOutput("archivo21.txt", Context.MODE_APPEND));
                    escritor.write(artista.getNombres() + "," + artista.getApellidos() + "," + artista.getNombreArtistico() + "," + artista.getPathFoto() + ","+ artista.getImgfoto() +";");
                    Dialog msgGuardar = new Dialog(ActividadMemoriaInterna.this);
                    msgGuardar.setContentView(R.layout.dlg_msg_guardado);
                    TextView msg = msgGuardar.findViewById(R.id.lblMSGguardado);
                    msg.setText("Mensaje guardado");
                    msg.getText();
                    msgGuardar.show();
                    escritor.close();
                } catch (Exception ex) {
                    Dialog msgGuardar = new Dialog(this);
                    msgGuardar.setContentView(R.layout.dlg_msg_guardado);
                    TextView msg = msgGuardar.findViewById(R.id.lblMSGguardado);
                    msg.setText("No se pudo guardar el mensaje");
                    msg.getText();
                    msgGuardar.show();
                    Log.e("archivoMI", "error de escritura" + ex.getMessage());
                }
                break;
            case R.id.btnBuscar:
                try {
                    listaArtistas = new ArrayList<Artista>();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(openFileInput("archivo21.txt")));
                    String lineas = lector.readLine();
                    String[] artis = lineas.split(";");
                    String[] arrayArchivo;
                    for (int i = 0; i < artis.length; i++) {
                        arrayArchivo = artis[i].split(",");
                        Artista artista = new Artista();
                        artista.setNombres(arrayArchivo[0]);
                        artista.setApellidos(arrayArchivo[1]);
                        artista.setNombreArtistico(arrayArchivo[2]);
                        artista.setPathFoto(Uri.parse(arrayArchivo[3]).toString());
                        listaArtistas.add(artista);
                    }
                    adapter = new ArtistaAdapter(listaArtistas);
                    recyclerArtistas.setLayoutManager(new LinearLayoutManager(ActividadMemoriaInterna.this));
                    adapter.setOnclickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cargarDialogo(v);
                        }
                    });
                    recyclerArtistas.setAdapter(adapter);
                    lector.close();
                } catch (Exception ex) {
                    Log.e("archivoMI", "error de lectura" + ex.getMessage());
                    break;

                }
        }

    }

    private void cargarDialogo(View view) {
        Dialog dlgDetalles = new Dialog(ActividadMemoriaInterna.this);
        dlgDetalles.setContentView(R.layout.dlg_datos_artistas);
        TextView nombresDetalles = dlgDetalles.findViewById(R.id.lblDatos1);
        TextView apellidosDetalles = dlgDetalles.findViewById(R.id.lblDatos2);
        ImageView fotoDetalles= dlgDetalles.findViewById(R.id.imgDlgDetalles);
        nombresDetalles.setText("Nombres: "+ listaArtistas.get(recyclerArtistas.getChildAdapterPosition(view)).getNombres());
        apellidosDetalles.setText("Apellidos: "+ listaArtistas.get(recyclerArtistas.getChildAdapterPosition(view)).getApellidos());
        fotoDetalles.setImageURI(Uri.parse(listaArtistas.get(recyclerArtistas.getChildAdapterPosition(view)).getPathFoto()));
        dlgDetalles.show();
    }

    private void cargarImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri path = data.getData();
            informacion = path.toString();
            imagen.setImageURI(path);
            cajaFotoPath.setText(informacion);
        }
    }
}
