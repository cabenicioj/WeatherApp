import iu.VentanaPrincipal;
import model.EstacionMeteorologica;

public class Main {
    public static void main(String[] args) {

        // 1. Creamos el Cerebro/Modelo (Esto por dentro enciende el WeatherService y el reloj)
        EstacionMeteorologica modelo = new EstacionMeteorologica();

        // 2. Creamos la Vista (La ventana) y le pasamos el cerebro para que lo escuche
        VentanaPrincipal ventana = new VentanaPrincipal(modelo);

        // 3. ¡Prendemos la pantalla!
        ventana.setVisible(true);

    }
}
