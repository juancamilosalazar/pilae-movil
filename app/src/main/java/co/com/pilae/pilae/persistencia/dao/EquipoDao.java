package co.com.pilae.pilae.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Torneo;

@Dao
public interface EquipoDao {
    @Query("SELECT * FROM EQUIPO Where idEquipo=:id")
    Equipo findByIdEquipo(String id);

    @Query("SELECT * FROM EQUIPO Where torneo=:id")
    List<Equipo> findByIdTorneo(String id);
    @Query("SELECT * FROM equipo Where nombre=:nombre")
    Equipo findByNombreEquipo(String nombre);
    @Insert
    void insert(Equipo equipo);
    @Update
    void update(Equipo equipo);

    @Query("SELECT * FROM equipo")
    List<Equipo> listar();
}
