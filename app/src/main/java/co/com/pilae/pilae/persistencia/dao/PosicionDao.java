package co.com.pilae.pilae.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Partido;
import co.com.pilae.pilae.entidades.TablaPosicion;

@Dao
public interface PosicionDao {
    @Query("SELECT * FROM POSICION Where idPosicion=:id")
    TablaPosicion findByIdPosicion(String id);

    @Insert
    void insert(TablaPosicion posicion);
    @Update
    void update(TablaPosicion posicion);
}
