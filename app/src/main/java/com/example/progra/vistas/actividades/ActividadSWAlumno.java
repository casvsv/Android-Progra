//Hilo
package com.example.progra.vistas.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.controlador.ServicioWebAlumno;
import com.example.progra.modelo.Alumnos;
import com.example.progra.vistas.adapter.AlumnoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActividadSWAlumno extends AppCompatActivity implements View.OnClickListener{
    EditText cajaNombre, cajaDireccion, cajaID;
    Button botonGuardar, botonListar, botonModificar, botonEliminar, botonBuscar;
    RecyclerView recyclerAlumnos;
    List<Alumnos> listaAlumnos;
    AlumnoAdapter adapter;
    //Se define la URL del servicio
    String host="http://reneguaman.000webhostapp.com";
    String insert="/insertar_alumno.php";
    String getAll="/obtener_alumnos.php";
    String getById="/obtener_alumno_por_id.php";
    String update="/actualizar_alumno.php";
    String delete="/borrar_alumno.php";
    //Objeto de tipo servicio web
    ServicioWebAlumno sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_swalumno);
        cargarComponentes();
    }

    //Construcciòn del Hilo
    //Acceder al servicio web mediante un hilo




        private void cargarComponentes(){
        recyclerAlumnos=findViewById(R.id.recyclerSWAlumnos);
        cajaID=findViewById(R.id.txtSWIDAlumno);
        cajaNombre=findViewById(R.id.txtSWNombreAlumno);
        cajaDireccion=findViewById(R.id.txtSWDireccionAlumno);
        botonGuardar=findViewById(R.id.btnSWCrearAlumno);
        botonListar=findViewById(R.id.btnSWListarAlumnos);
        botonModificar=findViewById(R.id.btnSWModificarAlumno);
        botonBuscar=findViewById(R.id.btnSWBuscarAlumno);
        botonEliminar=findViewById(R.id.btnSWEliminarAlumno);

        botonGuardar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        sw = new ServicioWebAlumno();
        switch (view.getId()){
            case R.id.btnSWCrearAlumno:
                try {
                    sw.execute(host.concat(insert), "1", cajaNombre.getText().toString(), cajaDireccion.getText().toString()).get();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                Toast.makeText(this,"Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;
            case R.id.btnSWListarAlumnos:
                try {
                String cadena = sw.execute(host.concat(getAll),"2").get();  //Ejecución del hilo en el doINBackGround
                JsonParse(cadena);
                    Toast.makeText(this,"Se han listado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
            case R.id.btnSWBuscarAlumno:
                try {
                String cadena=sw.execute(host.concat(getById),"3",cajaID.getText().toString()).get();
                JsonParses(cadena);
                limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
            case R.id.btnSWModificarAlumno:
                sw.execute(host.concat(update),"4",cajaID.getText().toString(),cajaNombre.getText().toString(),cajaDireccion.getText().toString());
                Toast.makeText(this,"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;
            case R.id.btnSWEliminarAlumno:
                sw.execute(host.concat(delete),"5",cajaID.getText().toString());
                Toast.makeText(this,"Se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;
        }
    }

    private void JsonParse(String cadena) throws JSONException {
        JSONObject jsonObject = new JSONObject(cadena);
        JSONArray jsonArray = jsonObject.getJSONArray("alumnos");
        listaAlumnos=new ArrayList<Alumnos>();
        for (int i=0 ; i<jsonArray.length() ; i++){
            JSONObject alumnos = jsonArray.getJSONObject(i);
            Alumnos alumno = new Alumnos();
            alumno.setIdalumno(alumnos.getInt("idalumno"));
            alumno.setNombre(alumnos.getString("nombre"));
            alumno.setDireccion(alumnos.getString("direccion"));

            listaAlumnos.add(alumno);
            adapter = new AlumnoAdapter(listaAlumnos);
            recyclerAlumnos.setLayoutManager(new LinearLayoutManager(ActividadSWAlumno.this));
            adapter.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarCajasdeTexto(v);
                }
            });
            recyclerAlumnos.setAdapter(adapter);
        }
    }

    private void JsonParses(String cadena) throws JSONException {
        JSONObject jsonObject = new JSONObject(cadena);
        JSONObject alumnos = jsonObject.getJSONObject("alumno");
        listaAlumnos=new ArrayList<Alumnos>();
        Alumnos alumno = new Alumnos();
        alumno.setIdalumno(alumnos.getInt("idAlumno"));
        alumno.setNombre(alumnos.getString("nombre"));
        alumno.setDireccion(alumnos.getString("direccion"));

        listaAlumnos.add(alumno);
        adapter = new AlumnoAdapter(listaAlumnos);
        recyclerAlumnos.setLayoutManager(new LinearLayoutManager(ActividadSWAlumno.this));
        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarCajasdeTexto(v);
            }
        });
        recyclerAlumnos.setAdapter(adapter);
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
}
