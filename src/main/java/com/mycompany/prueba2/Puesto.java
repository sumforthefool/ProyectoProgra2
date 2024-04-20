/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prueba2;

import javax.swing.JButton;

/**
 *
 * @author Usuario
 */
public class Puesto extends JButton
{
    private String nombre;
    private String telefono;
    private String email;

    public Puesto() { }
    
    public Puesto(String numero)
    {
        super(numero);
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

}
