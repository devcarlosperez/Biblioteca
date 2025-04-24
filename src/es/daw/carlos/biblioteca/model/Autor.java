package es.daw.carlos.biblioteca.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Autor {
    private int id;
    private String nombre;
    private String nacionalidad;
}