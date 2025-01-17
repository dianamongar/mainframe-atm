package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UsuarioX {
    String usuario;
    String nombre;
    int password;
    double saldo;
    int id;
    public UsuarioX(String usuario,String nombre, int password, double saldo, int id){
        this.usuario=usuario;
        this.nombre=nombre;
        this.password=password;
        this.saldo=saldo;
        this.id=id;
    }
    public UsuarioX verificarUsuario(Connection connection, int pass, String user){
        String nom="";
        double sald=0;
        int id=0;
        String query = "SELECT id, nombre, pin, saldo, alias FROM usuarios WHERE alias = ? AND pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setInt(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
                nom = resultSet.getString("nombre");
                sald = resultSet.getDouble("saldo");
                return new UsuarioX(user,nom,pass,sald,id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UsuarioX(null,null,0,0,0);
    }
    public void consultarSaldo(Connection connection, double monto){
        String query = "SELECT saldo FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.id);
            ResultSet resultSet = preparedStatement.executeQuery();

                
            if (resultSet.next()) {
                saldo = resultSet.getDouble("saldo");

                String updateQueryHist = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'consultaSaldo', ?)"; // Cambia esto según tu tabla
                PreparedStatement preparedStatementHist = connection.prepareStatement(updateQueryHist);
                preparedStatementHist.setInt(1, this.id);
                preparedStatementHist.setDouble(2, monto);
                preparedStatementHist.executeUpdate();  

                System.out.println("Su saldo actual es: $" + saldo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean depositar(Connection connection, double monto){
         if (monto <= 0) {
            System.out.println("Cantidad no válida.");
            JOptionPane.showMessageDialog(null, "Cantidad no válida");
            return false;
        } else {
            try {
                String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?"; // Cambia esto según tu tabla
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setDouble(1, monto);
                preparedStatement.setInt(2, this.id); // Cambia el valor según el ID de la cuenta

                String updateQueryHist = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'deposito', ?)"; // Cambia esto según tu tabla
                PreparedStatement preparedStatementHist = connection.prepareStatement(updateQueryHist);
                preparedStatementHist.setInt(1,this.id);
                preparedStatementHist.setDouble(2, monto);
                int rowsAffected = preparedStatement.executeUpdate();
                int rowsHist = preparedStatementHist.executeUpdate();
                if (rowsAffected + rowsHist > 0) {
                    this.saldo += monto;
                    System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + saldo);
                    return true;
                } else {
                    System.out.println("No se pudo realizar el depósito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al realizar el depósito: " + e.getMessage());
            }
        }
        return false;
    }
    public int retirar(Connection connection, double monto){
        if (monto <= 0) {
            System.out.println("Cantidad no válida.");
            return 1;
        } else if (monto > saldo) {
            System.out.println("Saldo insuficiente.");
            return 2;
        } else {
            try {
                String updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?"; // Cambia esto según tu tabla
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setDouble(1, monto);
                preparedStatement.setInt(2, this.id); // Cambia el valor según el ID de la cuenta

                String updateQueryHist = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'retiro', ?)"; // Cambia esto según tu tabla
                PreparedStatement preparedStatementHist = connection.prepareStatement(updateQueryHist);
                preparedStatementHist.setInt(1, this.id);
                preparedStatementHist.setDouble(2, monto);
                int rowsAffected = preparedStatement.executeUpdate();
                int rowsHist = preparedStatementHist.executeUpdate();
                if (rowsAffected + rowsHist > 0) {
                    this.saldo -= monto;
                    System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + this.saldo);
                    return 3;
                } else {
                    System.out.println("No se pudo realizar el retiro.");
                    return 4;
                }
            } catch (SQLException e) {
                System.out.println("Error al realizar el retiro: " + e.getMessage());
            }
        }
        return 4;
    }
    public boolean confirmarPassword(Connection connection, int pinIngresado){
        if(this.password==pinIngresado){
            return true;
        }
        return false;
    }
    public int cambiarPassword(Connection connection, int nuevoPin, int confirmacionPin){
            if (nuevoPin == confirmacionPin) {
                //pinActual = nuevoPin;
                //System.out.println("PIN actualizado con éxito.");
                try {
                    String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?"; // Cambia esto según tu tabla
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, nuevoPin);
                    preparedStatement.setInt(2, this.id); // Cambia el valor según el ID de la cuenta
    
                    String updateQueryHist = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'cambioPin', ?)"; // Cambia esto según tu tabla
                    PreparedStatement preparedStatementHist = connection.prepareStatement(updateQueryHist);
                    preparedStatementHist.setInt(1, this.id);
                    preparedStatementHist.setInt(2, nuevoPin);
                    int rowsAffected = preparedStatement.executeUpdate();
                    int rowsHist = preparedStatementHist.executeUpdate();
                    if (rowsAffected + rowsHist > 0) {
                        this.password=nuevoPin;
                        System.out.println("Cambio de pin realizado con éxito. Su pin ha sido actualizado.");
                        return 1;
                    } else {
                        System.out.println("No se pudo realizar el cambio de pin.");
                        return 2;
                    }
                } catch (SQLException e) {
                    System.out.println("Error al realizar el cambio de pin: " + e.getMessage());
                    return 2;
                }
            } else {
                System.out.println("Los PINs no coinciden.");
                return 3;
            }
        }
}
