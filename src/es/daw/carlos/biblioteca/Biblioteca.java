package es.daw.carlos.biblioteca;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.vista.BibliotecaApp;
import java.sql.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Biblioteca {
    
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error al conectar la base de datos");
        } else {
            ConexionBD.crearTablas(conn);
        }
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        new BibliotecaApp();
    }
}