package com.example.progra.modelo;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name="carro")
public class Carro extends Model {
    @Column(name="placa", unique = true)
    private String placa;
    @Column(name="modelo", notNull = true)
    private String modelo;
    @Column(name="marca", notNull = true)
    private String marca;
    @Column(name="anio", notNull = true)
    private int anio;

    public Carro(){
        super();
    }

    public Carro(String placa, String modelo, String marca, int anio) {
        super();
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anio = anio;
    }

    public static List<Carro> getAllCars(){
        return new Select().from(Carro.class).execute();
    }

    public  static Carro getCarByPlate(String placa){
        if(new Select().from(Carro.class).where("placa=?",placa).executeSingle().equals(null)){
            return null;
        }
        else{
            return new Select().from(Carro.class).where("placa=?",placa).executeSingle();
        }
    }

    public  static void modifyCar(String placa,String modelo,String marca,int anio){
        Carro car;
        car= new Select().from(Carro.class).where("placa=?",placa).executeSingle();
        car.setMarca(marca);
        car.setModelo(modelo);
        car.setAnio(anio);
        car.save();
    }

    public  static void deleteCar(String placa){
        Carro car;
        car = new Select().from(Carro.class).where("placa=?",placa).executeSingle();
        car.delete();
    }

    public  static void deleteAll(){
        new Delete().from(Carro.class).execute();
    }



    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }



}
