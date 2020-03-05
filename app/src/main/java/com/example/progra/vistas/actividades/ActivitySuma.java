package com.example.progra.vistas.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progra.R;

public class ActivitySuma extends AppCompatActivity implements View.OnClickListener {

    EditText numero1, numero2;
    TextView resultado;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);
        SumaNumeros();
    }
    private void SumaNumeros (){
        numero1 = findViewById(R.id.txtNum1);
        numero2 = findViewById(R.id.txtNum2);
        resultado = findViewById(R.id.txtResultado);
        boton = findViewById(R.id.btnBoton);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int valor1 = Integer.parseInt(numero1.getText().toString());
        int valor2 = Integer.parseInt(numero2.getText().toString());
        int suma = valor1 + valor2;
        resultado.setText(suma + "");
        numero1.setText("");
        numero2.setText("");
    }
}
