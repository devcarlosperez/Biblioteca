package es.daw.carlos.biblioteca.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Autor {
    private int id;
    private String nombre;
    private String nacionalidad;
    
    // Constructor manual SIN id
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
}