package com.example.progra.modelo;

import java.util.Date;

public class Artista {
    private String nombres;
    private String apellidos;
    private int imgfoto;
    private Date fechaNacimiento;
    private int botonDatos;
    private String nombreArtistico;
    private String pathFoto;


    public int getImgfoto() {
        return imgfoto;
    }
    public void setImgfoto(int imgfoto) {
        this.imgfoto = imgfoto;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }


    public int getBotonDatos() {
        return botonDatos;
    }

    public void setBotonDatos(int botonDatos) {
        this.botonDatos = botonDatos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


}
