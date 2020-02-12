package com.example.progra.modelo;

public class Usuario {
    private int documento;
    private String nombre;
    private String profesion;
    private String imagen;
    private String ruta_imagen;

    public Usuario(){

    }

    public Usuario(int documento, String nombre, String profesion, String imagen, String ruta_imagen) {
        this.documento = documento;
        this.nombre = nombre;
        this.profesion = profesion;
        this.imagen = imagen;
        this.ruta_imagen = ruta_imagen;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
}
