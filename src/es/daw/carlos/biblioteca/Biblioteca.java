package es.daw.carlos.biblioteca;

import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.vista.BibliotecaApp;

import java.sql.*;

public class Biblioteca {
    
    public static void main(String[] args) {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error al conectar la base de datos");
        } else {
            ConexionBD.crearTablas(conn);
        }
        
        new BibliotecaApp();
    }
}