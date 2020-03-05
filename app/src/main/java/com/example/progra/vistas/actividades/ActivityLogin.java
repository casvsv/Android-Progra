package com.example.progra.vistas.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.progra.R;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{

    EditText cajaUsuario, cajaPassword;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargaComponentes();
    }
    private void cargaComponentes(){
        cajaUsuario = findViewById(R.id.txtLogin);
        cajaPassword = findViewById(R.id.txtPassword);
        boton = findViewById(R.id.btnLogin);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(ActivityLogin.this,"Usuario: " + cajaUsuario.getText() + "\nClave: " + cajaPassword.getText(),Toast.LENGTH_SHORT).show();
    }
}
