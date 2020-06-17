package co.com.pilae.pilae.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Partido;

@Dao
public interface PartidoDao {
    @Query("SELECT * FROM PARTIDO Where idPartido=:id")
    Partido findByIdPartido(String id);

    @Insert
    void insert(Partido partido);
    @Update
    void update(Partido partido);
    @Query("SELECT * FROM PARTIDO Where torneo=:id")
    List<Partido> findByIdTorneo(String id);
    @Query("SELECT * FROM partido")
    List<Partido> listar();
}
