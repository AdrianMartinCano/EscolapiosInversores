package Vista;

import Modelo.Conexion.ConexionLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField usuarioTextField;
    private JPasswordField contrasenaPasswordField;

    private ConexionLogin conLogin;

    private static final String DB_URL = "jdbc:mysql://localhost/escolapios";
    private static final String USER = "root";
    private static final String PASS = "root";
    private String QUERY = "SELECT * FROM Clientes";

    public Login() {
        conLogin = new ConexionLogin(DB_URL, USER, PASS, QUERY);
        // Configurar la ventana
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear paneles
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // Añadir espacio entre componentes

        // Etiquetas y campos de texto
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioTextField = new JTextField();

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaPasswordField = new JPasswordField();

        // Botón de entrada
        JButton entrarButton = new JButton("Entrar");
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (conLogin.validarUsuario(usuarioTextField.getText(), contrasenaPasswordField.getText())==0) {
                    JOptionPane.showMessageDialog(Login.this, "Te has logueado como administrador");
                    Login.this.dispose();
                    conLogin.cerrarConexion();
                    AdminVista av = new AdminVista();
                } else if(conLogin.validarUsuario(usuarioTextField.getText(), contrasenaPasswordField.getText())==1) {
                    JOptionPane.showMessageDialog(Login.this, "Bienvenido, " + usuarioTextField.getText());
                    conLogin.cerrarConexion();
                    Login.this.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(Login.this, "Error: Usuario y/o contraseña incorrecto", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                    usuarioTextField.setText("");
                    contrasenaPasswordField.setText("");
                }
            }
        });

        // Ajustar el tamaño de los campos de texto
        usuarioTextField.setPreferredSize(new Dimension(150, 25));
        contrasenaPasswordField.setPreferredSize(new Dimension(150, 25));

        // Añadir componentes al panel
        panel.add(usuarioLabel);
        panel.add(usuarioTextField);
        panel.add(contrasenaLabel);
        panel.add(contrasenaPasswordField);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(entrarButton);

        // Añadir el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }
}
