package es.daw.carlos.biblioteca.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Categoria {
    private int id;
    private String nombre;
    
    // Constructor manual SIN id
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
