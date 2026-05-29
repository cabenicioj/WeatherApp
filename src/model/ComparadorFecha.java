package model;

import java.util.Comparator;

public class ComparadorFecha implements Comparator<Clima> {

    @Override
    public int compare(Clima c1, Clima c2) {
        // Obtenemos el String completo de Fecha + Hora que preparamos en la clase Clima
        String fechaHora1 = c1.getFechaHoraString();
        String fechaHora2 = c2.getFechaHoraString();

        // Comparamos c2 contra c1 (al revés) para que sea orden DESCENDENTE
        // (El más reciente arriba detodo)
        return fechaHora2.compareTo(fechaHora1);
    }

}
