package es.daw.carlos.biblioteca.model;
import lombok.Data;

@Data
public class Libro {
    private String isbn;
    private String titulo;
    private int anioPublicacion;
    private int autorId;
    private int categoriaId; 
}
