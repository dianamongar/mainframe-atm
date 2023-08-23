package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.edu.ucb.sis213.until.Usuario;

public class UsuariosDao {
    Connection connection;
    HistoricoDao historicoDao;
    public UsuariosDao(){
        try {
            connection = ConexionDao.getConnection();
            historicoDao= new HistoricoDao();
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a la Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }

        
    }
    public Usuario verificarUsuario(int pass, String user){
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
                return new Usuario(user,nom,pass,sald,id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Usuario(null,null,0,0,0);
    }
    public double consultarSaldo(int idUsuario){
        double saldo=0;
        String query = "SELECT saldo FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();   
            if (resultSet.next()) {
                saldo = resultSet.getDouble("saldo");
                historicoDao.insertarHistorico(idUsuario,"Consulta Saldo", saldo);
                System.out.println("Su saldo actual es: $" + saldo);
                return saldo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saldo;
    }
    public boolean actualizarSaldo(int id, double monto, String op){
        String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
        if(op=="Retiro")
            updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?"; // Cambia esto según tu tabla
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, monto);
            preparedStatement.setInt(2, id); // Cambia el valor según el ID de la cuenta
            int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected  > 0) {
                    System.out.println("Actualizacion realizada con exito.");
                    historicoDao.insertarHistorico(id,op,monto);
                    return true;
                } else {
                    System.out.println("No se pudo realizar la actualizacion.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al realizar la actualizacion: " + e.getMessage());
                return false;
            }
    }
    public boolean cambiarPassword(int nuevoPin, int id){
            try {
                String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?"; // Cambia esto según tu tabla
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, nuevoPin);
                preparedStatement.setInt(2, id); // Cambia el valor según el ID de la cuenta
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Cambio de pin realizado con éxito. Su pin ha sido actualizado.");
                    historicoDao.insertarHistorico(id,"Cambio Pin",nuevoPin);
                    return true;
                } else {
                    System.out.println("No se pudo realizar el cambio de pin.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error al realizar el cambio de pin: " + e.getMessage());
                return false;
            }
    } 
}
