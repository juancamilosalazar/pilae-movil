package co.com.pilae.pilae.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.pilae.pilae.persistencia.Tabla;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity(tableName = Tabla.TORNEO)
@NoArgsConstructor
public class Torneo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idTorneo")
    private Integer idTorneo;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "deporte")
    private String deporte;
}
