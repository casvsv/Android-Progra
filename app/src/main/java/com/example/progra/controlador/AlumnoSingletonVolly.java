package com.example.progra.controlador;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AlumnoSingletonVolly {
    private RequestQueue queue;
    private Context context;
    private static AlumnoSingletonVolly miInstancia;

    public AlumnoSingletonVolly(Context context) {
        this.context = context;
        queue=getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if (queue==null)
            queue = Volley.newRequestQueue(context.getApplicationContext());
        return queue;
    }

    public static synchronized AlumnoSingletonVolly getInstance(Context context){
        if(miInstancia==null)
            miInstancia=new AlumnoSingletonVolly(context);
        return miInstancia;
    }

    //<T> Coleccion de cualquier tipo
    public <T> void addToRequestqueue(Request request){
        queue.add(request);
    }
}
