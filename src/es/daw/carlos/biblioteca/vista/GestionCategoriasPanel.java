package es.daw.carlos.biblioteca.vista;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionCategoriasPanel extends JPanel {
    
    private JPanel panelCategorias = new JPanel();
    private BibliotecaApp bibliotecaApp;
    
    public GestionCategoriasPanel(BibliotecaApp bibliotecaApp) {
        this.bibliotecaApp = bibliotecaApp;
        initComponents();
    }
    
    public void initComponents() {
        JLabel tituloBienvenidaCategorias = new JLabel("Categorias");
        tituloBienvenidaCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton botonAñadirCategoria = new JButton("Añadir");
        JButton botonBorrarCategoria = new JButton("Borrar");
        JButton botonEditarCategoria = new JButton("Editar");
        JButton botonBuscarCategoria = new JButton("Buscar");
        JTextField textoBuscarCategoria = new JTextField(10);
        JButton botonVolverBibliotecaApp = new JButton("Volver");
        
        String[] columnasTablaCategorias = {"ID", "Nombre"};
        DefaultTableModel modeloTablaCategorias = new DefaultTableModel(columnasTablaCategorias, 0);
        JTable tablaAutores = new JTable(modeloTablaCategorias);
        
        JPanel panelTituloBienvenidaCategorias = new JPanel();
        panelTituloBienvenidaCategorias.setLayout(new BoxLayout(panelTituloBienvenidaCategorias, BoxLayout.Y_AXIS));
        panelTituloBienvenidaCategorias.add(Box.createVerticalStrut(20));
        panelTituloBienvenidaCategorias.add(tituloBienvenidaCategorias);
        
        JPanel panelBotonesCategorias = new JPanel();
        panelBotonesCategorias.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonesCategorias.add(botonAñadirCategoria);
        panelBotonesCategorias.add(botonBorrarCategoria);
        panelBotonesCategorias.add(botonEditarCategoria);
        panelBotonesCategorias.add(botonBuscarCategoria);
        panelBotonesCategorias.add(textoBuscarCategoria);
        
        JPanel panelBotonVolverBibliotecaApp = new JPanel();
        panelBotonVolverBibliotecaApp.add(botonVolverBibliotecaApp);
        
        JScrollPane panelTablaCategorias = new JScrollPane(tablaAutores);
        
        panelCategorias.setLayout(new BoxLayout(panelCategorias, BoxLayout.Y_AXIS));
        panelCategorias.add(panelTituloBienvenidaCategorias);
        panelCategorias.add(Box.createVerticalStrut(20));
        panelCategorias.add(panelBotonesCategorias);
        panelCategorias.add(Box.createVerticalStrut(10));
        panelCategorias.add(panelBotonVolverBibliotecaApp);
        panelCategorias.add(Box.createVerticalStrut(20));
        panelCategorias.add(panelTablaCategorias);
        
        botonVolverBibliotecaApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bibliotecaApp.volverInicioBibliotecaApp();
            }
        });
    }
    
    public JPanel devolverPanelCategorias() {
        return panelCategorias;
    }
}