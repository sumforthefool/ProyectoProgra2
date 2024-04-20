/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.prueba2;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.lang.*;
import static javafx.application.Application.launch;
import javax.swing.border.TitledBorder;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class Prueba2 extends JFrame
        implements ActionListener {

    static final Integer NUMEROS = 100;

    public JPanel panelNumeros = new JPanel();
    public JPanel panelDatos = new JPanel();

    public JTextField txtNombre;
    public JTextField txtTelefono;
    public JTextField txtEmail;

    public JLabel lblNombre;
    public JLabel lblNumero;
    public JLabel lblTelefono;
    public JLabel lblEmail;

    public JButton btnRegistrar;
    public JButton btnPagar;
    public JButton btnLimpiar;

    public JButton btnSortear;

    public Integer seleccionado = 0;

    public Integer vendidos = 0;
    Puesto[] btns = new Puesto[NUMEROS];

    public Prueba2() {

        setTitle("Sorteo");
        setSize(790, 550);

        Container c = getContentPane();
        c.setLayout(null);

        panelNumeros.setBounds(5, 5, 500, 500);
        c.add(panelNumeros);

        panelDatos.setBounds(510, 1, 260, 400);
        c.add(panelDatos);

        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();

        lblNombre = new JLabel("Nombre");
        lblNumero = new JLabel("", SwingConstants.CENTER);
        lblEmail = new JLabel("Email");
        lblTelefono = new JLabel("Telefono");

        btnRegistrar = new JButton("Registrar");
        btnPagar = new JButton("Pagar");
        btnLimpiar = new JButton("Limpiar");

        btnSortear = new JButton("Sortear");

        btnSortear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortear();
            }
        });

        JButton btnComprarAleatorio = new JButton("Comprar Número Aleatorio");
        btnComprarAleatorio.setBounds(510, 405, 260, 40);
        c.add(btnComprarAleatorio);

// ActionListener para el nuevo botón
        btnComprarAleatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar si aún quedan números disponibles
                if (vendidos < NUMEROS) {
                    // Generar un número aleatorio hasta que se encuentre uno no vendido
                    int numeroAleatorio;
                    do {
                        numeroAleatorio = (int) (Math.random() * NUMEROS);
                    } while (btns[numeroAleatorio].getNombre() != null);

                    // Mostrar el número aleatorio seleccionado
                    JOptionPane.showMessageDialog(null, "Has comprado el número " + numeroAleatorio);

                    // Simular la compra del número seleccionado
                    Puesto puestoSeleccionado = btns[numeroAleatorio];
                    puestoSeleccionado.setNombre(txtNombre.getText());
                    puestoSeleccionado.setTelefono(txtTelefono.getText());
                    puestoSeleccionado.setEmail(txtEmail.getText());
                    puestoSeleccionado.setBackground(Color.gray);
                    vendidos++;

                    // Actualizar la vista si se han vendido todos los números
                    if (vendidos == NUMEROS) {
                        JOptionPane.showMessageDialog(null, "¡Todos los números han sido vendidos!");
                    }
                } else {
                    // Mostrar un mensaje si todos los números ya han sido vendidos
                    JOptionPane.showMessageDialog(null, "¡Todos los números han sido vendidos!");
                }
            }
        });

        lblNumero.setFont(new Font("SansSerif", Font.BOLD, 30));

        Integer filas = ((Double) Math.sqrt(NUMEROS)).intValue();

        panelDatos.setLayout(new GridLayout(13, 1));

        panelDatos.add(lblNumero);
        panelDatos.add(lblEmail);
        panelDatos.add(txtEmail);
        panelDatos.add(lblNombre);
        panelDatos.add(txtNombre);
        panelDatos.add(lblTelefono);
        panelDatos.add(txtTelefono);
        panelDatos.add(new JSeparator());
        panelDatos.add(btnRegistrar);
        panelDatos.add(btnPagar);
        panelDatos.add(btnLimpiar);
        panelDatos.add(new JSeparator());

        panelDatos.add(btnSortear);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container c = ((JButton) e.getSource()).getParent().getParent();
                Puesto p = btns[Integer.parseInt(lblNumero.getText())];
                p.setNombre(txtNombre.getText());
                p.setTelefono(txtTelefono.getText());
                p.setEmail(txtEmail.getText());
                JOptionPane.showMessageDialog(c, "Compraste el numero "
                        + lblNumero.getText() + ". Gracias por participar es para una buena causa");
                p.setBackground(Color.gray);
            }
        });

        btnSortear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortear();
            }
        });

        btnLimpiar.addActionListener(limpiarListener());

        TitledBorder t = new TitledBorder("Datos del Comprador");
        panelDatos.setBorder(t);

        panelNumeros.setLayout(new GridLayout(filas, filas, 1, 1));

        for (Integer i = 0; i < NUMEROS; i++) {
            btns[i] = new Puesto(i.toString());
            btns[i].setBackground(Color.white);
            btns[i].addActionListener(this);
            panelNumeros.add(btns[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        Puesto p = (Puesto) e.getSource();

        if (p.getNombre() != null) {
            JOptionPane.showMessageDialog(this, "El numero "
                    + e.getActionCommand() + " ya esta vendido. Intenta con otro");
            txtNombre.setText(p.getNombre());
            txtEmail.setText(p.getEmail());
            txtTelefono.setText(p.getTelefono());
            return;
        }
        vendidos++;

        lblNumero.setText(e.getActionCommand());

        if (vendidos == NUMEROS) {
            Integer ganador = ((Double) (NUMEROS * Math.random())).intValue();
            JOptionPane.showMessageDialog(this, "El ganador es el numero "
                    + ganador.toString() + ". Felicitaciones a " + btns[ganador].getNombre());

            btns[ganador].setBackground(Color.yellow);
        }

    }

    public ActionListener limpiarListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNombre.setText(null);
                txtEmail.setText(null);
                txtTelefono.setText(null);
                lblNumero.setText(null);
                txtEmail.requestFocusInWindow();
            }
        };
    }

