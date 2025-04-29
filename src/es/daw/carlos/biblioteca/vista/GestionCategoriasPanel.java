package es.daw.carlos.biblioteca.vista;

import es.daw.carlos.biblioteca.dao.CategoriaDAO;
import es.daw.carlos.biblioteca.model.Categoria;
import es.daw.carlos.biblioteca.model.Libro;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionCategoriasPanel extends JPanel {
    
    private JPanel panelCategorias = new JPanel();
    private static ArrayList<Categoria> listaCategorias;
    
    // Método para utilizar la listaCategorias fuera de la clase
    public static ArrayList<Categoria> getListaCategorias() {
        return listaCategorias;
    }
    
    public GestionCategoriasPanel() {
        initComponents();
    }
    
    public void initComponents() {
        String[] columnasTablaCategorias = {"ID", "NOMBRE"};
        DefaultTableModel modeloTablaCategorias = new DefaultTableModel(columnasTablaCategorias, 0);
        JTable tablaCategorias = new JTable(modeloTablaCategorias);
        
        // Llamamos al método para cargar los registros al iniciar el programa
        listaCategorias = CategoriaDAO.listarCategorias();
        cargarCategoriasTabla(modeloTablaCategorias, listaCategorias);
        
        // Componentes de la interfaz
        JLabel tituloBienvenidaCategorias = new JLabel("Categorías");
        tituloBienvenidaCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton botonAñadirCategoria = new JButton("Añadir");
        JButton botonBorrarCategoria = new JButton("Borrar");
        JButton botonEditarCategoria = new JButton("Editar");
        JButton botonBuscarCategoria = new JButton("Buscar");
        JTextField textoBuscarCategoria = new JTextField(10);
        JButton botonVolverAtras = new JButton("Volver");
        botonVolverAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVolverAtras.setVisible(false);
        
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
        
        JScrollPane panelTablaCategorias = new JScrollPane(tablaCategorias);
        panelTablaCategorias.setPreferredSize(new Dimension(480, 300));
        
        panelCategorias.setLayout(new BoxLayout(panelCategorias, BoxLayout.Y_AXIS));
        panelCategorias.add(panelTituloBienvenidaCategorias);
        panelCategorias.add(Box.createVerticalStrut(20));
        panelCategorias.add(panelBotonesCategorias);
        panelCategorias.add(Box.createVerticalStrut(10));
        panelCategorias.add(botonVolverAtras);
        panelCategorias.add(Box.createVerticalStrut(20));
        panelCategorias.add(panelTablaCategorias);
        add(panelCategorias);
        
        botonAñadirCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.getWindowAncestor(GestionCategoriasPanel.this);
                JDialog confirmacionAñadirCategorias = new JDialog(parentWindow, "Añadir nueva categoría", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionAñadirCategorias.setSize(300, 180);
                confirmacionAñadirCategorias.setLayout(new BorderLayout());
                
                // Componentes ventana JDialog
                JLabel labelNombreCategoria = new JLabel("Nombre:");
                JTextField textoNombreCategoria = new JTextField(20);
                textoNombreCategoria.setAlignmentX(Component.LEFT_ALIGNMENT);
                JButton aceptarAñadirCategoria = new JButton("Aceptar");
                JButton cancelarAñadirCategoria = new JButton("Cancelar");

                JLabel campoObligatorioNombreCategoria = new JLabel("Campo Obligatorio *");
                campoObligatorioNombreCategoria.setForeground(Color.red);
                campoObligatorioNombreCategoria.setVisible(false);

                textoNombreCategoria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                
                JPanel formularioAñadirCategorias = new JPanel();
                formularioAñadirCategorias.setLayout(new BoxLayout(formularioAñadirCategorias, BoxLayout.Y_AXIS));
                formularioAñadirCategorias.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                formularioAñadirCategorias.add(labelNombreCategoria);
                formularioAñadirCategorias.add(Box.createVerticalStrut(10));
                formularioAñadirCategorias.add(textoNombreCategoria);
                formularioAñadirCategorias.add(campoObligatorioNombreCategoria);

                JPanel panelBotonesAñadirCategoria = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panelBotonesAñadirCategoria.add(aceptarAñadirCategoria);
                panelBotonesAñadirCategoria.add(cancelarAñadirCategoria);

                confirmacionAñadirCategorias.add(formularioAñadirCategorias, BorderLayout.CENTER);
                confirmacionAñadirCategorias.add(panelBotonesAñadirCategoria, BorderLayout.SOUTH);

                aceptarAñadirCategoria.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Comprobación campo vacio
                        if (textoNombreCategoria.getText().trim().isEmpty()) {
                            campoObligatorioNombreCategoria.setVisible(true);
                        } else {
                            String nombreCategoria = textoNombreCategoria.getText().trim();
                            // Usamos constructor personalizado (sin id)
                            Categoria categoria = new Categoria(nombreCategoria);
                            CategoriaDAO.insertarCategoria(categoria);
                            listaCategorias = CategoriaDAO.listarCategorias();
                            cargarCategoriasTabla(modeloTablaCategorias, listaCategorias);
                            confirmacionAñadirCategorias.dispose();
                        }
                    }
                });
                cancelarAñadirCategoria.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionAñadirCategorias.dispose();
                    }
                });

                confirmacionAñadirCategorias.setLocationRelativeTo(GestionCategoriasPanel.this);
                confirmacionAñadirCategorias.setVisible(true);
            }
        });
        
        botonEditarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaCategorias.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(GestionCategoriasPanel.this, "Por favor selecciona una categoría para editar.");
                    return;
                }

                int idCategoria = Integer.parseInt(tablaCategorias.getValueAt(filaSeleccionada, 0).toString());
                String nombreCategoriaActual = String.valueOf(tablaCategorias.getValueAt(filaSeleccionada, 1));

                Window parentWindow = SwingUtilities.getWindowAncestor(GestionCategoriasPanel.this);
                JDialog confirmacionEditarCategorias = new JDialog(parentWindow, "Editar categoría", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionEditarCategorias.setSize(300, 180);
                confirmacionEditarCategorias.setLayout(new BorderLayout());
                
                // Componentes ventana JDialog
                JLabel labelNombreCategoria = new JLabel("Nombre:");
                JTextField textoNombreCategoria = new JTextField(nombreCategoriaActual, 20);
                textoNombreCategoria.setAlignmentX(Component.LEFT_ALIGNMENT);
                JButton aceptarEditarCategoria = new JButton("Aceptar");
                JButton cancelarEditarCategoria = new JButton("Cancelar");

                JLabel campoObligatorioNombreCategoria = new JLabel("Campo Obligatorio *");
                campoObligatorioNombreCategoria.setForeground(Color.red);
                campoObligatorioNombreCategoria.setVisible(false);

                textoNombreCategoria.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

                JPanel formularioEditarCategorias = new JPanel();
                formularioEditarCategorias.setLayout(new BoxLayout(formularioEditarCategorias, BoxLayout.Y_AXIS));
                formularioEditarCategorias.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                formularioEditarCategorias.add(labelNombreCategoria);
                formularioEditarCategorias.add(Box.createVerticalStrut(10));
                formularioEditarCategorias.add(textoNombreCategoria);
                formularioEditarCategorias.add(campoObligatorioNombreCategoria);

                JPanel panelBotonesEditarCategoria = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panelBotonesEditarCategoria.add(aceptarEditarCategoria);
                panelBotonesEditarCategoria.add(cancelarEditarCategoria);

                confirmacionEditarCategorias.add(formularioEditarCategorias, BorderLayout.CENTER);
                confirmacionEditarCategorias.add(panelBotonesEditarCategoria, BorderLayout.SOUTH);

                aceptarEditarCategoria.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Comprobación campo vacio
                        if (textoNombreCategoria.getText().trim().isEmpty()) {
                            campoObligatorioNombreCategoria.setVisible(true);
                        } else {
                            String nombreCategoriaNueva = textoNombreCategoria.getText().trim();
                            // Usamos el constructor con id
                            Categoria categoriaActualizada = new Categoria(idCategoria, nombreCategoriaNueva);
                            CategoriaDAO.actualizarCategoria(categoriaActualizada);
                            listaCategorias = CategoriaDAO.listarCategorias();
                            cargarCategoriasTabla(modeloTablaCategorias, listaCategorias);
                            confirmacionEditarCategorias.dispose();
                        }
                    }
                });

                cancelarEditarCategoria.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionEditarCategorias.dispose();
                    }
                });

                confirmacionEditarCategorias.setLocationRelativeTo(GestionCategoriasPanel.this);
                confirmacionEditarCategorias.setVisible(true);
            }
        });

        botonBorrarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaCategorias.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(GestionCategoriasPanel.this, "Por favor selecciona una categoría para borrar.");
                    return;
                }

                int idCategoria = Integer.parseInt(tablaCategorias.getValueAt(filaSeleccionada, 0).toString());

                Window parentWindow = SwingUtilities.getWindowAncestor(GestionCategoriasPanel.this);
                JDialog confirmacionBorrarCategorias = new JDialog(parentWindow, "Borrar categoría", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionBorrarCategorias.setSize(320, 180);
                confirmacionBorrarCategorias.setLayout(new BorderLayout());
                
                // Componentes ventana JDialog
                JLabel mensajeConfirmacionCategoriaBorrada = new JLabel("¿Estas seguro/a que quieres eliminar esta categoría");
                mensajeConfirmacionCategoriaBorrada.setAlignmentX(CENTER_ALIGNMENT);
                JButton aceptarConfirmacionCategoriaBorrada = new JButton("Aceptar");
                JButton cancelarConfirmacionCategoriaBorrada = new JButton("Cancelar");

                JPanel panelBotonesConfirmacion = new JPanel();
                panelBotonesConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
                panelBotonesConfirmacion.add(aceptarConfirmacionCategoriaBorrada);
                panelBotonesConfirmacion.add(cancelarConfirmacionCategoriaBorrada);

                JPanel ventanaConfirmacion = new JPanel();
                ventanaConfirmacion.add(Box.createVerticalStrut(30));
                ventanaConfirmacion.setLayout(new BoxLayout(ventanaConfirmacion, BoxLayout.Y_AXIS));
                ventanaConfirmacion.add(mensajeConfirmacionCategoriaBorrada);
                ventanaConfirmacion.add(Box.createVerticalStrut(20));
                ventanaConfirmacion.add(panelBotonesConfirmacion);

                confirmacionBorrarCategorias.add(ventanaConfirmacion, BorderLayout.CENTER);

                aceptarConfirmacionCategoriaBorrada.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Comprobación si la categoría tiene asociado un libro
                        boolean categoriaExisteEnLibro = false;
                        for (Libro i : GestionLibrosPanel.getListaLibros()) {
                            if ((idCategoria == i.getCategoria_id())) {
                                categoriaExisteEnLibro = true;
                            }
                        }
                        if (categoriaExisteEnLibro) {
                            JOptionPane.showMessageDialog(null, "La categoría que quieres borrar "
                                        + "es una categoría de un libro existente");
                        } else {
                            // Pasamos el id de la categoría a borrar
                            CategoriaDAO.borrarCategoria(idCategoria);
                            listaCategorias= CategoriaDAO.listarCategorias();
                            cargarCategoriasTabla(modeloTablaCategorias, listaCategorias);
                            confirmacionBorrarCategorias.dispose();
                        }
                    }
                });

                cancelarConfirmacionCategoriaBorrada.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionBorrarCategorias.dispose();
                    }
                });

                confirmacionBorrarCategorias.setLocationRelativeTo(GestionCategoriasPanel.this);
                confirmacionBorrarCategorias.setVisible(true);
            }
        });

        botonBuscarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Comprobación búsqueda vacia
                if (textoBuscarCategoria.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor introduzca un valor "
                            + "en la búsqueda");
                    return;
                }
                try {
                    // Comprobación búsqueda con un número entero
                    int idBuscarCategoria = Integer.parseInt(textoBuscarCategoria.getText());
                    Categoria categoriaBuscada = CategoriaDAO.buscarCategoria(idBuscarCategoria);
                    
                    // Comprobación de resultados
                    if (categoriaBuscada != null) {
                        ArrayList<Categoria> listaCategoriasBuscados = new ArrayList<>();
                        listaCategoriasBuscados.add(categoriaBuscada);
                        cargarCategoriasTabla(modeloTablaCategorias, listaCategoriasBuscados);
                        botonVolverAtras.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado categoría que "
                                + "coincida con el parámetro de búsqueda");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un número entero");
                }
            }
        });
        
        botonVolverAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaCategorias = CategoriaDAO.listarCategorias();
                // Se actualizan los registros
                cargarCategoriasTabla(modeloTablaCategorias, listaCategorias);
                textoBuscarCategoria.setText(""); // Se limpia el parámetro de búsqueda
                botonVolverAtras.setVisible(false);
            }
        });
    }
    
    // Método para actualizar la tabla en cada operación
    public void cargarCategoriasTabla(DefaultTableModel modeloTabla, ArrayList<Categoria> listaCategorias) {
        modeloTabla.setRowCount(0);
        for (Categoria i : listaCategorias) {
            String[] nuevaFilaCategoria = {String.valueOf(i.getId()), i.getNombre()};
            modeloTabla.addRow(nuevaFilaCategoria);
        }
    }
}