package iu;

import model.Clima;
import model.EstacionMeteorologica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class VentanaPrincipal extends JFrame implements Observer {

    private final EstacionMeteorologica model;

    private JLabel lblTemperaturaActual;
    private JList<Clima> listaHistorial;
    private DefaultListModel<Clima> modeloLista;
    private JButton btnTemp, btnDate, btnClean;

    public VentanaPrincipal(EstacionMeteorologica model) {
        this.model = model;
        this.model.addObserver(this); // "escuchamos" cambios

        configurarVentana();
        inicializarComponentes();
        armarLayout();
        configurarEventos();
    }

    private void configurarVentana() {
        setTitle("Estación Meteorológica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        lblTemperaturaActual = new JLabel("Temperatura Actual: -- °C", SwingConstants.CENTER);
        lblTemperaturaActual.setFont(new Font("Arial", Font.BOLD, 24));

        modeloLista = new DefaultListModel<>();
        listaHistorial = new JList<>(modeloLista);

        btnTemp = new JButton("Temperature");
        btnDate = new JButton("Date");
        btnClean = new JButton("Clean");
    }

    private void armarLayout() {
        JPanel panelNorte = new JPanel();
        panelNorte.add(lblTemperaturaActual);
        add(panelNorte, BorderLayout.NORTH);

        JScrollPane scrollCentro = new JScrollPane(listaHistorial);
        add(scrollCentro, BorderLayout.CENTER);

        JPanel panelOeste = new JPanel();
        panelOeste.setLayout(new GridLayout(3, 1, 5, 5));
        panelOeste.add(btnTemp);
        panelOeste.add(btnDate);
        panelOeste.add(btnClean);
        add(panelOeste, BorderLayout.WEST);
    }

    private void configurarEventos() {
        //limpia
        btnClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.limpiarHistorial(); // controlador avisa al modelo
            }
        });

        //ordenar por temperatura
        btnTemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.ordenarPorTemperatura();
            }
        });

        //ordenar por fecha
        btnDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.ordenarPorFecha();
            }
        });

        // para el doble clic en la lista
        listaHistorial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // obtenie elemento seleccionado
                    int index = listaHistorial.locationToIndex(e.getPoint());
                    if (index >= 0) {

                        Clima climaSeleccionado = modeloLista.getElementAt(index);

                        VentanaDetalle detalle = new VentanaDetalle(climaSeleccionado);
                        detalle.setVisible(true);
                    }
                }
            }
        });
    }

    //ESTE METODO SE EJECUTA SOLO CUANDO EL MODELO AVISA QUE HAY CAMBIOS
    @Override
    public void update(Observable o, Object arg) {
        // 1. Actualizamos el texto de arriba con la temperatura del clima actual
        if (model.getClimaActual() != null) {
            lblTemperaturaActual.setText("Temperatura Actual: " + model.getClimaActual().getTemperaturaActual() + " °C");
        }

        // 2. Limpiamos la lista visual para que no se dupliquen los datos
        modeloLista.clear();

        // 3. Acá va tu for! Recorremos el historial y agregamos cada clima a la lista visual
        for (Clima c : model.getHistorial()) {
            modeloLista.addElement(c);
        }
    }
}
