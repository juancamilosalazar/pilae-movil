package co.com.pilae.pilae.operaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;

public class GenerarFixture {


    private static Partido[][] calcularLigaNumEquiposPar(HashMap equipos) {
        int numRondas = equipos.size() - 1;
        int numPartidosPorRonda = equipos.size() / 2;

        Partido[][] rondas = new Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i++) {
            for (int j = 0; j < numPartidosPorRonda; j++) {
                rondas[i][j] = new Partido();
                rondas[i][j].local = k;
                k++;

                if (k == numRondas)
                    k = 0;
            }
        }

        for (int i = 0; i < numRondas; i++) {
            if (i % 2 == 0) {
                rondas[i][0].visitante = equipos.size() - 1;
            } else {
                rondas[i][0].visitante = rondas[i][0].local;
                rondas[i][0].local = equipos.size() - 1;
            }
        }

        int equipoMasAlto = equipos.size() - 1;
        int equipoImparMasAlto = equipoMasAlto - 1;

        for (int i = 0, k = equipoImparMasAlto; i < numRondas; i++) {
            for (int j = 1; j < numPartidosPorRonda; j++) {
                rondas[i][j].visitante = k;

                k--;

                if (k == -1)
                    k = equipoImparMasAlto;
            }
        }

        return rondas;
    }

    private static Partido[][] calcularLigaNumEquiposImpar(HashMap equipos) {
        int numRondas = equipos.size();
        int numPartidosPorRonda = equipos.size() / 2;

        Partido[][] rondas = new Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i++) {
            for (int j = -1; j < numPartidosPorRonda; j++) {
                if (j >= 0) {
                    rondas[i][j] = new Partido();
                    rondas[i][j].local = k;
                }

                k++;

                if (k == numRondas)
                    k = 0;
            }
        }

        int equipoMasAlto = equipos.size() - 1;

        for (int i = 0, k = equipoMasAlto; i < numRondas; i++) {
            for (int j = 0; j < numPartidosPorRonda; j++) {
                rondas[i][j].visitante = k;

                k--;

                if (k == -1)
                    k = equipoMasAlto;
            }
        }

        return rondas;
    }

    public static Partido[][] calcularLiga(HashMap<Integer, Equipo> equipos) {

        if (equipos.size() % 2 == 0)
            return calcularLigaNumEquiposPar(equipos);
        else
            return calcularLigaNumEquiposImpar(equipos);
    }



    static public class Partido {
        public int local = -1, visitante = -1;
    }

}
