package com.example.progra.vistas.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.progra.R;
import com.example.progra.controlador.HelperProducto;
import com.example.progra.modelo.Producto;
import com.example.progra.vistas.adapter.ProductoAdapter;

import java.io.BufferedReader;
import java.util.List;

public class ActividadProductoHelper extends AppCompatActivity implements View.OnClickListener {

    EditText cajaDescripcion, cajaPrecio, cajaCantidad, cajaCodigo;
    Button botonCrear, botonListar, botonModificar, botonElimianr, botonBuscar, botonEliminarTodo;
    RecyclerView recyclerView;
    HelperProducto helperProducto;
    List<Producto> listaProducto;
    BufferedReader lector;
    ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_producto_helper);
        tomarControl();
    }
    private void tomarControl(){

        cajaDescripcion = findViewById(R.id.txtDescripcionDB);
        cajaPrecio = findViewById(R.id.txtPrecioDB);
        cajaCantidad = findViewById(R.id.txtCantidadDB);
        cajaCodigo = findViewById(R.id.txtCodigoDB);
        recyclerView = findViewById(R.id.recyclerViewDB);
        botonCrear = findViewById(R.id.btnCrearDB);
        botonListar = findViewById(R.id.btnListarDB);
        botonModificar= findViewById(R.id.btnModificarDB);
        botonElimianr = findViewById(R.id.btnEliminarDB);
        botonBuscar = findViewById(R.id.btnBuscarDB);
        botonEliminarTodo = findViewById(R.id.btnEliminarTodoDB);

        botonCrear.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonElimianr.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        botonEliminarTodo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
                case R.id.btnCrearDB:
            try {
                    HelperProducto conn = new HelperProducto(this,"name",null,1 );
                    SQLiteDatabase db = conn.getWritableDatabase();
                    Producto product = new Producto();
                    product.setDescripcion(cajaDescripcion.getText().toString());
                    product.setCantidad(Integer.parseInt(cajaCantidad.getText().toString()));
                    product.setPrecio(Double.parseDouble(cajaPrecio.getText().toString()));
                    product.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                    conn.insertar(product);
                    db.close();
                    limpiar();
                    Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                Log.e("error" , ex.getMessage());
            }
            break;
            case R.id.btnListarDB:
                try {
                    final HelperProducto conn = new HelperProducto(this,"name",null,1 );
                    SQLiteDatabase db = conn.getReadableDatabase();
                    listaProducto = conn.getAll();
                    adapter = new ProductoAdapter(listaProducto);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setOnclickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cargarCajasdeTexto(v);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    Log.e("lista: ", adapter.toString());
                    db.close();
                }catch (Exception ex){
                    Log.e("error", ex.getMessage());
                }
                break;
            case R.id.btnModificarDB:
                try {
                    HelperProducto helperProducto = new HelperProducto(this,"name",null,1 );
                    SQLiteDatabase db = helperProducto.getReadableDatabase();
                    Producto producto = new Producto();
                    producto.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                    producto.setDescripcion(cajaDescripcion.getText().toString());
                    producto.setCantidad(Integer.parseInt(cajaCantidad.getText().toString()));
                    producto.setPrecio(Double.parseDouble(cajaPrecio.getText().toString()));
                    producto.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                    helperProducto.modificar(producto);
                    db.close();
                    limpiar();
                    Toast.makeText(this, "Se ha modificado correctamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                }catch (Exception ex){
                    Log.e("error",ex.getMessage());
                }
                break;
            case R.id.btnEliminarDB:
                try {
                    HelperProducto helperProducto = new HelperProducto(this,"name",null,1 );
                    SQLiteDatabase db = helperProducto.getReadableDatabase();
                    Producto producto = new Producto();
                    producto.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                    helperProducto.eliminar(producto);
                    Toast.makeText(this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                    db.close();
                }catch (Exception ex){
                    Log.e("error: ", ex.getMessage());
                }
                break;
            case R.id.btnBuscarDB:
                try {
                    HelperProducto helperProducto = new HelperProducto(this,"name",null,1 );
                    SQLiteDatabase db = helperProducto.getReadableDatabase();
                    Producto producto = new Producto();
                    producto.setCodigo(Integer.parseInt(cajaCodigo.getText().toString()));
                    String codidgo = (cajaCodigo.getText().toString());
                    listaProducto = helperProducto.getProductByCode(codidgo);
                    adapter = new ProductoAdapter(listaProducto);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(adapter);
                    db.close();
                }catch (Exception ex){
                    Log.e("error: ", ex.getMessage());
                }
                break;
            case R.id.btnEliminarTodoDB:
                try{
                    HelperProducto helperProducto = new HelperProducto(this,"name",null,1 );
                    SQLiteDatabase db = helperProducto.getReadableDatabase();
                    helperProducto.eliminarTodo();
                    db.close();
                    limpiar();
                    Toast.makeText(this, "Se eliminaron todos los productos correctamente", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("Error", ex.getMessage());
                }
                break;
        }
    }
    private void cargarCajasdeTexto(View view) {
       cajaCodigo.setText(""+listaProducto.get(recyclerView.getChildAdapterPosition(view)).getCodigo());
       cajaDescripcion.setText(""+listaProducto.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
       cajaPrecio.setText(""+listaProducto.get(recyclerView.getChildAdapterPosition(view)).getPrecio());
       cajaCantidad.setText(""+listaProducto.get(recyclerView.getChildAdapterPosition(view)).getCantidad());

       }

    private void limpiar(){
        cajaCodigo.setText("");
        cajaDescripcion.setText("");
        cajaPrecio.setText("");
        cajaCantidad.setText("");
    }
}
