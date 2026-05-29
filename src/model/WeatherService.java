package model;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class WeatherService extends Observable {

    private final City ciudad;
    private final int segundos;
    private Clima climaActual;
    private Timer timer; // reloj interno para ejecutar tareas cada x tiempo
    private final Random random; // para generar climas aleatorios

    public WeatherService(City ciudad, int segundos) {
        this.ciudad = ciudad;
        this.segundos = segundos;
        this.random = new Random();
    }

    public void start() {
        if (timer != null) return; // si ya estaba corriendo, no hacemos nada

        timer = new Timer(true);

        // timer, ejecuta cada x segundos
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                generarClima();
            }
        }, 0, segundos * 1000L);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel(); // apagamos reloj
            timer = null;
        }
    }

    public Clima getClima() {
        return climaActual;
    }

    // inventa un clima
    private void generarClima() {
        // inventa temperaturas
        double tempActual = 5.0 + (30.0 * random.nextDouble()); // entre 5 y 35 grados
        double tempMin = tempActual - (random.nextDouble() * 5.0);
        double tempMax = tempActual + (random.nextDouble() * 5.0);

        // inventa restos de datos
        double presion = 980.0 + (50.0 * random.nextDouble());
        double humedad = 30.0 + (70.0 * random.nextDouble()); // entre 30% y 100%
        double viento = 0.0 + (60.0 * random.nextDouble()); // hasta 60 km/h
        double visibilidad = 2.0 + (13.0 * random.nextDouble());
        char escala = 'C';

        String[] condiciones = {"Despejado", "Nublado", "Lluvia", "Tormenta"};
        String condicionActual = condiciones[random.nextInt(condiciones.length)];

        // crear el nuevo clima
        this.climaActual = new Clima(this.ciudad, condicionActual, tempActual, tempMin, tempMax, presion, humedad,
                viento, visibilidad, escala);

        // aviso
        setChanged();
        notifyObservers();
    }
}
