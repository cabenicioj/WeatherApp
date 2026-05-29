package model;

import java.util.Comparator;

public class ComparadorTemperatura implements Comparator<Clima> {

    @Override
    public int compare(Clima c1, Clima c2) {
        // compara temperaturas
        return Double.compare(c1.getTemperaturaActual(), c2.getTemperaturaActual());
    }

}
