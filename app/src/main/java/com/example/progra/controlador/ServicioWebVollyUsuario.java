package com.example.progra.controlador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progra.modelo.Usuario;

import org.json.JSONObject;

public class ServicioWebVollyUsuario {
    Context context;

    //Se define la URL del servicio
    String host="http://192.168.1.8/Salazar";
    String getAll="/wsJSONConsultarLista.php";
    String insert="/wsJSONRegistro.php";
    String update="/wsJSONUpdateMovil.php";
    String getById="/wsJSONConsultarUsuario.php";
    String delete="/wsJSONDeleteMovil.php";


    public ServicioWebVollyUsuario(Context context) {
        this.context = context;
    }

    public void ObtenerTodos(final volleyResponseListener listener){
        String path=host.concat(getAll);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error:",error.getMessage());
            }
        });
        UsuarioSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public  void Insertar(Usuario usuario){
        String datos="?documento="+usuario.getDocumento()+"&nombre="+usuario.getNombre()+"&profesion="+usuario.getProfesion();
        String path=host.concat(insert).concat(datos);
        Log.e("ASD:",path);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,path,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Se ha guardado el usuario correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        UsuarioSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public void BuscarporID(String id, final volleyResponseListener listener){
        String path=host.concat(getById).concat("?documento=").concat(id);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error:",error.getMessage());
            }
        });


        UsuarioSingletonVolly.getInstance(context).addToRequestqueue(request);
    }


    public void Eliminar(String id){
        String path=host.concat(delete).concat("?documento=").concat(id);
        Log.e("ASD:",path);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error:",error.getMessage());
            }
        });


        UsuarioSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public void Modificar(Usuario usuario){
        String path=host.concat(update).concat("?documento=").concat(usuario.getDocumento()+"");
        Log.e("Error",path);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("documento",usuario.getDocumento());
            jsonObject.put("nombre",usuario.getNombre());
            jsonObject.put("profesion",usuario.getProfesion());
            jsonObject.put("imagen",null);
        } catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error:","Ha habido un error");
            }
        });
        UsuarioSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public interface volleyResponseListener{
        void onError(String message);

        void onResponse(JSONObject response);
    }
}

