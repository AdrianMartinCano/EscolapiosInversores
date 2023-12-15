package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.AccionesDAO;
import Modelo.DAO.TransaccionesDAO;
import Modelo.Entidades.Acciones;
import Modelo.Entidades.Clientes;
import Modelo.Entidades.Transacciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccionesListaView extends JFrame {

    private Clientes c;
    private ArrayList<Transacciones> listaTransacciones;
    private Conexion conexion;
    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";

    private AccionesDAO accionesDAO;

    private TransaccionesDAO transaccionesDAO;

    private JTable table;
    private JButton backButton;

    public AccionesListaView(Clientes c) {
        conexion = new Conexion(DB_URL, USER, PASS);
        accionesDAO = new AccionesDAO(conexion);
        transaccionesDAO = new TransaccionesDAO(conexion);
        this.c = c;
        listaTransacciones = new ArrayList<Transacciones>();
        llenarLista(c);

        // Configurar la tabla
        String[] columnNames = {"ID Cliente", "Nombre Cliente", "Apellido", "Número Acciones", "Nombre Empresa", "Tipo Operación"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Llenar la tabla con datos
        for (Transacciones transacciones : listaTransacciones) {
            Object[] rowData = {
                    transacciones.getIdCliente(),
                    transacciones.getNombreCliente(),
                    transacciones.getApellido(),
                    transacciones.getNumeroAcciones(),
                    transacciones.getNombreEmpresa(),
                    transacciones.getTipoOperacion().toString()
            };
            model.addRow(rowData);
        }

        // Agregar la tabla a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar el JScrollPane al JFrame
        add(scrollPane);

        // Configurar el botón de "Atrás"
        backButton = new JButton("Atrás");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual al hacer clic en el botón de "Atrás"
                dispose();
            }
        });

        // Crear un panel para el botón de "Atrás"
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        // Agregar el panel de botones al JFrame
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurar la ventana
        setTitle("Lista de Acciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void llenarLista(Clientes c) {
        listaTransacciones = transaccionesDAO.listaTransacciones(c.getId());

    }

    public void actualizarTabla() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

        // Llenar la tabla con datos actualizados
        for (Transacciones transacciones : listaTransacciones) {
            Object[] rowData = {
                    transacciones.getIdCliente(),
                    transacciones.getNombreCliente(),
                    transacciones.getApellido(),
                    transacciones.getNumeroAcciones(),
                    transacciones.getNombreEmpresa(),
                    transacciones.getTipoOperacion(),
            };
            model.addRow(rowData);
        }
    }

}
