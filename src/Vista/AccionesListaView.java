package Vista;

import Modelo.Conexion.Conexion;
import Modelo.DAO.AccionesDAO;
import Modelo.Entidades.Acciones;
import Modelo.Entidades.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccionesListaView extends JFrame {

    private Clientes c;
    private ArrayList<Acciones> listaAcciones;
    private Conexion conexion;
    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";

    private AccionesDAO accionesDAO;

    private JTable table;
    private JButton backButton;

    public AccionesListaView(Clientes c) {
        conexion = new Conexion(DB_URL, USER, PASS);
        accionesDAO = new AccionesDAO(conexion);
        this.c = c;
        listaAcciones = new ArrayList<Acciones>();
        llenarLista(c);

        // Configurar la tabla
        String[] columnNames = {"ID Cliente", "Nombre Cliente", "Apellido", "Número Acciones", "Nombre Empresa"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Llenar la tabla con datos
        for (Acciones acciones : listaAcciones) {
            Object[] rowData = {
                    acciones.getIdCliente(),
                    acciones.getNombrecliente(),
                    acciones.getApellido(),
                    acciones.getNumeroAcciones(),
                    acciones.getNombreEmpresa()
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
        listaAcciones = accionesDAO.consultaAcciones(c.getId());
    }


}
