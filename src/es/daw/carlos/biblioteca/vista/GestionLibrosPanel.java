package es.daw.carlos.biblioteca.vista;

import es.daw.carlos.biblioteca.dao.LibroDAO;
import es.daw.carlos.biblioteca.model.Libro;
import es.daw.carlos.biblioteca.model.Autor;
import es.daw.carlos.biblioteca.model.Categoria;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class GestionLibrosPanel extends JPanel {

    private JPanel panelLibros = new JPanel();
    private ArrayList<Libro> listaLibros;

    public GestionLibrosPanel() {
        initComponents();
    }

    public void initComponents() {
        String[] columnasTablaLibros = {"ISBN", "TÍTULO", "AÑO PUBLICACIÓN", "AUTOR_NOMBRE", "CATEGORÍA_NOMBRE"};
        DefaultTableModel modeloTablaLibros = new DefaultTableModel(columnasTablaLibros, 0);
        JTable tablaLibros = new JTable(modeloTablaLibros);

        listaLibros = LibroDAO.listarLibros();
        cargarLibrosTabla(modeloTablaLibros, listaLibros);

        JLabel tituloBienvenidaLibros = new JLabel("Libros");
        tituloBienvenidaLibros.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botonAñadirLibro = new JButton("Añadir");
        JButton botonBorrarLibro = new JButton("Borrar");
        JButton botonEditarLibro = new JButton("Editar");
        JButton botonBuscarLibro = new JButton("Buscar");
        JTextField textoBuscarLibro = new JTextField(10);
        JButton botonVolverAtras = new JButton("Volver");
        botonVolverAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVolverAtras.setVisible(false);

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

        JScrollPane panelTablaLibros = new JScrollPane(tablaLibros);

        panelLibros.setLayout(new BoxLayout(panelLibros, BoxLayout.Y_AXIS));
        panelLibros.add(panelTituloBienvenidaLibros);
        panelLibros.add(Box.createVerticalStrut(20));
        panelLibros.add(panelBotonesLibros);
        panelLibros.add(Box.createVerticalStrut(10));
        panelLibros.add(botonVolverAtras);
        panelLibros.add(Box.createVerticalStrut(20));
        panelLibros.add(panelTablaLibros);
        add(panelLibros);

        botonAñadirLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.getWindowAncestor(GestionLibrosPanel.this);
                JDialog confirmacionAñadirLibros = new JDialog(parentWindow, "Añadir nuevo libro", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionAñadirLibros.setSize(320, 450);
                confirmacionAñadirLibros.setLayout(new BorderLayout());

                JLabel labelIsbnLibro = new JLabel("Isbn:");
                JTextField textoIsbnLibro = new JTextField(20);
                textoIsbnLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelTituloLibro = new JLabel("Título:");
                JTextField textoTituloLibro = new JTextField(20);
                textoTituloLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelAnioPublicacionLibro = new JLabel("Año publicación:");
                JTextField textoAnioPublicacionLibro = new JTextField(20);
                textoAnioPublicacionLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelAutorIdLibro = new JLabel("Autor id:");
                JComboBox<Integer> comboBoxAutorIdLibro = new JComboBox<>();
                comboBoxAutorIdLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelCategoriaIdLibro = new JLabel("Categoría id");
                JComboBox<Integer> comboBoxCategoriaIdLibro = new JComboBox<>();
                comboBoxCategoriaIdLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JButton aceptarAñadirLibro = new JButton("Aceptar");
                JButton cancelarAñadirLibro = new JButton("Cancelar");

                for (Autor i : GestionAutoresPanel.getListaAutores()) {
                    comboBoxAutorIdLibro.addItem(i.getId());
                }

                for (Categoria i : GestionCategoriasPanel.getListaCategorias()) {
                    comboBoxCategoriaIdLibro.addItem(i.getId());
                }

                JLabel campoObligatorioIsbnLibro = new JLabel("Campo Obligatorio *");
                campoObligatorioIsbnLibro.setForeground(Color.red);
                campoObligatorioIsbnLibro.setVisible(false);
                JLabel campoObligatorioTituloLibro = new JLabel("Campo Obligatorio *");
                campoObligatorioTituloLibro.setForeground(Color.red);
                campoObligatorioTituloLibro.setVisible(false);
                JLabel campoObligatorioAnioPublicacionLibro = new JLabel("Campo Obligatorio *");
                campoObligatorioAnioPublicacionLibro.setForeground(Color.red);
                campoObligatorioAnioPublicacionLibro.setVisible(false);

                textoIsbnLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                textoTituloLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                textoAnioPublicacionLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                comboBoxAutorIdLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
                comboBoxCategoriaIdLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

                JPanel formularioAñadirLibros = new JPanel();
                formularioAñadirLibros.setLayout(new BoxLayout(formularioAñadirLibros, BoxLayout.Y_AXIS));
                formularioAñadirLibros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                formularioAñadirLibros.add(labelIsbnLibro);
                formularioAñadirLibros.add(Box.createVerticalStrut(10));
                formularioAñadirLibros.add(textoIsbnLibro);
                formularioAñadirLibros.add(campoObligatorioIsbnLibro);

                formularioAñadirLibros.add(Box.createVerticalStrut(10));

                formularioAñadirLibros.add(labelTituloLibro);
                formularioAñadirLibros.add(Box.createVerticalStrut(10));
                formularioAñadirLibros.add(textoTituloLibro);
                formularioAñadirLibros.add(campoObligatorioTituloLibro);

                formularioAñadirLibros.add(Box.createVerticalStrut(10));

                formularioAñadirLibros.add(labelAnioPublicacionLibro);
                formularioAñadirLibros.add(Box.createVerticalStrut(10));
                formularioAñadirLibros.add(textoAnioPublicacionLibro);
                formularioAñadirLibros.add(campoObligatorioAnioPublicacionLibro);

                formularioAñadirLibros.add(Box.createVerticalStrut(10));

                formularioAñadirLibros.add(labelAutorIdLibro);
                formularioAñadirLibros.add(Box.createVerticalStrut(10));
                formularioAñadirLibros.add(comboBoxAutorIdLibro);
                formularioAñadirLibros.add(Box.createVerticalStrut(10));
                formularioAñadirLibros.add(labelCategoriaIdLibro);
                formularioAñadirLibros.add(Box.createVerticalStrut(10));
                formularioAñadirLibros.add(comboBoxCategoriaIdLibro);

                JPanel panelBotonesAñadirLibro = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panelBotonesAñadirLibro.add(aceptarAñadirLibro);
                panelBotonesAñadirLibro.add(cancelarAñadirLibro);

                confirmacionAñadirLibros.add(formularioAñadirLibros, BorderLayout.CENTER);
                confirmacionAñadirLibros.add(panelBotonesAñadirLibro, BorderLayout.SOUTH);

                aceptarAñadirLibro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean camposVacios = false;
                        if (textoIsbnLibro.getText().trim().isEmpty()) {
                            campoObligatorioIsbnLibro.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioIsbnLibro.setVisible(false);
                        }

                        if (textoTituloLibro.getText().trim().isEmpty()) {
                            campoObligatorioTituloLibro.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioTituloLibro.setVisible(false);
                        }

                        Integer anioPublicacionLibro = null;

                        if (textoAnioPublicacionLibro.getText().trim().isEmpty()) {
                            campoObligatorioAnioPublicacionLibro.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioAnioPublicacionLibro.setVisible(false);
                            try {
                                anioPublicacionLibro = Integer.parseInt(textoAnioPublicacionLibro.getText().trim());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "El año de publicación "
                                        + "debe ser un número entero");
                            }
                        }

                        if (camposVacios == false) {
                            String isbnLibro = textoIsbnLibro.getText().trim();
                            String tituloLibro = textoTituloLibro.getText().trim();
                            Integer autorIdLibro = Integer.parseInt(comboBoxAutorIdLibro.getSelectedItem().toString());
                            Integer categoriaIdLibro = Integer.parseInt(comboBoxCategoriaIdLibro.getSelectedItem().toString());
                            Libro libro = new Libro(isbnLibro, tituloLibro, anioPublicacionLibro, autorIdLibro, categoriaIdLibro);
                            LibroDAO.insertarLibro(libro);
                            listaLibros = LibroDAO.listarLibros();
                            cargarLibrosTabla(modeloTablaLibros, listaLibros);
                            confirmacionAñadirLibros.dispose();
                        }
                    }
                });
                cancelarAñadirLibro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionAñadirLibros.dispose();
                    }
                });

                confirmacionAñadirLibros.setLocationRelativeTo(GestionLibrosPanel.this);
                confirmacionAñadirLibros.setVisible(true);
            }
        });

        botonEditarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaLibros.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(GestionLibrosPanel.this, "Por favor selecciona un libro para editar.");
                    return;
                }

                String isbnActualLibro = String.valueOf(tablaLibros.getValueAt(filaSeleccionada, 0));
                String tituloActualLibro = String.valueOf(tablaLibros.getValueAt(filaSeleccionada, 1));
                String anioPublicacionActualLibro = String.valueOf(tablaLibros.getValueAt(filaSeleccionada, 2));
                Integer autorIdActualLibro = Integer.parseInt(tablaLibros.getValueAt(filaSeleccionada, 3).toString());
                Integer categoriaIdActualLibro = Integer.parseInt(tablaLibros.getValueAt(filaSeleccionada, 4).toString());

                Window parentWindow = SwingUtilities.getWindowAncestor(GestionLibrosPanel.this);
                JDialog confirmacionEditarLibros = new JDialog(parentWindow, "Editar libro", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionEditarLibros.setSize(320, 450);
                confirmacionEditarLibros.setLayout(new BorderLayout());

                JLabel labelIsbnLibro = new JLabel("Isbn:");
                JTextField textoIsbnLibro = new JTextField(isbnActualLibro);
                textoIsbnLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelTituloLibro = new JLabel("Título:");
                JTextField textoTituloLibro = new JTextField(tituloActualLibro);
                textoTituloLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelAnioPublicacionLibro = new JLabel("Año publicación:");
                JTextField textoAnioPublicacionLibro = new JTextField(anioPublicacionActualLibro);
                textoAnioPublicacionLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelAutorIdLibro = new JLabel("Autor id:");
                JComboBox<Integer> comboBoxAutorIdLibro = new JComboBox<>();
                comboBoxAutorIdLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel labelCategoriaIdLibro = new JLabel("Categoría id");
                JComboBox<Integer> comboBoxCategoriaIdLibro = new JComboBox<>();
                comboBoxCategoriaIdLibro.setAlignmentX(Component.LEFT_ALIGNMENT);
                JButton aceptarEditarLibro = new JButton("Aceptar");
                JButton cancelarEditarLibro = new JButton("Cancelar");

                for (Autor i : GestionAutoresPanel.getListaAutores()) {
                    comboBoxAutorIdLibro.addItem(i.getId());
                }

                for (Categoria i : GestionCategoriasPanel.getListaCategorias()) {
                    comboBoxCategoriaIdLibro.addItem(i.getId());
                }
                

                JLabel campoObligatorioIsbnLibro = new JLabel("Campo Obligatorio *");
                campoObligatorioIsbnLibro.setForeground(Color.red);
                campoObligatorioIsbnLibro.setVisible(false);
                JLabel campoObligatorioTituloLibro = new JLabel("Campo Obligatorio *");
                campoObligatorioTituloLibro.setForeground(Color.red);
                campoObligatorioTituloLibro.setVisible(false);
                JLabel campoObligatorioAnioPublicacionLibro = new JLabel("Campo Obligatorio *");
                campoObligatorioAnioPublicacionLibro.setForeground(Color.red);
                campoObligatorioAnioPublicacionLibro.setVisible(false);

                textoIsbnLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                textoTituloLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                textoAnioPublicacionLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                comboBoxAutorIdLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
                comboBoxCategoriaIdLibro.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

                JPanel formularioEditarLibros = new JPanel();
                formularioEditarLibros.setLayout(new BoxLayout(formularioEditarLibros, BoxLayout.Y_AXIS));
                formularioEditarLibros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                formularioEditarLibros.add(labelIsbnLibro);
                formularioEditarLibros.add(Box.createVerticalStrut(10));
                formularioEditarLibros.add(textoIsbnLibro);
                formularioEditarLibros.add(campoObligatorioIsbnLibro);

                formularioEditarLibros.add(Box.createVerticalStrut(10));

                formularioEditarLibros.add(labelTituloLibro);
                formularioEditarLibros.add(Box.createVerticalStrut(10));
                formularioEditarLibros.add(textoTituloLibro);
                formularioEditarLibros.add(campoObligatorioTituloLibro);

                formularioEditarLibros.add(Box.createVerticalStrut(10));

                formularioEditarLibros.add(labelAnioPublicacionLibro);
                formularioEditarLibros.add(Box.createVerticalStrut(10));
                formularioEditarLibros.add(textoAnioPublicacionLibro);
                formularioEditarLibros.add(campoObligatorioAnioPublicacionLibro);

                formularioEditarLibros.add(Box.createVerticalStrut(10));

                formularioEditarLibros.add(labelAutorIdLibro);
                formularioEditarLibros.add(Box.createVerticalStrut(10));
                formularioEditarLibros.add(comboBoxAutorIdLibro);
                formularioEditarLibros.add(Box.createVerticalStrut(10));
                formularioEditarLibros.add(labelCategoriaIdLibro);
                formularioEditarLibros.add(Box.createVerticalStrut(10));
                formularioEditarLibros.add(comboBoxCategoriaIdLibro);

                JPanel panelBotonesEditarLibro = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panelBotonesEditarLibro.add(aceptarEditarLibro);
                panelBotonesEditarLibro.add(cancelarEditarLibro);

                confirmacionEditarLibros.add(formularioEditarLibros, BorderLayout.CENTER);
                confirmacionEditarLibros.add(panelBotonesEditarLibro, BorderLayout.SOUTH);

                aceptarEditarLibro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean camposVacios = false;
                        if (textoIsbnLibro.getText().trim().isEmpty()) {
                            campoObligatorioIsbnLibro.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioIsbnLibro.setVisible(false);
                        }

                        if (textoTituloLibro.getText().trim().isEmpty()) {
                            campoObligatorioTituloLibro.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioTituloLibro.setVisible(false);
                        }

                        Integer anioPublicacionLibro = null;

                        if (textoAnioPublicacionLibro.getText().trim().isEmpty()) {
                            campoObligatorioAnioPublicacionLibro.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioAnioPublicacionLibro.setVisible(false);
                            try {
                                anioPublicacionLibro = Integer.parseInt(textoAnioPublicacionLibro.getText().trim());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "El año de publicación "
                                        + "debe ser un número entero");
                            }
                        }

                        if (camposVacios == false) {
                            String isbnLibroNuevo = textoIsbnLibro.getText().trim();
                            String tituloLibroNuevo = textoTituloLibro.getText().trim();
                            Integer anioPublicacionLibroNuevo = Integer.parseInt(textoAnioPublicacionLibro.getText().trim().toString());
                            Integer autorIdLibroNuevo = Integer.parseInt(comboBoxAutorIdLibro.getSelectedItem().toString());
                            Integer categoriaIdLibroNuevo = Integer.parseInt(comboBoxCategoriaIdLibro.getSelectedItem().toString());
                            Libro libroActualizado = new Libro(isbnLibroNuevo, tituloLibroNuevo, anioPublicacionLibroNuevo, autorIdLibroNuevo, categoriaIdLibroNuevo);
                            LibroDAO.actualizarLibro(libroActualizado, isbnActualLibro);
                            listaLibros = LibroDAO.listarLibros();
                            cargarLibrosTabla(modeloTablaLibros, listaLibros);
                            confirmacionEditarLibros.dispose();
                        }
                    }
                });

                cancelarEditarLibro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionEditarLibros.dispose();
                    }
                });

                confirmacionEditarLibros.setLocationRelativeTo(GestionLibrosPanel.this);
                confirmacionEditarLibros.setVisible(true);
            }
        });

        botonBorrarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaLibros.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(GestionLibrosPanel.this, "Por favor selecciona un libro para borrar.");
                    return;
                }

                String isbnLibro = String.valueOf(tablaLibros.getValueAt(filaSeleccionada, 0));

                Window parentWindow = SwingUtilities.getWindowAncestor(GestionLibrosPanel.this);
                JDialog confirmacionBorrarLibros = new JDialog(parentWindow, "Borrar libro", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionBorrarLibros.setSize(320, 180);
                confirmacionBorrarLibros.setLayout(new BorderLayout());

                JLabel mensajeConfirmacionLibroBorrado = new JLabel("¿Estas seguro/a que quieres eliminar este libro?");
                mensajeConfirmacionLibroBorrado.setAlignmentX(CENTER_ALIGNMENT);
                JButton aceptarConfirmacionLibroBorrado = new JButton("Aceptar");
                JButton cancelarConfirmacionLibroBorrado = new JButton("Cancelar");

                JPanel panelBotonesConfirmacion = new JPanel();
                panelBotonesConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
                panelBotonesConfirmacion.add(aceptarConfirmacionLibroBorrado);
                panelBotonesConfirmacion.add(cancelarConfirmacionLibroBorrado);

                JPanel ventanaConfirmacion = new JPanel();
                ventanaConfirmacion.add(Box.createVerticalStrut(30));
                ventanaConfirmacion.setLayout(new BoxLayout(ventanaConfirmacion, BoxLayout.Y_AXIS));
                ventanaConfirmacion.add(mensajeConfirmacionLibroBorrado);
                ventanaConfirmacion.add(Box.createVerticalStrut(20));
                ventanaConfirmacion.add(panelBotonesConfirmacion);

                confirmacionBorrarLibros.add(ventanaConfirmacion, BorderLayout.CENTER);

                aceptarConfirmacionLibroBorrado.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LibroDAO.borrarLibro(isbnLibro);
                        listaLibros = LibroDAO.listarLibros();
                        cargarLibrosTabla(modeloTablaLibros, listaLibros);
                        confirmacionBorrarLibros.dispose();
                    }
                });

                cancelarConfirmacionLibroBorrado.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionBorrarLibros.dispose();
                    }
                });

                confirmacionBorrarLibros.setLocationRelativeTo(GestionLibrosPanel.this);
                confirmacionBorrarLibros.setVisible(true);
            }
        });

        botonBuscarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textoBuscarLibro.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor introduzca un valor "
                            + "en la búsqueda");
                    return;
                }
                String isbnBuscarLibro = textoBuscarLibro.getText();
                Libro libroBuscado = LibroDAO.buscarLibro(isbnBuscarLibro);

                if (libroBuscado != null) {
                    ArrayList<Libro> listaLibrosBuscados = new ArrayList<>();
                    listaLibrosBuscados.add(libroBuscado);
                    cargarLibrosTabla(modeloTablaLibros, listaLibrosBuscados);
                    botonVolverAtras.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado libro que "
                            + "coincida con el parámetro de búsqueda");
                }
            }
        });

        botonVolverAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaLibros = LibroDAO.listarLibros();
                cargarLibrosTabla(modeloTablaLibros, listaLibros);
                textoBuscarLibro.setText("");
                botonVolverAtras.setVisible(false);
            }
        });
    }

    public void cargarLibrosTabla(DefaultTableModel modeloTabla, ArrayList<Libro> listaLibros) {
        modeloTabla.setRowCount(0);;
        for (Libro i : listaLibros) {
            String[] nuevaFilaLibro = {i.getIsbn(), i.getTitulo(), String.valueOf(i.getAnioPublicacion()), String.valueOf(i.getAutor_id()), String.valueOf(i.getCategoria_id())};
            modeloTabla.addRow(nuevaFilaLibro);
        }
    }
}
