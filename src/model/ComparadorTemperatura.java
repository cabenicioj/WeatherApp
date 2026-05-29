package model;

import java.util.Comparator;

public class ComparadorTemperatura implements Comparator<Clima> {

    @Override
    public int compare(Clima c1, Clima c2) {
        // Compara las dos temperaturas (como son números con coma, usamos Double.compare)
        return Double.compare(c1.getTemperaturaActual(), c2.getTemperaturaActual());
    }

}