public void sortear() {
    List<Integer> nms = new ArrayList<Integer>();

    for (Integer i = 0; i < btns.length; i++) {
        nms.add(i);
    }

    Timer timer = new Timer(5, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (!nms.isEmpty()) {
                Integer a = nms.get((int) ((double) nms.size() * Math.random()));
                nms.remove(a);
                btns[a].setBackground(Color.black);
                if (nms.isEmpty()) {
                    btns[a].setBackground(Color.yellow);
                    ((Timer) e.getSource()).stop();
                    if (btns[a].getNombre() == null) {
                        JOptionPane.showMessageDialog(getParent(),
                                "Con el numero " + btns[a].getActionCommand() + " la casa gana");
                    } else {
                        JOptionPane.showMessageDialog(getParent(),
                                "El ganador es " + btns[a].getNombre() + ". Con el numero " + btns[a].getActionCommand());
                        // Generar la imagen con los datos del ganador
                        generarImagen(Integer.parseInt(btns[a].getActionCommand()), btns[a].getNombre(), btns[a].getTelefono(),
                                btns[a].getEmail());
                    }
                }
            }
        }
    });
    timer.start();
}

         

    public static void generarImagen(int numeroGanador, String nombre, String telefono, String email) {
        // Creamos una imagen en blanco con dimensiones específicas
        BufferedImage imagen = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagen.createGraphics();

        // Rellenamos el fondo de la imagen
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imagen.getWidth(), imagen.getHeight());

        // Dibujamos el número ganador y los datos del ganador en la imagen
        g2d.setColor(Color.BLACK);
        g2d.drawString("Número Ganador: " + numeroGanador, 20, 30);
        g2d.drawString("Nombre: " + nombre, 20, 60);
        g2d.drawString("Teléfono: " + telefono, 20, 90);
        g2d.drawString("Email: " + email, 20, 120);

        // Guardamos la imagen en un archivo
        File output = new File("ganador.png");
        try {
            ImageIO.write(imagen, "png", output);
            System.out.println("Imagen generada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen: " + e.getMessage());
        }

        // Liberamos los recursos
        g2d.dispose();
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioCRUD().setVisible(true);
            }
        });
    }
}
