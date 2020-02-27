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

public class ActividadSensorProximidad extends AppCompatActivity implements SensorEventListener {
    SensorManager manager;
    Sensor sensor;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_sensor_proximidad);
        imagen=findViewById(R.id.imgProximity);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
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
            Toast.makeText(this,"El dispositivo no posee sensor de Proximidad",Toast.LENGTH_LONG);
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
        if(event.values[0]>0){
            imagen.setImageResource(R.drawable.lejos);
        }
        else{
            imagen.setImageResource(R.drawable.cerca);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
