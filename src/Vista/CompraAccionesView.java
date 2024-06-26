package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.AccionesDAO;
import Modelo.DAO.ClientesDAO;
import Modelo.DAO.TransaccionesDAO;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Empresas;
import Modelo.Tipos.TipoOperacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CompraAccionesView extends JFrame {
    private Clientes clientes;
    private JButton botonAtras;
    private JButton seleccionarButton;
    private ArrayList<Empresas> listaEmpresas;
    private JList<Empresas> empresasJList;
    private DefaultListModel<Empresas> listModel;
    private JTextField accionesTextField;
    private int precio;
    private int cantidad;
    private String nombreEmpresa;

    private ClientesDAO clientesDAO;

    private TransaccionesDAO transaccionesDAO;

    private AccionesDAO accionesDAO;
    private Empresas empresas;
    private Conexion conexion;

    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";

    public CompraAccionesView(ArrayList<Empresas> listaEmpresas, Clientes clientes) {
        this.listaEmpresas = listaEmpresas;
        this.clientes=clientes;
        this.nombreEmpresa="";
        conexion = new Conexion(DB_URL, USER, PASS);
        clientesDAO = new ClientesDAO(conexion);
        transaccionesDAO = new TransaccionesDAO(conexion);
        accionesDAO = new AccionesDAO(conexion);
        setTitle("Compra acciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Crear el modelo para la JList
        listModel = new DefaultListModel<>();
        for (Empresas empresa : listaEmpresas) {
            listModel.addElement(empresa);
        }

        // Crear la JList con el modelo
        empresasJList = new JList<>(listModel);
        empresasJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Personalizar el renderer para mostrar el nombre de la empresa y precio de la acción
        empresasJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Empresas) {
                    Empresas empresa = (Empresas) value;
                    Random ran = new Random();
                    int precio = ran.nextInt(10000) + 200;
                    setText(empresa.getNombreEmpresa() + " Precio acción: " + precio);

                }
                return this;
            }
        });

        // Cuadro de texto para introducir el número de acciones
        accionesTextField = new JTextField();
        accionesTextField.setPreferredSize(new Dimension(50, accionesTextField.getPreferredSize().height)); // Ajustar el ancho
        seleccionarButton = new JButton("Comprar");
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cantidad=Integer.parseInt(accionesTextField.getText().toString());
                nombreEmpresa=getEmpresaSeleccionada().getNombreEmpresa();
                JOptionPane.showMessageDialog(CompraAccionesView.this, "Se han comprado " + cantidad + " acciones de la empresa " + nombreEmpresa);
                clientesDAO.actualizarAcciones(clientes.getId(),cantidad);
                clientes.setNumeroAcciones(clientes.getNumeroAcciones()+getNumeroAcciones());
                transaccionesDAO.actualizarTransaccionCompra(clientes, nombreEmpresa);
                Clientes aux = new Clientes(clientes.getId(),
                        clientes.getNombre(),
                        clientes.getApellido(),
                        clientes.getDNI(),
                        clientes.getNumeroCuenta(),
                        getNumeroAcciones(),
                        clientes.getUsuario(),
                        clientes.getContraseña(),
                        clientes.isEsAdmin());
                accionesDAO.agregarAcciones(aux, nombreEmpresa, TipoOperacion.Compra, getNumeroAcciones());
            }
        });

        // Crear el panel principal con GridLayout
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JScrollPane(empresasJList));

        // Panel para el cuadro de texto y el botón
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
                adminVista.setNombreEmpresa(nombreEmpresa);
                CompraAccionesView.this.dispose(); // Método para cerrar la ventana
            }
        });

        inputPanel.add(botonAtras);
        panel.add(inputPanel);

        // Agregar el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para obtener el número de acciones ingresado
    public int getNumeroAcciones() {
        try {
            return Integer.parseInt(accionesTextField.getText());
        } catch (NumberFormatException e) {
            // Manejo de error si el usuario ingresa un valor no numérico
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de acciones.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Valor inválido
        }
    }

    // Método para obtener la empresa seleccionada
    public Empresas getEmpresaSeleccionada() {
        return empresasJList.getSelectedValue();
    }
}

