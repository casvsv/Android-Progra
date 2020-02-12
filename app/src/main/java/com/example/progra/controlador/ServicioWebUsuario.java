package com.example.progra.controlador;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServicioWebUsuario extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... parametros) {
        String consulta="";
        URL url=null;
        String ruta=parametros[0];
        //Buscar Todos
        if(parametros[1].equals("1")){
            try{
                url=new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                int codigoRespuesta = conexion.getResponseCode();
                if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                    InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                    consulta+=lector.readLine();
                } else{
                    Log.e("Error","Revise su conexión a internet");
                }
                conexion.disconnect();
            } catch (Exception ex){
                Log.e("Error",ex.getMessage());
            }
        }
        //Guardar
        else if (parametros[1].equals("2")){
            try{
                ruta+="?documento="+parametros[2]+"&nombre="+parametros[3]+"&profesion="+parametros[4];

                url=new URL(ruta);
                Log.e("ASD:",url.toString());
                //Otra forma de establecer conexion
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);
                conexion.connect();

                int codigoRespuesta = conexion.getResponseCode();
                if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    consulta+=lector.readLine();
                } else{
                    Log.e("Error","Revise su conexión a internet");
                }

                conexion.disconnect();
            } catch (Exception ex){
                Log.e("Error",ex.getMessage());
            }
        }
        //Modificar
        else if (parametros[1].equals("3")){
            try{
                url=new URL(ruta);
                //Otra forma de establecer conexion
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);

                conexion.setRequestMethod("POST");
                conexion.connect();

                String cadena="documento="+parametros[2]+"&nombre="+parametros[3]+"&profesion="+parametros[4];

                OutputStream outputStream = conexion.getOutputStream();
                BufferedWriter escritor= new BufferedWriter(new OutputStreamWriter(outputStream));
                escritor.write(cadena);
                escritor.flush();
                escritor.close();

                int codigoRespuesta = conexion.getResponseCode();
                if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    consulta+=lector.readLine();
                } else{
                    Log.e("Error","Revise su conexión a internet");
                }
                conexion.disconnect();
            } catch (Exception ex){
                Log.e("Error",ex.getMessage());
            }
        }
        //Eliminar
        else if (parametros[1].equals("4")){
            try{
                ruta+="?documento="+parametros[2];
                url=new URL(ruta);
                //Otra forma de establecer conexion
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                int codigoRespuesta = conexion.getResponseCode();
                if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                    InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                    consulta+=lector.readLine();
                } else{
                    Log.e("Error","Revise su conexión a internet");
                }
                conexion.disconnect();
            } catch (Exception ex){
                Log.e("Error",ex.getMessage());
            }
        }
        //Buscar por ID
        else if (parametros[1].equals("5")){
            try{
                ruta+="?documento="+parametros[2];
                url=new URL(ruta);
                //Otra forma de establecer conexion
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                int codigoRespuesta = conexion.getResponseCode();
                if(codigoRespuesta==HttpURLConnection.HTTP_OK){
                    InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                    consulta+=lector.readLine();
                } else{
                    Log.e("Error","Revise su conexión a internet");
                }
                conexion.disconnect();
            } catch (Exception ex){
                Log.e("Error",ex.getMessage());
            }
        }

        return consulta;
    }
}
