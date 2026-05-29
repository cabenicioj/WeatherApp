package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class EstacionMeteorologica extends Observable implements Observer {
    private Clima climaActual;
    private final List<Clima> historial;
    private final WeatherService service;

    public EstacionMeteorologica() {
        this.historial = new ArrayList<>();
        // Iniciamos el servicio como dice la consigna
        this.service = new WeatherService(City.Junin, 5);
        this.service.addObserver(this);
        this.service.start();
    }

    // Este es el metodo que escucha al servicio cada 5 segundos
    @Override
    public void update(Observable weather, Object param) {
        Clima clima = ((WeatherService)weather).getClima();

        this.climaActual = clima;
        this.historial.add(clima);

        // Avisamos a la interfaz que llegaron nuevos datos
        setChanged();
        notifyObservers();
    }

    // Métodos para los botones de la IU
    public void limpiarHistorial() {
        historial.clear();
        setChanged();
        notifyObservers();
    }

    public void ordenarPorTemperatura() {
        historial.sort(new ComparadorTemperatura());
        setChanged();
        notifyObservers();
    }

    public void ordenarPorFecha() {
        historial.sort(new ComparadorFecha());
        setChanged();
        notifyObservers();
    }

    // Getters para que la ventana pueda leer la info y mostrarla
    public List<Clima> getHistorial() {
        return historial;
    }

    public Clima getClimaActual() {
        return climaActual;
    }

    // Metodo extra útil por si cerrás la ventana y querés apagar el servicio
    public void apagarServicio() {
        service.stop();
        service.deleteObserver(this);
    }
}
