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
        actualizarEstadoClienteLabel();
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
                if(clienteSeleccionado!=null){
                    clienteSeleccionado=clientesDAO.seleccionarCliente(c.getId());
                 AccionesListaView accionesListaView = new AccionesListaView(clienteSeleccionado);
                }else {
                    JOptionPane.showMessageDialog(AdminVista.this, "Debes seleccinar un cliente");
                }


            }
        });

        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //AdminVista.this.dispose();
                System.out.println("En consutruccion xd");
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

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
