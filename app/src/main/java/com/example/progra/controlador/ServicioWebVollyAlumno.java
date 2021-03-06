package com.example.progra.controlador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progra.modelo.Alumnos;

import org.json.JSONObject;

public class ServicioWebVollyAlumno {
    Context context;

    String host="http://reneguaman.000webhostapp.com";
    String insert="/insertar_alumno.php";
    String getAll="/obtener_alumnos.php";
    String getById="/obtener_alumno_por_id.php";
    String update="/actualizar_alumno.php";
    String delete="/borrar_alumno.php";

    public ServicioWebVollyAlumno(Context context) {
        this.context = context;
    }


    public  void Insertar(Alumnos alumno){
        String path=host.concat(insert);
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("nombre",alumno.getNombre());
            jsonObject.put("direccion",alumno.getDireccion());
        } catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,path,jsonObject,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Se ha guardado el alumno correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        AlumnoSingletonVolly.getInstance(context).addToRequestqueue(request);
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
        AlumnoSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public void BuscarporID(String id, final volleyResponseListener listener){
        String path=host.concat(getById).concat("?idalumno=").concat(id);
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


        AlumnoSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public void Eliminar(String id){
        String path=host.concat(delete);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idalumno",id);
        } catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,path,jsonObject,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error:","Ha habido un error");
            }
        });
        AlumnoSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public void Modificar(Alumnos alumno){
        String path=host.concat(update);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idalumno",alumno.getIdalumno());
            jsonObject.put("nombre",alumno.getNombre());
            jsonObject.put("direccion",alumno.getDireccion());
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
        AlumnoSingletonVolly.getInstance(context).addToRequestqueue(request);
    }

    public interface volleyResponseListener{
        void onError(String message);

        void onResponse(JSONObject response);
    }
}
