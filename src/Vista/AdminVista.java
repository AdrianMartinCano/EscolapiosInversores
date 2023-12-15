package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.ClientesDAO;
import Modelo.DAO.EmpresaDAO;
import Modelo.DAO.TransaccionesDAO;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Empresas;
import Modelo.Entidades.Transacciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminVista extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Conexion conexion;
    private Clientes clienteSeleccionado;
    private JLabel estadoClienteLabel;
    private String nombreEmpresa;

    private ClientesDAO clientesDAO;
    private TransaccionesDAO transaccionesDAO;
    private EmpresaDAO empresaDAO;

    private ArrayList<Clientes> listaClientes;
    private ArrayList<Transacciones> listaTransacciones;
    private ArrayList<Empresas> listaEmpresas;

    public AdminVista() {
        setTitle("Vista Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);  // Amplié el tamaño de la ventana
        setLocationRelativeTo(null);

        conexion = new Conexion(DB_URL, USER, PASS);

        clientesDAO = new ClientesDAO(conexion);
        listaClientes = clientesDAO.listaClientes();

        empresaDAO = new EmpresaDAO(conexion);
        listaEmpresas = empresaDAO.listaEmpresas();

        transaccionesDAO = new TransaccionesDAO(conexion);
        listaTransacciones = transaccionesDAO.listaTransacciones();

        // Crear paneles
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Etiqueta para mostrar el estado del cliente seleccionado
        estadoClienteLabel = new JLabel("Cliente no seleccionado");
        actualizarEstadoClienteLabel();

        // Panel para mostrar el estado del cliente seleccionado
        JPanel estadoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        estadoPanel.add(estadoClienteLabel);

        // Agregar el panel de estado al panel principal
        panel.add(estadoPanel);

        // Panel para los botones
        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Crear botones
        JButton comprarButton = new JButton("Comprar acciones");
        JButton venderButton = new JButton("Vender acciones");
        JButton verMovimientosButton = new JButton("Ver movimientos");

        // Establecer el tamaño de los botones como cuadrados
        Dimension buttonSize = new Dimension(150, 50);
        comprarButton.setPreferredSize(buttonSize);
        venderButton.setPreferredSize(buttonSize);
        verMovimientosButton.setPreferredSize(buttonSize);

        // Agregar acciones a los botones
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes c = clienteSeleccionado;
                if (clienteSeleccionado != null) {
                    clienteSeleccionado = clientesDAO.seleccionarCliente(c.getId());
                    CompraAccionesView compraAccionesView = new CompraAccionesView(listaEmpresas, clienteSeleccionado);
                    AdminVista.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminVista.this, "Debes seleccinar un cliente");
                }
            }
        });

        verMovimientosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes c = clienteSeleccionado;
                if (clienteSeleccionado != null) {
                    clienteSeleccionado = clientesDAO.seleccionarCliente(c.getId());
                    AccionesListaView accionesListaView = new AccionesListaView(clienteSeleccionado);

                } else {
                    JOptionPane.showMessageDialog(AdminVista.this, "Debes seleccinar un cliente");
                }
            }
        });

        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes c = clienteSeleccionado;
                if (clienteSeleccionado != null) {
                    clienteSeleccionado = clientesDAO.seleccionarCliente(c.getId());
                    VentaAccionesView ventaAccionesView = new VentaAccionesView(clienteSeleccionado);
                    AdminVista.this.dispose();

                } else {
                    JOptionPane.showMessageDialog(AdminVista.this, "Debes seleccinar un cliente");
                }
            }
        });

        // Agregar botones al panel de botones
        botonesPanel.add(comprarButton);
        botonesPanel.add(venderButton);
        botonesPanel.add(verMovimientosButton);

        // Agregar el panel de botones al panel principal
        panel.add(botonesPanel);

        // Botón para seleccionar usuario
        JButton seleccionarUsuarioButton = new JButton("Seleccionar Usuario");
        seleccionarUsuarioButton.setPreferredSize(buttonSize);
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

        JPanel seleccionarUsuarioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        seleccionarUsuarioPanel.add(seleccionarUsuarioButton);

        // Agregar el panel de selección de usuario al panel principal
        panel.add(seleccionarUsuarioPanel);

        // Botón "Atrás"
        JButton atrasButton = new JButton("Atrás");
        atrasButton.setPreferredSize(buttonSize);
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acciones al hacer clic en "Atrás"
                AdminVista.this.dispose();
                Login l = new Login();
            }
        });

        JPanel atrasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        atrasPanel.add(atrasButton);

        // Agregar el panel de "Atrás" al panel principal
        panel.add(atrasPanel);

        // Agregar el panel principal a la ventana
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

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
