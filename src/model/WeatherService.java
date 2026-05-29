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
    private Timer timer; // Reloj interno para ejecutar tareas cada X tiempo
    private final Random random; // Para generar climas aleatorios

    public WeatherService(City ciudad, int segundos) {
        this.ciudad = ciudad;
        this.segundos = segundos;
        this.random = new Random();
    }

    public void start() {
        if (timer != null) return; // Si ya estaba corriendo, no hacemos nada

        timer = new Timer(true);

        // Le decimos al timer: "Ejecutá este código cada X segundos"
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                generarClima();
            }
        }, 0, segundos * 1000L); // El tiempo en Java se mide en milisegundos (5 seg = 5000 ms)
    }

    public void stop() {
        if (timer != null) {
            timer.cancel(); // Apagamos el reloj
            timer = null;
        }
    }

    public Clima getClima() {
        return climaActual;
    }

    // Metodo privado que inventa un clima realista
    private void generarClima() {
        // 1. Inventamos temperaturas (la mínima es un poco menos que la actual, la máxima un poco más)
        double tempActual = 5.0 + (30.0 * random.nextDouble()); // Entre 5 y 35 grados
        double tempMin = tempActual - (random.nextDouble() * 5.0);
        double tempMax = tempActual + (random.nextDouble() * 5.0);

        // 2. Inventamos el resto de los datos con rangos lógicos
        double presion = 980.0 + (50.0 * random.nextDouble()); // Presión atmosférica normal
        double humedad = 30.0 + (70.0 * random.nextDouble()); // Entre 30% y 100%
        double viento = 0.0 + (60.0 * random.nextDouble()); // Hasta 60 km/h
        double visibilidad = 2.0 + (13.0 * random.nextDouble()); // Visibilidad en km
        char escala = 'C'; // Usamos C de Celsius por defecto

        String[] condiciones = {"Despejado", "Nublado", "Lluvia", "Tormenta"};
        String condicionActual = condiciones[random.nextInt(condiciones.length)];

        // Creamos el nuevo clima
        this.climaActual = new Clima(this.ciudad, condicionActual, tempActual, tempMin, tempMax, presion, humedad,
                viento, visibilidad, escala);

        // ¡EL MOMENTO CLAVE! Le avisamos a los que nos están observando
        setChanged();
        notifyObservers();
    }
}
