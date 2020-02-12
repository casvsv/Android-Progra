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
import com.example.progra.controlador.ServicioWebVollyUsuario;
import com.example.progra.modelo.Usuario;
import com.example.progra.vistas.adapter.UsuarioAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActividadVollyUsuario extends AppCompatActivity implements View.OnClickListener{
    EditText cajaDocumento,cajaNombre,cajaProfesion;
    Button botonGuardar, botonListar, botonModificar, botonEliminar, botonBuscar;
    RecyclerView recyclerUsuarios;
    List<Usuario> listaUsuarios;
    UsuarioAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_volly_usuario);
        cargarComponentes();
    }

    private void cargarComponentes(){
        recyclerUsuarios=findViewById(R.id.recyclerUsuarioVolly);
        cajaDocumento=findViewById(R.id.txtDocumentoUsuarioVolly);
        cajaNombre=findViewById(R.id.txtNombreUsuarioVolly);
        cajaProfesion=findViewById(R.id.txtProfesionUsuarioVolly);
        botonGuardar=findViewById(R.id.btnCrearUsuarioVolly);
        botonListar=findViewById(R.id.btnListarUsuarioVolly);
        botonModificar=findViewById(R.id.btnModificarUsuarioVolly);
        botonBuscar=findViewById(R.id.btnBuscarUsuarioVolly);
        botonEliminar=findViewById(R.id.btnEliminarUsuarioVolly);

        botonGuardar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ServicioWebVollyUsuario sw = new ServicioWebVollyUsuario(this);
        switch (v.getId()){
            case R.id.btnCrearUsuarioVolly:
                try {
                    Usuario usuario=new Usuario();
                    usuario.setDocumento(Integer.parseInt(cajaDocumento.getText().toString()));
                    usuario.setNombre(cajaNombre.getText().toString());
                    usuario.setProfesion(cajaProfesion.getText().toString());
                    sw.Insertar(usuario);
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;

            case R.id.btnListarUsuarioVolly:
                try {
                    sw.ObtenerTodos(new ServicioWebVollyUsuario.volleyResponseListener() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JsonParse(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;

            case R.id.btnModificarUsuarioVolly:
                try {
                    Usuario usuario= new Usuario();
                    usuario.setDocumento(Integer.parseInt(cajaDocumento.getText().toString()));
                    usuario.setNombre(cajaNombre.getText().toString());
                    usuario.setProfesion(cajaProfesion.getText().toString());
                    sw.Modificar(usuario);
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;

            case R.id.btnEliminarUsuarioVolly:
                try {
                    sw.Eliminar(cajaDocumento.getText().toString());
                    Toast.makeText(this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;

            case R.id.btnBuscarUsuarioVolly:
                try {
                    sw.BuscarporID(cajaDocumento.getText().toString(), new ServicioWebVollyUsuario.volleyResponseListener() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JsonParse(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    limpiar();
                } catch (Exception e){
                    Log.e("Error:",e.getMessage());
                }
                break;
        }
    }

    private void JsonParse(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray("usuario");
        listaUsuarios=new ArrayList<Usuario>();
        for (int i=0 ; i<jsonArray.length() ; i++){
            JSONObject usuarios = jsonArray.getJSONObject(i);
            Usuario usuario = new Usuario();
            usuario.setDocumento(usuarios.getInt("documento"));
            usuario.setNombre(usuarios.getString("nombre"));
            usuario.setProfesion(usuarios.getString("profesion"));

            listaUsuarios.add(usuario);
            adapter = new UsuarioAdapter(listaUsuarios);
            recyclerUsuarios.setLayoutManager(new LinearLayoutManager(ActividadVollyUsuario.this));
            adapter.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarCajasdeTexto(v);
                }
            });
            recyclerUsuarios.setAdapter(adapter);
        }
    }

    private void cargarCajasdeTexto(View view) {
        cajaDocumento.setText(""+listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(view)).getDocumento());
        cajaNombre.setText(""+listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(view)).getNombre());
        cajaProfesion.setText(""+listaUsuarios.get(recyclerUsuarios.getChildAdapterPosition(view)).getProfesion());
    }

    private void limpiar(){
        cajaDocumento.setText("");
        cajaNombre.setText("");
        cajaProfesion.setText("");
    }
}
