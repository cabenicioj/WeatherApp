package model;

import java.util.Comparator;

public class ComparadorFecha implements Comparator<Clima> {

    @Override
    public int compare(Clima c1, Clima c2) {
        // string completo de fecha+hora
        String fechaHora1 = c1.getFechaHoraString();
        String fechaHora2 = c2.getFechaHoraString();

        // compara c2 contra c1 (al reves) para que sea orden DESCENDENTE
        // (el mas reciente arriba detodo)
        return fechaHora2.compareTo(fechaHora1);
    }

}
