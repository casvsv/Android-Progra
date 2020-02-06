package com.example.progra.vistas.actividades;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.example.progra.R;
import com.example.progra.modelo.Clima;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ActividadSWClima extends AppCompatActivity implements View.OnClickListener{
    RequestQueue mQueue;
    TextView lon,lat,weatherid,weathermain,weatherdescription,weathericon,base,maintemp,mainpressure,mainhumidity,maintempmin,maintempmax,visibility,windspeed,winddeg,cloudsall,dt,systype,sysid,sysmessage,syscountry,syssunrise,syssunset,id,name,cod;
    Button boton;
    ServicioWebClima sw;
    Clima climas = new Clima();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_swclima);
        cargarComponentes();
    }

    private void cargarComponentes(){
        lon= findViewById(R.id.lblSWClimaLon);
        lat= findViewById(R.id.lblSWClimaLat);
        weatherid= findViewById(R.id.lblSWClimaClimaID);
        weathermain= findViewById(R.id.lblSWClimaClimaMain);
        weatherdescription= findViewById(R.id.lblSWClimaClimaIDecription);
        weathericon= findViewById(R.id.lblSWClimaClimaIcon);
        base= findViewById(R.id.lblSWClimaBase);
        maintemp= findViewById(R.id.lblSWClimaMainTemp);
        mainpressure= findViewById(R.id.lblSWClimaMainPressure);
        mainhumidity= findViewById(R.id.lblSWClimaMainHumidity);
        maintempmin= findViewById(R.id.lblSWClimaMainTempMin);
        maintempmax= findViewById(R.id.lblSWClimaMainTempMax);
        visibility= findViewById(R.id.lblSWClimaMainVisibility);
        windspeed= findViewById(R.id.lblSWClimaWindSpeed);
        winddeg= findViewById(R.id.lblSWClimaWindDeg);
        cloudsall= findViewById(R.id.lblSWClimaCloudsAll);
        dt= findViewById(R.id.lblSWClimaDT);
        systype= findViewById(R.id.lblSWClimaSysType);
        sysid= findViewById(R.id.lblSWClimaSysID);
        sysmessage= findViewById(R.id.lblSWClimaSysMessage);
        syscountry= findViewById(R.id.lblSWClimaSysCountry);
        syssunrise= findViewById(R.id.lblSWClimaSysSunrise);
        syssunset= findViewById(R.id.lblSWClimaSysSunset);
        id= findViewById(R.id.lblSWClimaID);
        name= findViewById(R.id.lblSWClimaName);
        cod= findViewById(R.id.lblSWClimaCod);
        boton=findViewById(R.id.btnCargarDatosSWClima);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sw = new ServicioWebClima();
        switch (v.getId()){
            case R.id.btnCargarDatosSWClima:
                sw.execute();
                Toast.makeText(this,"Se han cargado correctamente",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class  ServicioWebClima extends AsyncTask<String,Void,String> {

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
                    climas=parsearJson(consulta);
                    cargarDatos(climas);
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
    }

    private Clima parsearJson(String consulta) {
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

    private void cargarDatos(Clima clima){
        dt.setText(clima.getDt());
        lon.setText(clima.getLon());
        lat.setText(clima.getLat());
        weatherid.setText(clima.getId_clima());
        weathermain.setText(clima.getMain());
        weatherdescription.setText(clima.getDescription());
        weathericon.setText(clima.getIcon());
        base.setText(clima.getBase());
        maintemp.setText(""+clima.getTemp());
        mainpressure.setText(clima.getPressure());
        mainhumidity.setText(clima.getHumidity());
        maintempmin.setText(""+clima.getTemp_min());
        maintempmax.setText(""+clima.getTemp_max());
        visibility.setText(clima.getVisibility());
        windspeed.setText(""+clima.getSpeed());
        winddeg.setText(clima.getDeg());
        cloudsall.setText(clima.getAll());
        systype.setText(clima.getType());
        sysid.setText(clima.getId_sys());
        sysmessage.setText(""+clima.getMessage());
        syscountry.setText(clima.getCountry());
        syssunrise.setText(clima.getSunrise());
        syssunset.setText(clima.getSunset());
        id.setText(clima.getId());
        name.setText(clima.getName());
        cod.setText(clima.getCod());
    }
}
