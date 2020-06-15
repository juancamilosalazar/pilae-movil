package co.com.pilae.pilae.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import co.com.pilae.pilae.persistencia.Tabla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static androidx.room.ForeignKey.CASCADE;

@Data
@NoArgsConstructor
@Entity(tableName = Tabla.TABLA_POSICION,foreignKeys = {
        @ForeignKey(entity = Equipo.class,
                parentColumns = "idEquipo",
                childColumns = "equipo",
                onDelete = CASCADE),
        @ForeignKey(entity = Torneo.class,
                parentColumns = "idTorneo",
                childColumns = "torneo",
                onDelete = CASCADE)})
@AllArgsConstructor
public class TablaPosicion {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idPosicion")
    private Integer idPosicion;
    @ColumnInfo(name = "partidosJugados")
    private int partidosJugados;
    @ColumnInfo(name = "partidosGanados")
    private int partidosGanados;
    @ColumnInfo(name = "partidosPerdidos")
    private int partidosPerdidos;
    @ColumnInfo(name = "partidosEmpatados")
    private int partidosEmpatados;
    @ColumnInfo(name = "golesFavor")
    private int golesFavor;
    @ColumnInfo(name = "golesContra")
    private int golesContra;
    @ColumnInfo(name = "golesDiferencia")
    private int golesDiferencia;
    @ColumnInfo(name = "puntos")
    private int puntos;
    @ColumnInfo(name = "equipo")
    private Integer equipo;
    @ColumnInfo(name = "torneo")
    private Integer torneo;
}
