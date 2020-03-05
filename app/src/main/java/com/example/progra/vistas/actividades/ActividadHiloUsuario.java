package com.example.progra.vistas.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progra.R;
import com.example.progra.controlador.ServicioWebUsuario;
import com.example.progra.modelo.Usuario;
import com.example.progra.vistas.adapter.UsuarioAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActividadHiloUsuario extends AppCompatActivity implements View.OnClickListener{
    EditText cajaDocumento,cajaNombre,cajaProfesion;
    Button botonGuardar, botonListar, botonModificar, botonEliminar, botonBuscar;
    RecyclerView recyclerUsuarios;
    List<Usuario> listaUsuarios;
    UsuarioAdapter adapter;


    //Se define la URL del servicio
    String host="http://192.168.1.10/Salazar";
    String getAll="/wsJSONConsultarLista.php";
    String insert="/wsJSONRegistro.php";
    String update="/wsJSONUpdateMovil.php";
    String getById="/wsJSONConsultarUsuario.php";
    String delete="/wsJSONDeleteMovil.php";
    //Objeto de tipo servicio web
    ServicioWebUsuario sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_hilo_usuario);
        cargarComponentes();
    }

    private void cargarComponentes(){
        recyclerUsuarios=findViewById(R.id.recyclerUsuarioHilo);
        cajaDocumento=findViewById(R.id.txtDocumentoUsuarioHilo);
        cajaNombre=findViewById(R.id.txtNombreUsuarioHilo);
        cajaProfesion=findViewById(R.id.txtProfesionUsuarioHilo);
        botonGuardar=findViewById(R.id.btnCrearUsuarioHilo);
        botonListar=findViewById(R.id.btnListarUsuarioHilo);
        botonModificar=findViewById(R.id.btnModificarUsuarioHilo);
        botonBuscar=findViewById(R.id.btnBuscarUsuarioHilo);
        botonEliminar=findViewById(R.id.btnEliminarUsuarioHilo);

        botonGuardar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sw = new ServicioWebUsuario();
        switch (v.getId()){
            case R.id.btnListarUsuarioHilo:
                try {
                    String cadena = sw.execute(host.concat(getAll),"1").get();  //Ejecución del hilo en el doINBackGround
                    JsonParse(cadena);
                    Toast.makeText(this,"Se han listado correctamente",Toast.LENGTH_SHORT).show();
                    limpiar();
                } catch (Exception e){
                    Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_SHORT);
                }
                break;

            case R.id.btnCrearUsuarioHilo:
                try {
                    sw.execute(host.concat(insert), "2", cajaDocumento.getText().toString(), cajaNombre.getText().toString(),cajaProfesion.getText().toString()).get();
                } catch (Exception e){
                    Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_SHORT);
                }
                Toast.makeText(this,"Se ha guardado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;

            case R.id.btnModificarUsuarioHilo:
                try {
                    sw.execute(host.concat(update), "3", cajaDocumento.getText().toString(), cajaNombre.getText().toString(),cajaProfesion.getText().toString()).get();
                } catch (Exception e){
                    Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_SHORT);
                }
                Toast.makeText(this,"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;

            case R.id.btnEliminarUsuarioHilo:
                sw.execute(host.concat(delete),"4",cajaDocumento.getText().toString());
                Toast.makeText(this,"Se ha eliminado correctamente",Toast.LENGTH_SHORT).show();
                limpiar();
                break;

            case R.id.btnBuscarUsuarioHilo:
                try {
                    String cadena=sw.execute(host.concat(getById),"5",cajaDocumento.getText().toString()).get();
                    JsonParse(cadena);
                    limpiar();
                } catch (Exception e){
                    Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_SHORT);
                }
                break;
        }
    }

    private void JsonParse(String cadena) throws JSONException {
        JSONObject jsonObject = new JSONObject(cadena);
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
            recyclerUsuarios.setLayoutManager(new LinearLayoutManager(ActividadHiloUsuario.this));
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
