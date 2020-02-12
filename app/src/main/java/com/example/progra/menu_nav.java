package com.example.progra;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.progra.vistas.actividades.ActividadArchivo;
import com.example.progra.vistas.actividades.ActividadCarroORM;
import com.example.progra.vistas.actividades.ActividadEnviarParametros;
import com.example.progra.vistas.actividades.ActividadEscucharFragmento;
import com.example.progra.vistas.actividades.ActividadFragmento;
import com.example.progra.vistas.actividades.ActividadHiloUsuario;
import com.example.progra.vistas.actividades.ActividadMemoriaPrograma2;
import com.example.progra.vistas.actividades.ActividadProductoHelper;
import com.example.progra.vistas.actividades.ActividadRecyclerArtistas;
import com.example.progra.vistas.actividades.ActividadSWAlumno;
import com.example.progra.vistas.actividades.ActividadSWClima;
import com.example.progra.vistas.actividades.ActividadSWVolly;
import com.example.progra.vistas.actividades.ActividadVollyUsuario;
import com.example.progra.vistas.actividades.ActivityLogin;
import com.example.progra.vistas.actividades.ActivitySuma;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class menu_nav extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.opcionLogin:
                intent = new Intent(menu_nav.this, ActivityLogin.class);
                startActivity(intent);
                break;
            case R.id.opcionSumar:
                intent = new Intent(menu_nav.this, ActivitySuma.class);
                startActivity(intent);
                break;
            case R.id.opcionParametros:
                intent = new Intent(menu_nav.this, ActividadEnviarParametros.class);
                startActivity(intent);
                break;
            case R.id.opcionFrgColores:
                intent = new Intent(menu_nav.this, ActividadFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionFrgEscucaharFrag:
                intent = new Intent(menu_nav.this, ActividadEscucharFragmento.class);
                startActivity(intent);
                break;
            case R.id.opcionDlgSumar:
                final Dialog dlgSumar = new Dialog(menu_nav.this);
                dlgSumar.setContentView(R.layout.dlg_sumar);
                final EditText cajaN1 = dlgSumar.findViewById(R.id.txtN1Dg1);
                final EditText cajaN2 = dlgSumar.findViewById(R.id.txtN2Dg1);
                Button botonSumarDlg = dlgSumar.findViewById(R.id.btnSumarDlg);
                botonSumarDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int resultado = Integer.parseInt(cajaN1.getText().toString()) +  Integer.parseInt(cajaN2.getText().toString());
                        Toast.makeText(menu_nav.this,"La suma es: "+ resultado, Toast.LENGTH_SHORT).show();
                        dlgSumar.hide();
                    }
                });
                dlgSumar.show();
                break;
            case R.id.opcionRecyclerArtistas:
                intent = new Intent(menu_nav.this, ActividadRecyclerArtistas.class);
                startActivity(intent);
                break;
            case R.id.opcionArchivoReyes:
                intent = new Intent(menu_nav.this, ActividadMemoriaPrograma2.class);
                startActivity(intent);
                break;
            case R.id.opcionArtista:
                intent = new Intent(menu_nav.this, ActividadArchivo.class);
                startActivity(intent);
                break;
            case R.id.opcionHelper:
                intent = new Intent(menu_nav.this, ActividadProductoHelper.class);
                startActivity(intent);
                break;
            case R.id.opcionORM:
                intent = new Intent(menu_nav.this, ActividadCarroORM.class);
                startActivity(intent);
                break;
            case R.id.opcionSWHilo:
                intent = new Intent(menu_nav.this, ActividadSWAlumno.class);
                startActivity(intent);
                break;
            case R.id.opcionSWClima:
                intent = new Intent(menu_nav.this, ActividadSWClima.class);
                startActivity(intent);
                break;
            case R.id.opcionSWVolly:
                intent = new Intent(menu_nav.this, ActividadSWVolly.class);
                startActivity(intent);
                break;
            case R.id.opcionUsuarioHilo:
                intent = new Intent(menu_nav.this, ActividadHiloUsuario.class);
                startActivity(intent);
                break;
            case R.id.opcionUsuarioVolly:
                intent = new Intent(menu_nav.this, ActividadVollyUsuario.class);
                startActivity(intent);
                break;
        }
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
