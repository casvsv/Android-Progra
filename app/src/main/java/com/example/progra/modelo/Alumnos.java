package com.example.progra.modelo;

public class Alumnos {
    private int idalumno;
    private String nombre;
    private String direccion;

    public Alumnos(){

    }

    public Alumnos(int idalumno, String nombre, String direccion) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.direccion = direccion;
    }


    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
