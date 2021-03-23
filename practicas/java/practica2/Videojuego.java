package practica2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Videojuego extends JFrame {

    private Circulo circulo;

    public Videojuego(){
        initComponets();
    }

    public void initComponets(){

        //this.setLayout(new BorderLayout());

        circulo = new Circulo();
        this.add(circulo);

        btnCirculo = new JButton("Circulo");
        btnCirculo.setFocusable(false);
        btnCirculo.setFont(new Font("Open Sans", 0, 14));
        btnCirculo.setPreferredSize(new Dimension(90,30));

        btnCuadrado = new JButton("Cuadrado");
        btnCuadrado.setFocusable(false);
        btnCuadrado.setFont(new Font("Open Sans", 0, 14));
        btnCuadrado.setPreferredSize(new Dimension(90,30));

        barraBoton = new JPanel();
        barraBoton.add(btnCirculo);

        contDibujo = new JPanel();

        contOpciones = new JPanel();
        contOpciones.setBackground(Color.CYAN);
        contOpciones.setPreferredSize(new Dimension(150,500));
        contOpciones.add(circulo);
        contOpciones.add(btnCuadrado);

        //this.add(barraBoton, BorderLayout.NORTH);
        //this.add(contDibujo, BorderLayout.CENTER);
        //this.add(contOpciones, BorderLayout.EAST);

        //Personalizacion de la ventana
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    
    public static void main(String[] args) {
        Videojuego videojuego = new Videojuego();
        videojuego.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Variables declaration
    JButton btnCirculo;
    JButton btnRombo;
    JButton btnCuadrado;
    JPanel barraBoton;
    JPanel contDibujo;
    JPanel contOpciones;
    // End of variables declaration
    
}