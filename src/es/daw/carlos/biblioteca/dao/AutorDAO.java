package es.daw.carlos.biblioteca.dao;

import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.model.Autor;
import java.sql.*;
import java.util.*;

public class AutorDAO {
    
    public static void insertarAutor(Autor autor) {
        Connection conn = ConexionBD.conectar();
        String insertarAutor = "INSERT INTO Autor (nombre, nacionalidad)"
                + "VALUES (?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertarAutor);
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getNacionalidad());
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
    
    public static void borrarAutor(int id) {
        Connection conn = ConexionBD.conectar();
        String borrarAutor = "DELETE FROM Autor WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(borrarAutor);
            stmt.setInt(1, id);
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
    
    public static void actualizarAutor(Autor autor) {
        Connection conn = ConexionBD.conectar();
        String actualizarAutor = "UPDATE Autor SET nombre = ?, nacionalidad = ?"
                + "WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(actualizarAutor);
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getNacionalidad());
            stmt.setInt(3, autor.getId());
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
    
    public static ArrayList<Autor> listarAutores() {
        Connection conn = ConexionBD.conectar();
        ArrayList<Autor> listaAutores = new ArrayList<>();
        String leerAutores = "SELECT * FROM Autor";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(leerAutores);
            boolean hayRegistros = false;
            while (rs.next()) {
                listaAutores.add(new Autor(rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("nacionalidad")));
                hayRegistros = true;
            }
            if (hayRegistros) {
                System.out.println("Se han leído correctamente los registros");
            } else {
                System.out.println("No se han encontrado registros en la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
        return listaAutores;
    }
    
    public static Autor buscarAutor(int id) {
        Connection conn = ConexionBD.conectar();
        String buscarAutor = "SELECT * FROM Autor WHERE id = ?";
        Autor autor = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(buscarAutor);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                autor = new Autor(rs.getInt("id"),
                rs.getString("nombre"), rs.getString("nacionalidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
        return autor;
    }
}