package es.daw.carlos.biblioteca.config;

import java.sql.*;

public class ConexionBD {

    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
            System.out.println("Base de datos creada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos");
        }
        return conn;
    }

    public static void crearTablas(Connection conn) {
        String sqlAutor = "CREATE TABLE IF NOT EXISTS AUTOR ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT(100) NOT NULL,"
                + "nacionalidad TEXT(50))";
        String sqlCategoria = "CREATE TABLE IF NOT EXISTS CATEGORIA ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT(100) NOT NULL)";
        String sqlLibro = "CREATE TABLE IF NOT EXISTS LIBRO ("
                + "isbn TEXT(20) PRIMARY KEY,"
                + "titulo TEXT(200) NOT NULL,"
                + "anio_publicacion INTEGER,"
                + "autor_id INTEGER NOT NULL,"
                + "categoria_id INTEGER NOT NULL,"
                + "FOREIGN KEY (autor_id) REFERENCES Autor(id),"
                + "FOREIGN KEY (categoria_id) REFERENCES Categoria(id))";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlAutor);
            stmt.execute(sqlCategoria);
            stmt.execute(sqlLibro);
            System.out.println("Tablas creadas con éxito");
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas de la base de datos");
        }
    }
}