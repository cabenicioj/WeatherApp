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
        // inicia el servicio
        this.service = new WeatherService(City.Junin, 5);
        this.service.addObserver(this);
        this.service.start();
    }

    // metodo q escucha al servicio
    @Override
    public void update(Observable weather, Object param) {
        Clima clima = ((WeatherService)weather).getClima();

        this.climaActual = clima;
        this.historial.add(clima);

        // esto avisa q hay nuevos datos
        setChanged();
        notifyObservers();
    }

    // metodos para los botones de iu
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

    public List<Clima> getHistorial() {
        return historial;
    }

    public Clima getClimaActual() {
        return climaActual;
    }

    // metodo extra por si se cierra la ventana y quiero apagar el servicio
    public void apagarServicio() {
        service.stop();
        service.deleteObserver(this);
    }
}
