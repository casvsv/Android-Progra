package com.example.progra.modelo;

public class Ruta {
    private double Lat;
    private double Lng;
    private String Titulo;
    private String Descripcion;
    private String Icono;

    public Ruta(){
        Titulo = "";
        Lat = 0;
        Lng = 0;
        Descripcion = "";
        Icono = "";
    }
    public Ruta(double lat, double lng, String titulo,String descripcion,String icono) {
        Lat = lat;
        Lng = lng;
        Titulo = titulo;
        Descripcion = descripcion;
        Icono = icono;
    }

    public String getIcono() {
        return Icono;
    }

    public void setIcono(String icono) {
        Icono = icono;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
