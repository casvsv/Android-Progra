package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.progra.R;

public class ActividadEnviarParametros extends AppCompatActivity implements View.OnClickListener {
    EditText cajanombres, cajaapellidos;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_enviar_parametros);
        EnviarParametros();
    }
    private void EnviarParametros(){
        cajanombres = findViewById(R.id.txtNombreEnviarParametro);
        cajaapellidos = findViewById(R.id.txtApellidoEnviarParametro);
        enviar = findViewById(R.id.btnEnviarParametro);

        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ActividadEnviarParametros.this, ActividadRecibirParametros.class);
        Bundle bundle = new Bundle();
        bundle.putString("Nombre", cajanombres.getText().toString());
        bundle.putString("Apellido", cajaapellidos.getText()+"");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
