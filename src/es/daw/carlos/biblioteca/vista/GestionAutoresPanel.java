package es.daw.carlos.biblioteca.vista;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionAutoresPanel extends JFrame {
    
    private JPanel panelAutores = new JPanel();
    
    public GestionAutoresPanel() {
        initComponents();
    }
    
    public void initComponents() {
        setSize(500, 500);
        setTitle("Autores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel tituloBienvenidaAutores = new JLabel("Autores");
        tituloBienvenidaAutores.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton botonAñadirAutor = new JButton("Añadir");
        JButton botonBorrarAutor = new JButton("Borrar");
        JButton botonEditarAutor = new JButton("Editar");
        JButton botonBuscarAutor = new JButton("Buscar");
        JTextField textoBuscarAutor = new JTextField(10);
        JButton botonVolverBibliotecaApp = new JButton("Volver");
        
        String[] columnasTablaAutores = {"ID", "Nombre", "Nacionalidad"};
        DefaultTableModel modeloTablaAutores = new DefaultTableModel(columnasTablaAutores, 0);
        JTable tablaAutores = new JTable(modeloTablaAutores);
        
        JPanel panelTituloBienvenidaAutores = new JPanel();
        panelTituloBienvenidaAutores.setLayout(new BoxLayout(panelTituloBienvenidaAutores, BoxLayout.Y_AXIS));
        panelTituloBienvenidaAutores.add(Box.createVerticalStrut(20));
        panelTituloBienvenidaAutores.add(tituloBienvenidaAutores);
        
        JPanel panelBotonesAutores = new JPanel();
        panelBotonesAutores.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonesAutores.add(botonAñadirAutor);
        panelBotonesAutores.add(botonBorrarAutor);
        panelBotonesAutores.add(botonEditarAutor);
        panelBotonesAutores.add(botonBuscarAutor);
        panelBotonesAutores.add(textoBuscarAutor);
        
        JPanel panelBotonVolverBibliotecaApp = new JPanel();
        panelBotonVolverBibliotecaApp.add(botonVolverBibliotecaApp);
        
        JScrollPane panelTablaAutores = new JScrollPane(tablaAutores);
        
        panelAutores.setLayout(new BoxLayout(panelAutores, BoxLayout.Y_AXIS));
        panelAutores.add(panelTituloBienvenidaAutores);
        panelAutores.add(Box.createVerticalStrut(20));
        panelAutores.add(panelBotonesAutores);
        panelAutores.add(Box.createVerticalStrut(10));
        panelAutores.add(panelBotonVolverBibliotecaApp);
        panelAutores.add(Box.createVerticalStrut(20));
        panelAutores.add(panelTablaAutores);
        
        setContentPane(panelAutores);
        setVisible(false);
    }
    
    public JPanel devolverPanelAutores() {
        return panelAutores;
    }
}