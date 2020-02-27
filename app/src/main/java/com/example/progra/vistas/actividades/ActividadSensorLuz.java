package com.example.progra.vistas.actividades;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progra.R;

public class ActividadSensorLuz extends AppCompatActivity implements SensorEventListener {
    SensorManager manager;
    Sensor sensor;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_sensor_luz);
        imagen=findViewById(R.id.imgLight);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"Start",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor==null){
            Toast.makeText(this,"El dispositivo no posee sensor de Luz",Toast.LENGTH_LONG);
        }
        else {
            manager.registerListener(this,sensor,manager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"Stop",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Destroy",Toast.LENGTH_SHORT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Double valor = Double.valueOf(event.values[0]);
        if(valor>3){
            imagen.setImageResource(R.drawable.dia);
        }
        else{
            imagen.setImageResource(R.drawable.noche);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
