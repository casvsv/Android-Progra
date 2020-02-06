package com.example.progra.vistas.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.controlador.ServicioWebVollyAlumno;
import com.example.progra.modelo.Alumnos;
import com.example.progra.vistas.adapter.AlumnoAdapter;

import java.util.List;

public class ActividadSWVolly extends AppCompatActivity implements View.OnClickListener{
    EditText cajaNombre, cajaDireccion, cajaID;
    Button botonGuardar, botonListar, botonModificar, botonEliminar, botonBuscar;
    RecyclerView recyclerAlumnos;
    List<Alumnos> listaAlumnos;
    AlumnoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_swvolly);
        cargarComponentes();
    }

    private void cargarComponentes(){
        recyclerAlumnos=findViewById(R.id.recyclerSWVollyAlumnos);
        cajaID=findViewById(R.id.txtSWVollyIDAlumno);
        cajaNombre=findViewById(R.id.txtSWVollyNombreAlumno);
        cajaDireccion=findViewById(R.id.txtSWVollyDireccionAlumno);
        botonGuardar=findViewById(R.id.btnSWVollyCrearAlumno);
        botonListar=findViewById(R.id.btnSWVollyListarAlumnos);
        botonModificar=findViewById(R.id.btnSWVollyModificarAlumno);
        botonBuscar=findViewById(R.id.btnSWVollyBuscarAlumno);
        botonEliminar=findViewById(R.id.btnSWVollyEliminarAlumno);

        botonGuardar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
    }




    private void cargarCajasdeTexto(View view) {
        cajaID.setText(""+listaAlumnos.get(recyclerAlumnos.getChildAdapterPosition(view)).getIdalumno());
        cajaNombre.setText(""+listaAlumnos.get(recyclerAlumnos.getChildAdapterPosition(view)).getNombre());
        cajaDireccion.setText(""+listaAlumnos.get(recyclerAlumnos.getChildAdapterPosition(view)).getDireccion());
    }

    private void limpiar(){
        cajaID.setText("");
        cajaNombre.setText("");
        cajaDireccion.setText("");
    }

    @Override
    public void onClick(View v) {
        ServicioWebVollyAlumno sw = new ServicioWebVollyAlumno(this);
        switch (v.getId()){
            case R.id.btnSWVollyCrearAlumno:
                try {
                    Alumnos alumno=new Alumnos();
                    alumno.setNombre(cajaNombre.getText().toString());
                    alumno.setDireccion(cajaDireccion.getText().toString());
                    sw.Insertar(alumno);
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
            case R.id.btnSWVollyListarAlumnos:
                try {
                    String consulta=sw.ObtenerTodos();
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
            case R.id.btnSWVollyBuscarAlumno:
                try {
                    String consulta=sw.BuscarporID(cajaID.getText().toString());
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
            case R.id.btnSWVollyModificarAlumno:
                try {
                    Alumnos alumnom=new Alumnos();
                    alumnom.setIdalumno(Integer.parseInt(cajaID.getText().toString()));
                    alumnom.setNombre(cajaNombre.getText().toString());
                    alumnom.setDireccion(cajaDireccion.getText().toString());
                    sw.Modificar(alumnom);
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
            case R.id.btnSWVollyEliminarAlumno:
                try {
                    sw.Eliminar(cajaID.getText().toString());
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
        }
    }
}
