package co.com.pilae.pilae.persistencia.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Partido;
import co.com.pilae.pilae.entidades.TablaPosicion;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.dao.EquipoDao;
import co.com.pilae.pilae.persistencia.dao.PartidoDao;
import co.com.pilae.pilae.persistencia.dao.PosicionDao;
import co.com.pilae.pilae.persistencia.dao.TorneoDao;

@Database(entities = {
        Equipo.class,
        Partido.class,
        TablaPosicion.class,
        Torneo.class}, version = DataBaseHelper.VERSION_BASE_DATOS, exportSchema = false)

public abstract class DataBaseHelper extends RoomDatabase {

    public static final int VERSION_BASE_DATOS = 1;
    public static final String NOMBRE_BASE_DATOS = "pilae";
    private static DataBaseHelper instace;


    public static DataBaseHelper getSimpleDB(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).build();
        }
        return instace;
    }

    public static DataBaseHelper getDBMainThread(Context context){
        if (instace == null) {
            instace = Room.databaseBuilder(context, DataBaseHelper.class, NOMBRE_BASE_DATOS).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instace;
    }


    /**
     * Listado de DAO
     */

    public abstract EquipoDao getEquipoDAO();

    public abstract PartidoDao getPartidoDAO();

    public abstract PosicionDao getTablaPosicionDAO();

    public abstract TorneoDao getTorneoDAO();


}
