//Hilo
package com.example.progra.vistas.actividades;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.progra.R;
import com.example.progra.modelo.Alumnos;
import com.example.progra.vistas.adapter.AlumnoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActividadSWAlumno extends AppCompatActivity implements View.OnClickListener{
    EditText cajaNombre, cajaDireccion, cajaID;
    TextView datos;
    Button botonGuardar, botonListar, botonModificar, botonEliminar, botonBuscar;
    RecyclerView recyclerAlumnos;
    List<Alumnos> listaAlumnos;
    RequestQueue mQueue;
    AlumnoAdapter adapter;
    //Se define la URL del servicio
    String host="http://reneguaman.000webhostapp.com";
    String insert="/insertar_alumno.php";
    String getAll="/obtener_alumnos.php";
    String getById="/obtener_alumno_por_id.php";
    String update="/actualizar_alumno.php";
    String delete="/borrar_alumno.php";
    //Objeto de tipo servicio web
    ServicioWeb sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_swalumno);
        cargarComponentes();
        mQueue = Volley.newRequestQueue(this);
    }

    //Construcciòn del Hilo
    //Acceder al servicio web mediante un hilo
    class  ServicioWeb extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... parametros) {
            String consulta="";
            URL url=null;
            String ruta=parametros[0]; //Ruta del sw
            //Agregar nuevo alumno
            if(parametros[1].equals("1")){
                try{
                    url=new URL(ruta);
                    //Otra forma de establecer conexion
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoInput(true);
                    conexion.setDoOutput(true);
                    conexion.setUseCaches(false);
                    conexion.connect();

                    //Creacion del json con los parametros a enviar
                    JSONObject json=new JSONObject();
                    json.put("nombre",parametros[2]);
                    json.put("direccion",parametros[3]);

                    OutputStream outputStream = conexion.getOutputStream();
                    BufferedWriter escritor= new BufferedWriter(new OutputStreamWriter(outputStream));
                    escritor.write(json.toString());
                    escritor.flush();
                    escritor.close();

                    int codigoRespuesta = conexion.getResponseCode();
                    if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                        consulta+=lector.readLine();
                    } else{
                        Toast.makeText(ActividadSWAlumno.this,"Revise su conexión a internet", Toast.LENGTH_SHORT);
                    }

                } catch (Exception ex){
                    Toast.makeText(ActividadSWAlumno.this,"Ha habido un error", Toast.LENGTH_SHORT);
                }
            }
            //Obtener todos los alumnos
            else if (parametros[1].equals("2")){
                try{
                    url=new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();
                    if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                        InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                        consulta+=lector.readLine();
                        JsonParse(String.valueOf(url));
                    } else{
                        Toast.makeText(ActividadSWAlumno.this,"Revise su conexión a internet", Toast.LENGTH_SHORT);
                    }
                } catch (Exception ex){
                    Toast.makeText(ActividadSWAlumno.this,"Ha habido un error", Toast.LENGTH_SHORT);
                }
            }
            //Buscar por id
            else if (parametros[1].equals("3")){
                try{
                    ruta+="?idalumno="+parametros[2];
                    url=new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();
                    if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                        InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                        consulta+=lector.readLine();
                        JsonParses(String.valueOf(url));
                    } else{
                        Toast.makeText(ActividadSWAlumno.this,"Revise su conexión a internet", Toast.LENGTH_SHORT);
                    }
                } catch (Exception ex){
                    Toast.makeText(ActividadSWAlumno.this,"Ha habido un error", Toast.LENGTH_SHORT);
                }
            }
            //Modificar
            else if (parametros[1].equals("4")){
                try{
                    ruta+="?idalumno="+parametros[2];
                    url=new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();

                    JSONObject json=new JSONObject();
                    json.put("nombre",parametros[3]);
                    json.put("direccion",parametros[4]);

                    OutputStream outputStream = conexion.getOutputStream();
                    BufferedWriter escritor= new BufferedWriter(new OutputStreamWriter(outputStream));
                    escritor.write(json.toString());
                    escritor.flush();
                    escritor.close();
                    if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                        InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                        consulta+=lector.readLine();
                    } else{
                        Toast.makeText(ActividadSWAlumno.this,"Revise su conexión a internet", Toast.LENGTH_SHORT);
                    }
                } catch (Exception ex){
                    Toast.makeText(ActividadSWAlumno.this,"Ha habido un error", Toast.LENGTH_SHORT);
                }
            }
            //Eliminar
            else if (parametros[1].equals("5")){
                try{
                    ruta+="?idalumno="+parametros[2];
                    url=new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    int codigoRespuesta = conexion.getResponseCode();
                    if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                        InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                        consulta+=lector.readLine();
                    } else{
                        Toast.makeText(ActividadSWAlumno.this,"Revise su conexión a internet", Toast.LENGTH_SHORT);
                    }
                } catch (Exception ex){
                    Toast.makeText(ActividadSWAlumno.this,"Ha habido un error", Toast.LENGTH_SHORT);
                }
            }

            return consulta;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

        private void JsonParse(String url){
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("alumnos");
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
                        } catch (JSONException ex){
                            Log.e("Error: ",ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
            mQueue.add(request);
        }

    private void JsonParses(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject alumnos = response.getJSONObject("alumno");
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
                            } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

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
        sw = new ServicioWeb();
        switch (view.getId()){
            case R.id.btnSWCrearAlumno:
                sw.execute(host.concat(insert),"1",cajaNombre.getText().toString(),cajaDireccion.getText().toString());
                Toast.makeText(this,"Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;
            case R.id.btnSWListarAlumnos:
                sw.execute(host.concat(getAll),"2");  //Ejecución del hilo en el doINBackGround
                Toast.makeText(this,"Se han listado correctamente",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSWBuscarAlumno:
                sw.execute(host.concat(getById),"3",cajaID.getText().toString());
                break;
            case R.id.btnSWModificarAlumno:
                sw.execute(host.concat(update),"4",cajaID.getText().toString(),cajaNombre.getText().toString(),cajaDireccion.getText().toString());
                Toast.makeText(this,"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSWEliminarAlumno:
                sw.execute(host.concat(delete),"5",cajaID.getText().toString());
                Toast.makeText(this,"Se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
                break;
        }
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
