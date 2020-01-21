package com.example.progra.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progra.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class HelperProducto extends SQLiteOpenHelper {

    public HelperProducto(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table producto (id_producto integer primary key autoincrement, codigo integer , descripcion text(200) not null, precio double not null, cantidad integer)");
    }

    public void insertar(Producto producto){
        ContentValues valores = new ContentValues();
        valores.put("codigo",producto.getCodigo());
        valores.put("descripcion",producto.getDescripcion());
        valores.put("precio",producto.getPrecio());
        valores.put("cantidad",producto.getCantidad());
        this.getWritableDatabase().insert("producto", null, valores);
    }

    public List<Producto> getAll(){
        List<Producto>lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from producto", null);
        if (cursor.moveToFirst()) {
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                lista.add(p);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void modificar(Producto producto){
        ContentValues values = new ContentValues();
        values.put("descripcion", producto.getDescripcion());
        values.put("precio",producto.getPrecio());
        values.put("cantidad",producto.getCantidad());
        this.getWritableDatabase().update(
                "producto", values, "codigo=" + producto.getCodigo(), null
        );
    }

    public void  eliminar(Producto producto){
        this.getWritableDatabase().delete(
                "producto", "codigo=" + producto.getCodigo(), null
        );
    }

    public void  eliminarTodo(){
        this.getWritableDatabase().delete(
                "producto", null, null
        );
    }

    public List<Producto> getProductByCode(String code){
        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery(
                "select * from producto where codigo=" +code  , null
        );
        if (cursor.moveToFirst()){
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                lista.add(p);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
