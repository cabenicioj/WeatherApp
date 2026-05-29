package iu;

import model.Clima; // Importamos nuestra clase Clima

import javax.swing.*;
import java.awt.*;

public class VentanaDetalle extends JFrame {

    // Ahora recibimos directamente un objeto Clima
    public VentanaDetalle(Clima clima) {
        setTitle("Detalle Meteorológico");
        setSize(300, 400); // La hicimos más alta para que entren los nuevos datos
        setLocationRelativeTo(null);

        // Usamos un GridLayout de 10 filas y 1 columna
        setLayout(new GridLayout(10, 1, 5, 5));

        // Creamos las etiquetas usando TODOS los getters que IntelliJ decía que no usábamos
        JLabel lblCiudad = new JLabel("  Ciudad: " + clima.getCiudad());
        lblCiudad.setFont(new Font("Arial", Font.BOLD, 16)); // Resaltamos la ciudad

        JLabel lblFecha = new JLabel("  Fecha y Hora: " + clima.getFechaHoraString());
        JLabel lblCondicion = new JLabel("  Condición: " + clima.getCondicion());
        JLabel lblTemp = new JLabel("  Temp. Actual: " + String.format("%.1f", clima.getTemperaturaActual()) + " °" + clima.getEscala());
        JLabel lblTempMin = new JLabel("  Mínima: " + String.format("%.1f", clima.getTemperaturaMinDia()) + " °" + clima.getEscala());
        JLabel lblTempMax = new JLabel("  Máxima: " + String.format("%.1f", clima.getTemperaturaMaxDia()) + " °" + clima.getEscala());
        JLabel lblHumedad = new JLabel("  Humedad: " + String.format("%.1f", clima.getHumedad()) + " %");
        JLabel lblPresion = new JLabel("  Presión: " + String.format("%.1f", clima.getPresion()) + " hPa");
        JLabel lblViento = new JLabel("  Viento: " + String.format("%.1f", clima.getViento()) + " km/h");
        JLabel lblVisibilidad = new JLabel("  Visibilidad: " + String.format("%.1f", clima.getVisibilidad()) + " km");

        // Agregamostodo a la ventanita en orden
        add(lblCiudad);
        add(lblFecha);
        add(lblCondicion);
        add(lblTemp);
        add(lblTempMin);
        add(lblTempMax);
        add(lblHumedad);
        add(lblPresion);
        add(lblViento);
        add(lblVisibilidad);
    }
}
