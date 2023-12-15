package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.AccionesDAO;
import Modelo.DAO.ClientesDAO;
import Modelo.DAO.TransaccionesDAO;
import Modelo.Entidades.Acciones;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Empresas;
import Modelo.Tipos.TipoOperacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class VentaAccionesView extends JFrame {

    //select * from acciones where idCliente= IDCliente
    //AccionesDao

    private Acciones acciones;

    private AccionesDAO accionesDAO;

    private Clientes clientes;

    private ArrayList<Acciones> listaAccionesUsuario;

    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";


    private DefaultListModel<Acciones> listModel;

    private JList<Acciones> accionesJlist;

    private JTextField accionesTextField;

    private JButton seleccionarButton;

    private JButton botonAtras;

    private int cantidad;

    private ClientesDAO clientesDAO;
    private TransaccionesDAO transaccionesDAO;

    private Conexion conexion;
    public VentaAccionesView(Clientes clientes){
        this.clientes=clientes;
        listaAccionesUsuario= new ArrayList<Acciones>();
        conexion= new Conexion(DB_URL, USER, PASS);
        accionesDAO = new AccionesDAO(conexion);
        clientesDAO= new ClientesDAO(conexion);
        transaccionesDAO= new TransaccionesDAO(conexion);
        llenarLista(clientes);
        setTitle("Venta de acciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        for (Acciones a : listaAccionesUsuario){
            listModel.addElement(a);
        }
        accionesJlist = new JList<>(listModel);
        accionesJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        accionesJlist.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Acciones) {
                    Acciones acc = (Acciones) value;
                    setText(acc.toDatos());
                }
                return this;
            }
        });
        accionesTextField = new JTextField();
        accionesTextField.setPreferredSize(new Dimension(50, accionesTextField.getPreferredSize().height)); // Ajustar el ancho
        seleccionarButton = new JButton("Vender");
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cantidad=Integer.parseInt(accionesTextField.getText().toString());
                Acciones a = getEmpresaSeleccionada();
                JOptionPane.showMessageDialog(VentaAccionesView.this, "Se han vendido " + cantidad + " acciones de la empresa " + a.getNombreEmpresa());
                clientesDAO.restarAcciones(clientes.getId(), cantidad);
                clientes.setNumeroAcciones(clientes.getNumeroAcciones()-getNumeroAcciones());
                transaccionesDAO.actualizarTransaccionVenta(clientes, a.getNombreEmpresa());
                Clientes aux = new Clientes(clientes.getId(),
                        clientes.getNombre(),
                        clientes.getApellido(),
                        clientes.getDNI(),
                        clientes.getNumeroCuenta(),
                        getNumeroAcciones(),
                        clientes.getUsuario(),
                        clientes.getContraseña(),
                        clientes.isEsAdmin());
                accionesDAO.agregarAcciones(aux, a.getNombreEmpresa(), TipoOperacion.Venta, getNumeroAcciones());
            }
        });
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JScrollPane(accionesJlist));
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Número de acciones a comprar:"));
        inputPanel.add(accionesTextField);
        inputPanel.add(seleccionarButton);
        botonAtras = new JButton("Atrás");
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminVista adminVista = new AdminVista();
                adminVista.setClienteSeleccionado(clientes);
                adminVista.actualizarEstadoClienteLabel();
                //adminVista.setNombreEmpresa(nombreEmpresa);
                VentaAccionesView.this.dispose(); // Método para cerrar la ventana
            }
        });

        inputPanel.add(botonAtras);
        panel.add(inputPanel);

        // Agregar el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);

    }

    private void llenarLista(Clientes cliente){
         listaAccionesUsuario=accionesDAO.consultaAccionesCompra(cliente.getId());
    }

    public Acciones getEmpresaSeleccionada() {
        return accionesJlist.getSelectedValue();
    }



    public int getNumeroAcciones() {
        try {
            return Integer.parseInt(accionesTextField.getText());
        } catch (NumberFormatException e) {
            // Manejo de error si el usuario ingresa un valor no numérico
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de acciones.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Valor inválido
        }
    }


}
