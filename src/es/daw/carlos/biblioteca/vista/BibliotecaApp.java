package es.daw.carlos.biblioteca.vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BibliotecaApp extends JFrame {
    
    private static JPanel panelBibliotecaApp = new JPanel();
    
    private GestionAutoresPanel gestionAutoresPanel;
    private GestionCategoriasPanel gestionCategoriasPanel;
    private GestionLibrosPanel gestionLibrosPanel;
    
    public BibliotecaApp() {
        initComponents();
    }
    
    public void initComponents() {
        gestionAutoresPanel = new GestionAutoresPanel(this);
        gestionCategoriasPanel = new GestionCategoriasPanel(this);
        gestionLibrosPanel = new GestionLibrosPanel(this);
        
        setSize(500, 500);
        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel tituloBienvenida = new JLabel("Bienvenido a tu biblioteca");
        tituloBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton botonPanelAutores = new JButton("Autores");
        JButton botonPanelCategorias = new JButton("Categorías");
        JButton botonPanelLibros = new JButton("Libros");
        
        JPanel panelTituloBienvenida = new JPanel();
        panelTituloBienvenida.setLayout(new BoxLayout(panelTituloBienvenida, BoxLayout.Y_AXIS));
        panelTituloBienvenida.add(Box.createVerticalStrut(20));
        panelTituloBienvenida.add(tituloBienvenida);
        
        JPanel panelBotonesBibliotecaApp = new JPanel();
        panelBotonesBibliotecaApp.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonesBibliotecaApp.add(botonPanelAutores);
        panelBotonesBibliotecaApp.add(botonPanelCategorias);
        panelBotonesBibliotecaApp.add(botonPanelLibros);
        
        panelBibliotecaApp.setLayout(new BoxLayout(panelBibliotecaApp, BoxLayout.Y_AXIS));
        panelBibliotecaApp.add(panelTituloBienvenida);
        panelBibliotecaApp.add(Box.createVerticalStrut(20));
        panelBibliotecaApp.add(panelBotonesBibliotecaApp);
        
        setContentPane(panelBibliotecaApp);
        setVisible(true);
        
        botonPanelAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBibliotecaApp.setVisible(false);
                setContentPane(gestionAutoresPanel.devolverPanelAutores());
            }
        });
        
        botonPanelCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBibliotecaApp.setVisible(false);
                setContentPane(gestionCategoriasPanel.devolverPanelCategorias());
            }
        });
        
        botonPanelLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBibliotecaApp.setVisible(false);
                setContentPane(gestionLibrosPanel.devolverPanelLibros());
            }
        });
    }
    
    public static JPanel devolverPanelBibliotecaApp() {
        return panelBibliotecaApp;
    }
    
    public void volverInicioBibliotecaApp() {
        panelBibliotecaApp.setVisible(true);
        setContentPane(panelBibliotecaApp);
    }
}