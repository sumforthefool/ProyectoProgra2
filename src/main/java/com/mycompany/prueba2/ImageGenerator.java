/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prueba2;

/**
 *
 * @author Usuario
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGenerator {

    public static void generarImagen(int numeroGanador, String nombre, String telefono, String email) {
        // Crear una nueva imagen en blanco
        BufferedImage imagen = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();

        // Definir el color de fondo
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 400, 200);

        // Definir la fuente y el color del texto
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        // Dibujar el número ganador
        g2d.drawString("Número Ganador: " + numeroGanador, 50, 50);

        // Dibujar los datos asociados
        g2d.drawString("Nombre: " + nombre, 50, 100);
        g2d.drawString("Teléfono: " + telefono, 50, 130);
        g2d.drawString("Email: " + email, 50, 160);

        // Liberar recursos de gráficos
        g2d.dispose();

        // Guardar la imagen en un archivo
        try {
            File output = new File("ganador.png");
            ImageIO.write(imagen, "png", output);
            System.out.println("Imagen generada correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
       
    }
}