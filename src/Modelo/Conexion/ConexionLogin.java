package Modelo.Conexion;

import java.sql.*;

public class ConexionLogin {

    private String DB_URL;
    private String USER;
    private String PASS;
    private String QUERY;

    private Connection connection;

    public ConexionLogin(String DB_URL, String USER, String PASS, String QUERY) {
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
        this.QUERY = QUERY;

        try {
            // Inicializar la conexión en el constructor
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexión creada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int validarUsuario(String usuario, String contraseña) {


        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clientes WHERE usuario = ? AND contraseña = ?")) {
            statement.setString(1, usuario);
            statement.setString(2, contraseña);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Si el usuario es administrador o simplemente un usuario válido, devuelve true
                    // 0: Admin
                    //1: Usuario:
                    //2: error
                    if (resultSet.getBoolean("esAdmin")) {
                        return 0;
                    }else {
                        return 1;
                    }
                }
                else{
                    return 2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 2;
    }


    // Otros métodos que puedas necesitar para manipular la conexión, cerrarla, etc.

    public void setQUERY(String QUERY) {
        this.QUERY = QUERY;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
