package com.example.progra.modelo;

public class Ruta {
    private double Lat;
    private double Lng;
    private String Titulo;
    private String Descripcion;
    private String Icono;
    private String Informacion;
    private String ImagenLugar;

    public Ruta(){
        Titulo = "";
        Lat = 0;
        Lng = 0;
        Descripcion = "";
        Icono = "";
        Informacion = "";
        ImagenLugar = "";
    }
    public Ruta(double lat, double lng, String titulo,String descripcion,String icono,String informacion, String imagenLugar) {
        Lat = lat;
        Lng = lng;
        Titulo = titulo;
        Descripcion = descripcion;
        Icono = icono;
        Informacion = informacion;
        ImagenLugar = imagenLugar;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String informacion) {
        Informacion = informacion;
    }

    public String getImagenLugar() {
        return ImagenLugar;
    }

    public void setImagenLugar(String imagenLugar) {
        ImagenLugar = imagenLugar;
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
