import iu.VentanaPrincipal;
import model.EstacionMeteorologica;

public class Main {
    public static void main(String[] args) {

        // creamos el cerebro/modelo (enciende weatherservice y el reloj)
        EstacionMeteorologica modelo = new EstacionMeteorologica();

        // creamos la vista/ventana y le pasamos el modelo
        VentanaPrincipal ventana = new VentanaPrincipal(modelo);

        // prende la pantalla
        ventana.setVisible(true);

    }
}
