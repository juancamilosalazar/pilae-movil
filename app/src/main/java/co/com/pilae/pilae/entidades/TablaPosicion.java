package co.com.pilae.pilae.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TablaPosicion {
    private Long codigo;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int partidosEmpatados;
    private int golesFavor;
    private int golesContra;
    private int golesDiferencia;
    private int puntos;
    private String equipo;
    //private Equipo fkEquipo;
    //private Torneo fkTorneo;
}
