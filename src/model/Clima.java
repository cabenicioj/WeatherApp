package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clima {
    private final City ciudad;
    private final String condicion;
    private final double temperaturaActual;
    private final double temperaturaMinDia;
    private final double temperaturaMaxDia;
    private final double presion;
    private final double humedad;
    private final double viento;
    private final double visibilidad;
    private final char escala;
    private final LocalDate fecha;
    private final LocalTime horaUltimaActualizacion;

    public Clima(City ciudad, String condicion, double temperaturaActual, double temperaturaMinDia,
                 double temperaturaMaxDia, double presion, double humedad, double viento,
                 double visibilidad, char escala) {

        this.ciudad = ciudad;
        this.condicion = condicion;
        this.temperaturaActual = temperaturaActual;
        this.temperaturaMinDia = temperaturaMinDia;
        this.temperaturaMaxDia = temperaturaMaxDia;
        this.presion = presion;
        this.humedad = humedad;
        this.viento = viento;
        this.visibilidad = visibilidad;
        this.escala = escala;
        this.fecha = LocalDate.now();
        this.horaUltimaActualizacion = LocalTime.now();
    }

    public City getCiudad() { return ciudad; }
    public String getCondicion() { return condicion; }
    public double getTemperaturaActual() { return temperaturaActual; }
    public double getTemperaturaMinDia() { return temperaturaMinDia; }
    public double getTemperaturaMaxDia() { return temperaturaMaxDia; }
    public double getPresion() { return presion; }
    public double getHumedad() { return humedad; }
    public double getViento() { return viento; }
    public double getVisibilidad() { return visibilidad; }
    public char getEscala() { return escala; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraUltimaActualizacion() { return horaUltimaActualizacion; }

    // une fecha y hora en un string
    public String getFechaHoraString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return fecha.toString() + " " + horaUltimaActualizacion.format(timeFormatter);
    }

    // se muestra en ventana principal
    @Override
    public String toString() {
        return getFechaHoraString() + " | " + ciudad + " | Temp: " + String.format("%.1f", temperaturaActual) + " °" + escala;
    }
}