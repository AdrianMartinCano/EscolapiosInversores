package Vista;

import Modelo.Entidades.Clientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeleccionClienteVista extends JFrame {

    private ArrayList<Clientes> listaClientes;
    private JList<Clientes> clientesJList;
    private JButton seleccionarButton;

    private Clientes clientes;

    public SeleccionClienteVista(ArrayList<Clientes> listaClientes, AdminVista adminVista) {
        this.listaClientes = listaClientes;
        setTitle("Seleccionar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Crear el modelo para la JList
        DefaultListModel<Clientes> listModel = new DefaultListModel<>();
        for (Clientes cliente : listaClientes) {
            listModel.addElement(cliente);
        }

        // Crear la JList con el modelo
        clientesJList = new JList<>(listModel);
        clientesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Crear el botón de selección
        seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes clienteSeleccionado = clientesJList.getSelectedValue();

                if (clienteSeleccionado != null) {
                    adminVista.setClienteSeleccionado(clienteSeleccionado);
                    adminVista.actualizarEstadoClienteLabel();
                } else {
                    JOptionPane.showMessageDialog(SeleccionClienteVista.this, "Por favor, seleccione un cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                dispose();
            }
        });

        adminVista.setClienteSeleccionado(clientes);

        // Crear el panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(clientesJList), BorderLayout.CENTER);
        panel.add(seleccionarButton, BorderLayout.SOUTH);

        // Agregar el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    public Clientes getCliente() {
        return clientes;
    }

}
