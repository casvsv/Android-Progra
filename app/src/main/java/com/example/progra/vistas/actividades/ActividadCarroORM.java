package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progra.R;
import com.example.progra.modelo.Carro;
import com.example.progra.vistas.adapter.CarroAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.progra.modelo.Carro.deleteAll;
import static com.example.progra.modelo.Carro.getCarByPlate;

public class ActividadCarroORM extends AppCompatActivity implements View.OnClickListener{
    EditText cajaPlaca, cajaModel, cajaMarca, cajaAnio;
    Button botonGuardar, botonListar, botonModificar, botonEliminar, botonBuscar, botonEliminarTodo;
    RecyclerView recyclerCarro;
    List<Carro> listaCarros;
    CarroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_carro_orm);
        cargarComponentes();
    }

    private void cargarComponentes(){
        recyclerCarro=findViewById(R.id.recyclerORM);
        cajaPlaca=findViewById(R.id.txtPlacaORM);
        cajaModel=findViewById(R.id.txtModeloORM);
        cajaMarca=findViewById(R.id.txtMarcaORM);
        cajaAnio=findViewById(R.id.txtAnioORM);
        botonGuardar=findViewById(R.id.btnCrearDBORM);
        botonListar=findViewById(R.id.btnListarDBORM);
        botonModificar=findViewById(R.id.btnModificarDBORM);
        botonBuscar=findViewById(R.id.btnBuscarDBORM);
        botonEliminar=findViewById(R.id.btnEliminarDBORM);
        botonEliminarTodo=findViewById(R.id.btnEliminarTodoDBORM);

        botonGuardar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonEliminarTodo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnCrearDBORM:
                try {
                Carro carros= new Carro();
                carros.setPlaca(cajaPlaca.getText().toString());
                carros.setMarca(cajaMarca.getText().toString());
                carros.setModelo(cajaModel.getText().toString());
                carros.setAnio(Integer.parseInt(cajaAnio.getText().toString()));
                carros.save();
                limpiar();
                Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(this, "Los campos están vacios", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnListarDBORM:
                Toast.makeText(this, "Número de carros: "+Carro.getAllCars().size(), Toast.LENGTH_SHORT).show();
                try {
                    Carro carroL= new Carro();
                    listaCarros = carroL.getAllCars();
                    adapter = new CarroAdapter(listaCarros);
                    recyclerCarro.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setOnclickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cargarCajasdeTexto(v);
                        }
                    });
                    recyclerCarro.setAdapter(adapter);
                }catch (Exception ex){
                    Log.e("error", ex.getMessage());
                }
                break;
            case R.id.btnModificarDBORM:
                try {
                    Carro carroM= new Carro();
                    carroM.modifyCar(cajaPlaca.getText().toString(),cajaModel.getText().toString(),cajaMarca.getText().toString(),Integer.parseInt(cajaAnio.getText().toString()));
                    limpiar();
                    Toast.makeText(this, "Se ha modificado correctamente", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(this, "Ingrese la placa del vehículo que desea modificar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarDBORM:
                try {
                    Carro carroD= new Carro();
                    carroD.deleteCar(cajaPlaca.getText().toString());
                    limpiar();
                    Toast.makeText(this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(this, "Ingrese la placa del vehículo que desea eliminar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEliminarTodoDBORM:
                try {
                    limpiar();
                    confirmar();
                 }catch (Exception ex){
                    Log.e("error",ex.getMessage());
                }
                break;
            case R.id.btnBuscarDBORM:
                try {
                    Carro carroB;
                    List<Carro> listCar = new ArrayList<Carro>();
                    carroB=getCarByPlate(cajaPlaca.getText().toString());
                    limpiar();
                    listCar.add(carroB);
                    try {
                        adapter = new CarroAdapter(listCar);
                        recyclerCarro.setLayoutManager(new LinearLayoutManager(this));
                        adapter.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cargarCajasdeTexto(v);
                            }
                        });
                        recyclerCarro.setAdapter(adapter);
                    } catch (Exception e){
                        Log.e("error",e.getMessage());
                    }

                }catch (Exception ex){
                    Toast.makeText(this, "No se ha registrado un vehículo con esa placa", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void confirmar(){
        AlertDialog.Builder confirmacion = new AlertDialog.Builder(this);
        confirmacion.setTitle("Advertencia");
        confirmacion.setMessage("¿Está seguro que desea borrar todos los datos?");
        confirmacion.setCancelable(false);
        confirmacion.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                deleteAll();
                mostrarMensaje("Se ha eliminado correctamente");
            }
        });
        confirmacion.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                dialogo.cancel();
            }
        });
        confirmacion.show();
    }

    private void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void cargarCajasdeTexto(View view) {
        cajaPlaca.setText(""+listaCarros.get(recyclerCarro.getChildAdapterPosition(view)).getPlaca());
        cajaModel.setText(""+listaCarros.get(recyclerCarro.getChildAdapterPosition(view)).getModelo());
        cajaMarca.setText(""+listaCarros.get(recyclerCarro.getChildAdapterPosition(view)).getMarca());
        cajaAnio.setText(""+listaCarros.get(recyclerCarro.getChildAdapterPosition(view)).getAnio());
    }

    private void limpiar(){
        cajaPlaca.setText("");
        cajaMarca.setText("");
        cajaModel.setText("");
        cajaAnio.setText("");
    }
}
