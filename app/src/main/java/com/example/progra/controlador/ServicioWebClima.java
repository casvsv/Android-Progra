package com.example.progra.controlador;

import android.os.AsyncTask;
import android.util.Log;

import com.example.progra.modelo.Clima;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class  ServicioWebClima extends AsyncTask<String,Void,String> {

    @Override
    public String doInBackground(String... parametros) {
        String consulta = "";
        URL url;
        try {
            url = new URL("https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22");
            HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
            int codigoRespuesta = conexion.getResponseCode();
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = new BufferedInputStream(conexion.getInputStream());
                BufferedReader lector = new BufferedReader(new InputStreamReader(inputStream));
                consulta += lector.readLine();

            } else {
                Log.e("Error:", "Revise su conexión a internet");
            }
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return consulta;
    }

    @Override
    protected void onPostExecute(String s) {

    }

    public Clima parsearJson(String consulta) {
        Clima clima=new Clima();
        try {
            JSONObject jsonObject = new JSONObject(consulta);
            //Coord
            JSONObject coordenadas= new JSONObject(jsonObject.getString("coord"));
            clima.setLon(coordenadas.getString("lon"));
            clima.setLat(coordenadas.getString("lat"));
            //Weather
            JSONObject weather= new JSONObject(jsonObject.getString("weather"));
            clima.setId_clima(weather.getInt("id"));
            clima.setMain(weather.getString("main"));
            clima.setDescription(weather.getString("description"));
            clima.setIcon(weather.getString("icon"));
            //Base
            clima.setBase(jsonObject.getString("base"));
            //Main
            JSONObject main= new JSONObject(jsonObject.getString("main"));
            clima.setTemp(main.getDouble("temp"));
            clima.setPressure(main.getInt("pressure"));
            clima.setHumidity(main.getInt("humidity"));
            clima.setTemp_min(main.getDouble("temp_min"));
            clima.setTemp_max(main.getDouble("temp_max"));
            //Visibility
            clima.setVisibility(jsonObject.getInt("visibility"));
            //Wind
            JSONObject wind= new JSONObject(jsonObject.getString("wind"));
            clima.setSpeed(wind.getDouble("speed"));
            clima.setDeg(wind.getInt("deg"));
            //Clouds
            JSONObject clouds= new JSONObject(jsonObject.getString("clouds"));
            clima.setAll(clouds.getInt("all"));
            //Dt
            clima.setDt(jsonObject.getString("dt"));
            //Sys
            JSONObject sys= new JSONObject(jsonObject.getString("sys"));
            clima.setType(sys.getInt("type"));
            clima.setId_sys(sys.getInt("id"));
            clima.setMessage(sys.getInt("message"));
            clima.setCountry(sys.getString("country"));
            clima.setSunrise(sys.getInt("sunrise"));
            clima.setSunset(sys.getInt("sunset"));
            //ID
            clima.setId(jsonObject.getInt("id"));
            //Name
            clima.setName(jsonObject.getString("name"));
            //Cod
            clima.setCod(jsonObject.getInt("cod"));
        }catch (Exception e){
            e.getMessage();
        }
        return clima;
    }
}