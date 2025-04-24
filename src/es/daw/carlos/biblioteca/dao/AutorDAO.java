package es.daw.carlos.biblioteca.dao;

import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.model.Autor;
import java.sql.*;
import java.util.*;

public class AutorDAO {
    
    public static void insertarAutor(Autor autor) {
        Connection conn = ConexionBD.conectar();
        String insertarAutor = "INSER INTO AUTOR (NOMBRE, NACIONALIDAD)"
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
    
    public static void borrarAutor(Autor autor) {
        Connection conn = ConexionBD.conectar();
        String borrarAutor = "DELETE FROM Autor WHERE ID = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(borrarAutor);
            stmt.setInt(1, autor.getId());
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
        
    }
    
    public static ArrayList<Autor> listarAutores() {
        Connection conn = ConexionBD.conectar();
        ArrayList<Autor> listaAutores = new ArrayList<>();
        String leerAutores = "SELECT * FROM Autor";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(leerAutores);
            while (rs.next()) {
                listaAutores.add(new Autor(rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("nacionalidad")));
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
        return listaAutores;
    }
}