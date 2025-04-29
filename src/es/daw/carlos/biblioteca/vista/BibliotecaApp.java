package es.daw.carlos.biblioteca.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BibliotecaApp extends JFrame {
    
    private JTabbedPane menuBibliotecaApp = new JTabbedPane();
    
    public BibliotecaApp() {
        initComponents();
    }
    
    public void initComponents() {
        setSize(500, 500);
        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        menuBibliotecaApp.addTab("Autores", new GestionAutoresPanel());
        menuBibliotecaApp.addTab("Categorías", new GestionCategoriasPanel());
        menuBibliotecaApp.addTab("Libros", new GestionLibrosPanel());
        
        add(menuBibliotecaApp); // Añadimos el panel al frame
        setVisible(true);
    }
}