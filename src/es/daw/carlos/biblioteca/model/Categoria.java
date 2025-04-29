package es.daw.carlos.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Usamos la librería Lombok
@AllArgsConstructor
public class Categoria {
    private int id;
    private String nombre;
    
    // Constructor personalizado sin id
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
