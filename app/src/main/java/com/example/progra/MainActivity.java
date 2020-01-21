package com.example.progra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progra.vistas.actividades.ActividadEnviarParametros;
import com.example.progra.vistas.actividades.ActividadEscucharFragmento;
import com.example.progra.vistas.actividades.ActividadFragmento;
import com.example.progra.vistas.actividades.ActividadRecyclerArtistas;
import com.example.progra.vistas.actividades.ActivityLogin;
import com.example.progra.vistas.actividades.ActivitySuma;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button botonLogin, botonSumar, botonParametros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CargarComponentes();
    }
    private void CargarComponentes(){
        botonLogin = findViewById(R.id.btnLogin);
        botonSumar = findViewById(R.id.btnSumar);
        botonParametros = findViewById(R.id.btnParametro);
        botonLogin.setOnClickListener(this);
        botonSumar.setOnClickListener(this);
        botonParametros.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnLogin:
                intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                break;
            case R.id.btnSumar:
                intent = new Intent(MainActivity.this, ActivitySuma.class);
                startActivity(intent);
                break;
            case R.id.btnParametro:
                intent = new Intent(MainActivity.this, ActividadEnviarParametros.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //metodo para cargar los menus
        //MenuInflater: permite crear un objeto para manejar un archivo xml (main.xml)
        //el metodo inflate permirte agregar un menu implementado de un archivo xml a la actividad
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // este metodo permite realizar eventos en cada item de los menus
        Intent intent;
        switch (item.getItemId()){
            case R.id.opcionLogin:
                intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                break;
            case R.id.opcionSumar:
                intent = new Intent(MainActivity.this, ActivitySuma.class);
                startActivity(intent);
                break;
            case R.id.opcionParametros:
                intent = new Intent(MainActivity.this, ActividadEnviarParametros.class);
                startActivity(intent);
                break;
            case R.id.opcionFrgColores:
                intent = new Intent(MainActivity.this, ActividadFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionFrgEscucaharFrag:
                intent = new Intent(MainActivity.this, ActividadEscucharFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionDlgSumar:
                final Dialog dlgSumar = new Dialog(MainActivity.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);
                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtN1Dg1);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txtN2Dg1);
                Button botonSumarDlg = dlgSumar.findViewById(R.id.btnSumarDlg);
                botonSumarDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int resultado = Integer.parseInt(cajaN1.getText().toString()) +  Integer.parseInt(cajaN2.getText().toString());
                        Toast.makeText(MainActivity.this,"La suma es: "+ resultado, Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });
                dlgSumar.show();
                break;
            case R.id.opcionRecyclerArtistas:
                intent = new Intent(MainActivity.this, ActividadRecyclerArtistas.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
