package es.daw.carlos.biblioteca.dao;

import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.model.Libro;
import java.sql.*;
import java.util.*;

public class LibroDAO {
    
    public static void insertarLibro(Libro libro) {
        Connection conn = ConexionBD.conectar();
        String insertarLibro = "INSERT INTO Libro (isbn, titulo, anio_publicacion,"
                + "autor_id, categoria_id)"
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertarLibro);
            stmt.setString(1, libro.getIsbn());
            stmt.setString(2, libro.getTitulo());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setInt(4, libro.getAutorId());
            stmt.setInt(5, libro.getCategoriaId());
            int resultado = stmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("Se ha insertado el registro correctamente");
            } else {
                System.out.println("Error al insertar el registro");
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
    }
    
    public static void borrarCategoria(String isbn) {
        Connection conn = ConexionBD.conectar();
        String borrarAutor = "DELETE FROM Libro WHERE isbn = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(borrarAutor);
            stmt.setString(1, isbn);
            int resultado = stmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("Se ha borrado el registro correctamente");
            } else {
                System.out.println("Error al borrar el registro");
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
    }
    
    public static void actualizarLibro(Libro libro) {
        Connection conn = ConexionBD.conectar();
        String actualizarCategoria = "UPDATE Libro SET isbn = ?, titulo = ?,"
                + "anio_publicacion = ?, autor_id = ?, categoria_id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(actualizarCategoria);
            stmt.setString(1, libro.getIsbn());
            stmt.setString(2, libro.getTitulo());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setInt(4, libro.getAutorId());
            stmt.setInt(5, libro.getCategoriaId());
            int resultado = stmt.executeUpdate();
            if (resultado == 1) {
                System.out.println("Se ha actualizado el registro correctamente");
            } else {
                System.out.println("Error al actualizar el registro");
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
    }
    
    public static ArrayList<Libro> listarLibros() {
        Connection conn = ConexionBD.conectar();
        ArrayList<Libro> listaLibros = new ArrayList<>();
        String leerLibros = "SELECT * FROM Libro";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(leerLibros);
            boolean hayRegistros = false;
            while (rs.next()) {
                listaLibros.add(new Libro(rs.getString("isbn"), 
                        rs.getString("titulo"), rs.getInt("anio_publicacion"),
                        rs.getInt("autor_id"), rs.getInt("categoria_id")));
            }
            if (hayRegistros) {
                System.out.println("Se han leído correctamente los registros");
            } else {
                System.out.println("No se han encontrado registros en la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
        return listaLibros;
    }
}