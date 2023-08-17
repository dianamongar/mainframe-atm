package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Usuario {
    String usuario;
    String nombre;
    int password;
    double saldo;
    int id;
    public Usuario(String usuario,String nombre, int password, double saldo, int id){
        this.usuario=usuario;
        this.nombre=nombre;
        this.password=password;
        this.saldo=saldo;
        this.id=id;
    }
    public Usuario verificarUsuario(Connection connection, int pass, String user){
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
}
