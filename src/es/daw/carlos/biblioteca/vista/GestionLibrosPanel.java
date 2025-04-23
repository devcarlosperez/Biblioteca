package es.daw.carlos.biblioteca.vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionLibrosPanel extends JFrame {
    
    private JPanel panelLibros = new JPanel();
    
    public GestionLibrosPanel() {
        initComponents();
    }
    
    public void initComponents() {
        setSize(500, 500);
        setTitle("Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel tituloBienvenidaLibros = new JLabel("Libros");
        tituloBienvenidaLibros.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton botonAñadirLibro = new JButton("Añadir");
        JButton botonBorrarLibro = new JButton("Borrar");
        JButton botonEditarLibro = new JButton("Editar");
        JButton botonBuscarLibro = new JButton("Buscar");
        JTextField textoBuscarLibro = new JTextField(10);
        JButton botonVolverBibliotecaApp = new JButton("Volver");
        
        String[] columnasTablaLibros = {"Isbn", "Título", "Año publicación", "Autor_id", "Categoria_id"};
        DefaultTableModel modeloTablaLibros = new DefaultTableModel(columnasTablaLibros, 0);
        JTable tablaLibros = new JTable(modeloTablaLibros);
        
        JPanel panelTituloBienvenidaLibros = new JPanel();
        panelTituloBienvenidaLibros.setLayout(new BoxLayout(panelTituloBienvenidaLibros, BoxLayout.Y_AXIS));
        panelTituloBienvenidaLibros.add(Box.createVerticalStrut(20));
        panelTituloBienvenidaLibros.add(tituloBienvenidaLibros);
        
        JPanel panelBotonesLibros = new JPanel();
        panelBotonesLibros.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonesLibros.add(botonAñadirLibro);
        panelBotonesLibros.add(botonBorrarLibro);
        panelBotonesLibros.add(botonEditarLibro);
        panelBotonesLibros.add(botonBuscarLibro);
        panelBotonesLibros.add(textoBuscarLibro);
        
        JPanel panelBotonVolverBibliotecaApp = new JPanel();
        panelBotonVolverBibliotecaApp.add(botonVolverBibliotecaApp);
        
        JScrollPane panelTablaLibros = new JScrollPane(tablaLibros);
        
        panelLibros.setLayout(new BoxLayout(panelLibros, BoxLayout.Y_AXIS));
        panelLibros.add(panelTituloBienvenidaLibros);
        panelLibros.add(Box.createVerticalStrut(20));
        panelLibros.add(panelBotonesLibros);
        panelLibros.add(Box.createVerticalStrut(10));
        panelLibros.add(panelBotonVolverBibliotecaApp);
        panelLibros.add(Box.createVerticalStrut(20));
        panelLibros.add(panelTablaLibros);
        
        setContentPane(panelLibros);
        setVisible(false);
        
        botonVolverBibliotecaApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    
    public JPanel devolverPanelLibros() {
        return panelLibros;
    }
}