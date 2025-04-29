package es.daw.carlos.biblioteca;

import es.daw.carlos.biblioteca.config.ConexionBD;
import es.daw.carlos.biblioteca.vista.BibliotecaApp;
import java.sql.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
// Librerías de los diferentes temas de Java Swing
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;

public class Biblioteca {
    
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        Connection conn = ConexionBD.conectar();
        if (conn == null) {
            System.out.println("Error al conectar la base de datos");
        } else {
            ConexionBD.crearTablas(conn);
        }
        // Elige el tema a utilizar
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        // UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        // UIManager.setLookAndFeel(new LunaLookAndFeel());
        new BibliotecaApp();
    }
}