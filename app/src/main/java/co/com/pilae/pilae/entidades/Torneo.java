package co.com.pilae.pilae.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Torneo {
    private Integer idTorneo;
    private String nombre;
    private String deporte;
}
