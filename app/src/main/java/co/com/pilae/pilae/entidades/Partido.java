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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = Tabla.PARTIDO,foreignKeys = {
        @ForeignKey(entity = Equipo.class,
                parentColumns = "idEquipo",
                childColumns = "equipoLocal"),
        @ForeignKey(entity = Equipo.class,
                parentColumns = "idEquipo",
                childColumns = "equipoVisitante"),
        @ForeignKey(entity = Torneo.class,
                parentColumns = "idTorneo",
                childColumns = "torneo")
})
public class Partido {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idPartido")
    private Integer idPartido;
    @ColumnInfo(name = "equipoLocal")
    private Integer equipoLocal;
    @ColumnInfo(name = "equipoVisitante")
    private Integer equipoVisitante;
    @ColumnInfo(name = "torneo")
    private Integer torneo;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "idaVuelta")
    private String idaVuelta;
}
