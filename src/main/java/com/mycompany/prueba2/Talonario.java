package com.mycompany.prueba2;

public class Talonario {

    private int id;
    private String descripcion;
    private String premio;
    private String fecha; 
    private int valorNumero;

    public Talonario(String descripcion, String premio, String fecha, int valorNumero) {
        this.descripcion = descripcion;
        this.premio = premio;
        this.fecha = fecha;
        this.valorNumero = valorNumero;
    }

    // Métodos getter y setter para el campo id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Métodos getter para acceder a los campos privados
    public String getDescripcion() {
        return descripcion;
    }

    public String getPremio() {
        return premio;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public int getValorNumero() {
        return valorNumero;
    }
}
