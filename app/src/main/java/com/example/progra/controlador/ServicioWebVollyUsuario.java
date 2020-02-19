package com.example.progra.controlador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;

public class ServicioWebVollyUsuario {
    Context context;

    //Se define la URL del servicio
    String host="http://192.168.1.8/Salazar";
    String getAll="/wsJSONConsultarLista.php";
    String insert="/wsJSONRegistroMovil.php";
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

    public  void Insertar(final Map<String,String> params){
        String path=host.concat(insert);
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

    public void Modificar(final Map<String,String> params){
        String path=host.concat(update);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        UsuarioSingletonVolly.getInstance(context).addToRequestqueue(stringRequest);
    }


    public interface volleyResponseListener{
        void onError(String message);

        void onResponse(JSONObject response);
    }
}

