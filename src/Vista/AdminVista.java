package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.ClientesDAO;
import Modelo.Entidades.Clientes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminVista extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";

    private ClientesDAO clientesDAO;
    private ArrayList<Clientes> listaClientes;

    // Variable para realizar un seguimiento del cliente seleccionado
    private Clientes clienteSeleccionado;

    // Etiqueta para mostrar el estado del cliente seleccionado
    private JLabel estadoClienteLabel;

    private Conexion conexion;



    public AdminVista() {
        setTitle("Vista Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);  // Amplié el tamaño de la ventana
        setLocationRelativeTo(null);

        conexion = new Conexion(DB_URL, USER, PASS);
        clientesDAO = new ClientesDAO(conexion);
        listaClientes = clientesDAO.listaClientes();

        // Crear paneles
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

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

        // Etiqueta para mostrar el estado del cliente seleccionado
        estadoClienteLabel = new JLabel("Cliente no seleccionado");
        estadoClienteLabel.setForeground(Color.RED);

        // Agregar acciones a los botones
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
                SeleccionClienteVista scv = new SeleccionClienteVista(listaClientes, AdminVista.this);
                Clientes clienteSeleccionado = scv.getCliente();

                if (clienteSeleccionado != null) {
                    clienteSeleccionado = scv.getCliente();
                    estadoClienteLabel.setText("Cliente seleccionado: " + clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellido());
                    estadoClienteLabel.setForeground(Color.GREEN);
                } else {
                    clienteSeleccionado = null;
                    estadoClienteLabel.setText("Cliente no seleccionado");
                    estadoClienteLabel.setForeground(Color.RED);
                }


            }
        });

        verMovimientosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clienteSeleccionado==null){
                    JOptionPane.showMessageDialog(AdminVista.this, "No hay ningún cliente seleccionado");
                }
                else{
                    JOptionPane.showMessageDialog(AdminVista.this, "Nombre " + clienteSeleccionado.getNombre() + "Apellido " + clienteSeleccionado.getApellido());
                }
            }
        });

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones al hacer clic en "Atrás"
                Login l = new Login();
                AdminVista.this.dispose();
            }
        });

        // Agregar componentes al panel
        panel.add(estadoClienteLabel);
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

    public void actualizarEstadoClienteLabel() {
        if (clienteSeleccionado != null) {
            estadoClienteLabel.setText("Cliente seleccionado: " + clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellido());
            estadoClienteLabel.setForeground(Color.GREEN);
        } else {
            estadoClienteLabel.setText("Cliente no seleccionado");
            estadoClienteLabel.setForeground(Color.RED);
        }
    }
    public void setClienteSeleccionado(Clientes clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
}
