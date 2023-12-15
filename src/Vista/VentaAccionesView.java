package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.ClientesDAO;
import Modelo.DAO.TransaccionesDAO;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Empresas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentaAccionesView extends JFrame {

    private Clientes clientes;
    private Conexion conexion;

    private ArrayList<Clientes> listaClientes;

    private DefaultListModel<Clientes> listModel;
    private ClientesDAO clientesDAO;
    private TransaccionesDAO transaccionesDAO;

    private JList<Clientes> clientesJList;

    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";

    public VentaAccionesView(Clientes c){
        this.clientes=c;
        listaClientes= new ArrayList<Clientes>();
        llenarLista();
        // Hacer visible la ventana
        setVisible(true);
        System.out.println("el tamaño de la lista es" + listaClientes.size());

        conexion = new Conexion(DB_URL, USER, PASS);
        clientesDAO = new ClientesDAO(conexion);
        transaccionesDAO = new TransaccionesDAO(conexion);
        setTitle("Venta Acciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        for (Clientes cliente : listaClientes) {
            listModel.addElement(cliente);
        }


        // Crear la JList con el modelo
        clientesJList = new JList<>(listModel);
        clientesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // Personalizar el renderer para mostrar el nombre del cliente u otra información relevante
        clientesJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Clientes) {
                    Clientes cliente = (Clientes) value;
                    // Personaliza esta parte según lo que desees mostrar para el cliente
                    setText("ID: " + cliente.getId() + " Nombre: " + cliente.getNombre());
                }
                return this;
            }
        });

        getContentPane().add(new JScrollPane(clientesJList));


    }

    public void llenarLista(){
        listaClientes= clientesDAO.clientesFiltados(clientes.getId());
    }




}
