package com.example.progra.vistas.actividades;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progra.R;

public class ActividadSensorAcelerometro extends AppCompatActivity implements SensorEventListener {
    SensorManager manager;
    Sensor sensor;
    TextView X,Y,Z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_sensoracelerometro);
        cargarComponentes();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void cargarComponentes(){
        X=findViewById(R.id.lblAcelerometroX);
        Y=findViewById(R.id.lblAcelerometroY);
        Z=findViewById(R.id.lblAcelerometroZ);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"Start",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this,sensor,manager.SENSOR_DELAY_NORMAL);
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

    //Cambios del sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        float a,b,c;
        a = event.values[0];
        b = event.values[1];
        c = event.values[2];

        X.setText(a+"");
        Y.setText(b+"");
        Z.setText(c+"");
    }

    //Precisión del sensor
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
