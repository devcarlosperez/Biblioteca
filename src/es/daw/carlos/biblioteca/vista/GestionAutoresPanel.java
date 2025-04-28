package es.daw.carlos.biblioteca.vista;

import es.daw.carlos.biblioteca.dao.AutorDAO;
import es.daw.carlos.biblioteca.model.Autor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class GestionAutoresPanel extends JPanel {

    private JPanel panelAutores = new JPanel();

    public GestionAutoresPanel() {
        initComponents();
    }

    public void initComponents() {
        String[] columnasTablaAutores = {"ID", "Nombre", "Nacionalidad"};
        DefaultTableModel modeloTablaAutores = new DefaultTableModel(columnasTablaAutores, 0);
        JTable tablaAutores = new JTable(modeloTablaAutores);

        ArrayList<Autor> listaAutores = AutorDAO.listarAutores();
        cargarAutoresTabla(modeloTablaAutores, listaAutores);

        JLabel tituloBienvenidaAutores = new JLabel("Autores");
        tituloBienvenidaAutores.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botonAñadirAutor = new JButton("Añadir");
        JButton botonBorrarAutor = new JButton("Borrar");
        JButton botonEditarAutor = new JButton("Editar");
        JButton botonBuscarAutor = new JButton("Buscar");
        JTextField textoBuscarAutor = new JTextField(10);
        JButton botonVolverAtras = new JButton("Volver");
        botonVolverAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVolverAtras.setVisible(false);

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

        JScrollPane panelTablaAutores = new JScrollPane(tablaAutores);

        panelAutores.setLayout(new BoxLayout(panelAutores, BoxLayout.Y_AXIS));
        panelAutores.add(panelTituloBienvenidaAutores);
        panelAutores.add(Box.createVerticalStrut(20));
        panelAutores.add(panelBotonesAutores);
        panelAutores.add(Box.createVerticalStrut(10));
        panelAutores.add(botonVolverAtras);
        panelAutores.add(Box.createVerticalStrut(20));
        panelAutores.add(panelTablaAutores);
        add(panelAutores);

        botonAñadirAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.getWindowAncestor(GestionAutoresPanel.this);
                JDialog confirmacionAñadirAutores = new JDialog(parentWindow, "Añadir nuevo autor", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionAñadirAutores.setSize(300, 300);
                confirmacionAñadirAutores.setLayout(new BorderLayout());

                JLabel labelNombreAutor = new JLabel("Nombre:");
                JTextField textoNombreAutor = new JTextField(20);
                JLabel labelNacionalidadAutor = new JLabel("Nacionalidad:");
                JTextField textoNacionalidadAutor = new JTextField(20);
                JButton aceptarAñadirAutor = new JButton("Aceptar");
                JButton cancelarAñadirAutor = new JButton("Cancelar");

                JLabel campoObligatorioNombreAutor = new JLabel("Campo Obligatorio *");
                campoObligatorioNombreAutor.setForeground(Color.red);
                campoObligatorioNombreAutor.setVisible(false);
                JLabel campoObligatorioNacionalidadAutor = new JLabel("Campo Obligatorio *");
                campoObligatorioNacionalidadAutor.setForeground(Color.red);
                campoObligatorioNacionalidadAutor.setVisible(false);

                textoNombreAutor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                textoNacionalidadAutor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

                JPanel formularioAñadirAutores = new JPanel();
                formularioAñadirAutores.setLayout(new BoxLayout(formularioAñadirAutores, BoxLayout.Y_AXIS));
                formularioAñadirAutores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                formularioAñadirAutores.add(labelNombreAutor);
                formularioAñadirAutores.add(Box.createVerticalStrut(10));
                formularioAñadirAutores.add(textoNombreAutor);
                formularioAñadirAutores.add(campoObligatorioNombreAutor);

                formularioAñadirAutores.add(Box.createVerticalStrut(10));

                formularioAñadirAutores.add(labelNacionalidadAutor);
                formularioAñadirAutores.add(Box.createVerticalStrut(10));
                formularioAñadirAutores.add(textoNacionalidadAutor);
                formularioAñadirAutores.add(campoObligatorioNacionalidadAutor);

                JPanel panelBotonesAñadirAutor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                panelBotonesAñadirAutor.add(aceptarAñadirAutor);
                panelBotonesAñadirAutor.add(cancelarAñadirAutor);

                confirmacionAñadirAutores.add(formularioAñadirAutores, BorderLayout.CENTER);
                confirmacionAñadirAutores.add(panelBotonesAñadirAutor, BorderLayout.SOUTH);

                aceptarAñadirAutor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean camposVacios = false;
                        if (textoNombreAutor.getText().trim().isEmpty()) {
                            campoObligatorioNombreAutor.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioNombreAutor.setVisible(false);
                        }

                        if (textoNacionalidadAutor.getText().trim().isEmpty()) {
                            campoObligatorioNacionalidadAutor.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioNacionalidadAutor.setVisible(false);
                        }

                        if (camposVacios == false) {
                            String nombreAutor = textoNombreAutor.getText().trim();
                            String nacionalidadAutor = textoNacionalidadAutor.getText().trim();
                            Autor autor = new Autor(nombreAutor, nacionalidadAutor);
                            AutorDAO.insertarAutor(autor);
                            ArrayList<Autor> listaAutores = AutorDAO.listarAutores();
                            cargarAutoresTabla(modeloTablaAutores, listaAutores);
                            confirmacionAñadirAutores.dispose();
                        }
                    }
                });
                cancelarAñadirAutor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionAñadirAutores.dispose();
                    }
                });

                confirmacionAñadirAutores.setLocationRelativeTo(GestionAutoresPanel.this);
                confirmacionAñadirAutores.setVisible(true);
            }
        });

        botonEditarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaAutores.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(GestionAutoresPanel.this, "Por favor selecciona un autor para editar.");
                    return;
                }

                int idAutor = Integer.parseInt(tablaAutores.getValueAt(filaSeleccionada, 0).toString());
                String nombreAutorActual = String.valueOf(tablaAutores.getValueAt(filaSeleccionada, 1));
                String nacionalidadAutorActual = String.valueOf(tablaAutores.getValueAt(filaSeleccionada, 2));

                Window parentWindow = SwingUtilities.getWindowAncestor(GestionAutoresPanel.this);
                JDialog confirmacionEditarAutores = new JDialog(parentWindow, "Editar autor", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionEditarAutores.setSize(300, 300);
                confirmacionEditarAutores.setLayout(new BorderLayout());

                JLabel labelNombreAutor = new JLabel("Nombre:");
                JTextField textoNombreAutor = new JTextField(nombreAutorActual, 20);
                JLabel labelNacionalidadAutor = new JLabel("Nacionalidad:");
                JTextField textoNacionalidadAutor = new JTextField(nacionalidadAutorActual, 20);
                JButton aceptarEditarAutor = new JButton("Aceptar");
                JButton cancelarEditarAutor = new JButton("Cancelar");

                JLabel campoObligatorioNombreAutor = new JLabel("Campo Obligatorio *");
                campoObligatorioNombreAutor.setForeground(Color.red);
                campoObligatorioNombreAutor.setVisible(false);
                JLabel campoObligatorioNacionalidadAutor = new JLabel("Campo Obligatorio *");
                campoObligatorioNacionalidadAutor.setForeground(Color.red);
                campoObligatorioNacionalidadAutor.setVisible(false);

                textoNombreAutor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                textoNacionalidadAutor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

                JPanel formularioEditarAutores = new JPanel();
                formularioEditarAutores.setLayout(new BoxLayout(formularioEditarAutores, BoxLayout.Y_AXIS));
                formularioEditarAutores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                formularioEditarAutores.add(labelNombreAutor);
                formularioEditarAutores.add(Box.createVerticalStrut(10));
                formularioEditarAutores.add(textoNombreAutor);
                formularioEditarAutores.add(campoObligatorioNombreAutor);

                formularioEditarAutores.add(Box.createVerticalStrut(10));

                formularioEditarAutores.add(labelNacionalidadAutor);
                formularioEditarAutores.add(Box.createVerticalStrut(10));
                formularioEditarAutores.add(textoNacionalidadAutor);
                formularioEditarAutores.add(campoObligatorioNacionalidadAutor);

                JPanel panelBotonesEditarAutor = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                panelBotonesEditarAutor.add(aceptarEditarAutor);
                panelBotonesEditarAutor.add(cancelarEditarAutor);

                confirmacionEditarAutores.add(formularioEditarAutores, BorderLayout.CENTER);
                confirmacionEditarAutores.add(panelBotonesEditarAutor, BorderLayout.SOUTH);

                aceptarEditarAutor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean camposVacios = false;
                        if (textoNombreAutor.getText().trim().isEmpty()) {
                            campoObligatorioNombreAutor.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioNombreAutor.setVisible(false);
                        }

                        if (textoNacionalidadAutor.getText().trim().isEmpty()) {
                            campoObligatorioNacionalidadAutor.setVisible(true);
                            camposVacios = true;
                        } else {
                            campoObligatorioNacionalidadAutor.setVisible(false);
                        }

                        if (camposVacios == false) {
                            String nombreAutorNuevo = textoNombreAutor.getText().trim();
                            String nacionalidadAutorNueva = textoNacionalidadAutor.getText().trim();
                            Autor autorActualizado = new Autor(idAutor, nombreAutorNuevo, nacionalidadAutorNueva);  // IMPORTANTE pasar ID
                            AutorDAO.actualizarAutor(autorActualizado);
                            ArrayList<Autor> listaAutores = AutorDAO.listarAutores();
                            cargarAutoresTabla(modeloTablaAutores, listaAutores);
                            confirmacionEditarAutores.dispose();
                        }
                    }
                });

                cancelarEditarAutor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionEditarAutores.dispose();
                    }
                });

                confirmacionEditarAutores.setLocationRelativeTo(GestionAutoresPanel.this);
                confirmacionEditarAutores.setVisible(true);
            }
        });

        botonBorrarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaAutores.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(GestionAutoresPanel.this, "Por favor selecciona un autor para borrar.");
                    return;
                }

                int idAutor = Integer.parseInt(tablaAutores.getValueAt(filaSeleccionada, 0).toString());

                Window parentWindow = SwingUtilities.getWindowAncestor(GestionAutoresPanel.this);
                JDialog confirmacionBorrarAutores = new JDialog(parentWindow, "Borrar autor", Dialog.ModalityType.APPLICATION_MODAL);
                confirmacionBorrarAutores.setSize(320, 180);
                confirmacionBorrarAutores.setLayout(new BorderLayout());

                JLabel mensajeConfirmacionAutorBorrado = new JLabel("¿Estas seguro/a que quieres eliminar este autor?");
                mensajeConfirmacionAutorBorrado.setAlignmentX(CENTER_ALIGNMENT);
                JButton aceptarConfirmacionAutorBorrado = new JButton("Aceptar");
                JButton cancelarConfirmacionAutorBorrado = new JButton("Cancelar");

                JPanel panelBotonesConfirmacion = new JPanel();
                panelBotonesConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
                panelBotonesConfirmacion.add(aceptarConfirmacionAutorBorrado);
                panelBotonesConfirmacion.add(cancelarConfirmacionAutorBorrado);

                JPanel ventanaConfirmacion = new JPanel();
                ventanaConfirmacion.add(Box.createVerticalStrut(30));
                ventanaConfirmacion.setLayout(new BoxLayout(ventanaConfirmacion, BoxLayout.Y_AXIS));
                ventanaConfirmacion.add(mensajeConfirmacionAutorBorrado);
                ventanaConfirmacion.add(Box.createVerticalStrut(20));
                ventanaConfirmacion.add(panelBotonesConfirmacion);

                confirmacionBorrarAutores.add(ventanaConfirmacion, BorderLayout.CENTER);

                aceptarConfirmacionAutorBorrado.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AutorDAO.borrarAutor(idAutor);
                        ArrayList<Autor> listaAutores = AutorDAO.listarAutores();
                        cargarAutoresTabla(modeloTablaAutores, listaAutores);
                        confirmacionBorrarAutores.dispose();
                    }
                });

                cancelarConfirmacionAutorBorrado.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmacionBorrarAutores.dispose();
                    }
                });

                confirmacionBorrarAutores.setLocationRelativeTo(GestionAutoresPanel.this);
                confirmacionBorrarAutores.setVisible(true);
            }
        });

        botonBuscarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idBuscarAutor = Integer.parseInt(textoBuscarAutor.getText());
                    Autor autorBuscado = AutorDAO.buscarAutor(idBuscarAutor);

                    if (autorBuscado != null) {
                        ArrayList<Autor> listaAutoresBuscados = new ArrayList<>();
                        listaAutoresBuscados.add(autorBuscado);
                        cargarAutoresTabla(modeloTablaAutores, listaAutoresBuscados);
                        botonVolverAtras.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado autor que"
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
                ArrayList<Autor> listaAutores = AutorDAO.listarAutores();
                cargarAutoresTabla(modeloTablaAutores, listaAutores);
                botonVolverAtras.setVisible(false);
            }
        });
    }

    public void cargarAutoresTabla(DefaultTableModel modeloTabla, ArrayList<Autor> listaAutores) {
        modeloTabla.setRowCount(0);
        for (Autor i : listaAutores) {
            String[] nuevaFilaAutor = {String.valueOf(i.getId()), i.getNombre(), i.getNacionalidad()};
            modeloTabla.addRow(nuevaFilaAutor);
        }
    }
}
