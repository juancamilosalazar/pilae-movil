package co.com.pilae.pilae.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.TablaPosicion;
import co.com.pilae.pilae.entidades.Torneo;

@Dao
public interface TorneoDao {

    @Query("SELECT * FROM TORNEO Where idTorneo=:id")
    Torneo findByIdTorneo(String id);

    @Query("SELECT * FROM TORNEO Where nombre=:nombre")
    Torneo findByNombreTorneo(String nombre);
    @Insert
    void insert(Torneo torneo);
    @Update
    void update(Torneo torneo);
    @Query("SELECT * FROM torneo")
    List<Torneo> listar();
}
