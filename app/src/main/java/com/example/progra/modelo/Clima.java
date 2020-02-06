package com.example.progra.modelo;

public class Clima {
    //Coordenadas
    private String lon;
    private String lat;
    //Clima
    private int id_clima;
    private String main;
    private String description;
    private String icon;
    //Base
    private String base;
    //Main
    private double temp;
    private int pressure;
    private int humidity;
    private double temp_min;
    private double temp_max;
    //Visibility
    private int visibility;
    //Wind
    private double speed;
    private int deg;
    //Clouds
    private int all;
    //Dt
    private String dt;
    //Sys
    private int type;
    private int id_sys;
    private double message;
    private String country;
    private int sunrise;
    private int sunset;
    //
    private int id;
    private String name;
    private int cod;


    public Clima(){}

    public Clima(String lon, String lat, int id_clima, String main, String description, String icon, String base, double temp, int pressure, int humidity, double temp_min, double temp_max, int visibility, double speed, int deg, int all, String dt, int type, int id_sys, double message, String country, int sunrise, int sunset, int id, String name, int cod) {
        this.lon = lon;
        this.lat = lat;
        this.id_clima = id_clima;
        this.main = main;
        this.description = description;
        this.icon = icon;
        this.base = base;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.visibility = visibility;
        this.speed = speed;
        this.deg = deg;
        this.all = all;
        this.dt = dt;
        this.type = type;
        this.id_sys = id_sys;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getId_clima() {
        return id_clima;
    }

    public void setId_clima(int id_clima) {
        this.id_clima = id_clima;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId_sys() {
        return id_sys;
    }

    public void setId_sys(int id_sys) {
        this.id_sys = id_sys;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
