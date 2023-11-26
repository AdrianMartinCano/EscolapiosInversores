package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminVista extends JFrame {

    public AdminVista() {
        setTitle("Vista Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear paneles
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10)); // 5 filas, 1 columna, con espacio entre componentes

        // Crear botones
        JButton comprarButton = new JButton("Comprar acciones");
        JButton venderButton = new JButton("Vender acciones");
        JButton seleccionarUsuarioButton = new JButton("Seleccionar Usuario");
        JButton verMovimientosButton = new JButton("Ver movimientos");
        JButton atrasButton = new JButton("Atrás");

        // Establecer el tamaño de los botones como cuadrados
        Dimension buttonSize = new Dimension(150, 50);
        comprarButton.setPreferredSize(buttonSize);
        venderButton.setPreferredSize(buttonSize);
        seleccionarUsuarioButton.setPreferredSize(buttonSize);
        verMovimientosButton.setPreferredSize(buttonSize);
        atrasButton.setPreferredSize(buttonSize);

        // Agregar acciones a los botones si es necesario
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones al hacer clic en "Comprar acciones"
            }
        });

        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones al hacer clic en "Vender acciones"
            }
        });

        seleccionarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones al hacer clic en "Seleccionar Usuario"
            }
        });

        verMovimientosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones al hacer clic en "Ver movimientos"
            }
        });

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                AdminVista.this.dispose();
            }
        });

        // Agregar botones al panel
        panel.add(seleccionarUsuarioButton);
        panel.add(comprarButton);
        panel.add(venderButton);
        panel.add(verMovimientosButton);
        panel.add(atrasButton);

        // Agregar el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }


}
