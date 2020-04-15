package co.com.pilae.pilae.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
    private Integer idEquipo;
    private String nombre;
    private String deporte;
}
