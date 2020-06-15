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
@Entity(tableName = Tabla.EQUIPO,
        foreignKeys = @ForeignKey(entity = Torneo.class,
        parentColumns = "idTorneo",
        childColumns = "torneo"))
@NoArgsConstructor
public class Equipo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idEquipo")
    private Integer idEquipo;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "deporte")
    private String deporte;
    @ColumnInfo(name = "torneo")
    private Integer torneo;
}
