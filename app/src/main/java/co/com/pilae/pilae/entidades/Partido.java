package co.com.pilae.pilae.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partido {
    private String equipoLocal;
    private String equipoVisitante;
    private String fecha;
    private String torneo;
    private String idaVuelta;
}
