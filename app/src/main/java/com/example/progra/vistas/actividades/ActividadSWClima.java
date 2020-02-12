package com.example.progra.vistas.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.example.progra.R;
import com.example.progra.controlador.ServicioWebClima;
import com.example.progra.modelo.Clima;

import java.util.concurrent.ExecutionException;

public class ActividadSWClima extends AppCompatActivity implements View.OnClickListener{
    RequestQueue mQueue;
    TextView lon,lat,weatherid,weathermain,weatherdescription,weathericon,base,maintemp,mainpressure,mainhumidity,maintempmin,maintempmax,visibility,windspeed,winddeg,cloudsall,dt,systype,sysid,sysmessage,syscountry,syssunrise,syssunset,id,name,cod;
    Button boton;
    ServicioWebClima sw;
    Clima climas = new Clima();
    String consulta;
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
                try {
                    consulta=sw.execute().get();
                    climas=sw.parsearJson(consulta);
                    cargarDatos(climas);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this,"Se han cargado correctamente",Toast.LENGTH_SHORT).show();
                break;
        }
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
