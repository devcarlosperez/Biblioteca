package es.daw.carlos.biblioteca.dao;

import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.model.Categoria;
import java.sql.*;
import java.util.*;

public class CategoriaDAO {
    
    public static void insertarCategoria(Categoria categoria) {
        Connection conn = ConexionBD.conectar();
        String insertarCategoria = "INSERT INTO Categoria (nombre)"
                + "VALUES (?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertarCategoria);
            stmt.setString(1, categoria.getNombre());
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
    
    public static void borrarCategoria(int id) {
        Connection conn = ConexionBD.conectar();
        String borrarCategoria = "DELETE FROM Categoria WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(borrarCategoria);
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
    
    public static void actualizarAutor(Categoria categoria) {
        Connection conn = ConexionBD.conectar();
        String actualizarCategoria = "UPDATE Categoria SET nombre = ?"
                + "WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(actualizarCategoria);
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getId());
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
    
    public static ArrayList<Categoria> listarCategorias() {
        Connection conn = ConexionBD.conectar();
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        String leerCategorias = "SELECT * FROM Categoria";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(leerCategorias);
            boolean hayRegistros = false;
            while (rs.next()) {
                listaCategorias.add(new Categoria(rs.getInt("id"),
                rs.getString("nombre")));
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
        return listaCategorias;
    }
    
    public static Categoria buscarCategoria(int id) {
        Connection conn = ConexionBD.conectar();
        String buscarCategoria = "SELECT * FROM Categoria WHERE id = ?";
        Categoria categoria = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(buscarCategoria);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("id"),
                rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al preparar la query");
        }
        return categoria;
    }
}