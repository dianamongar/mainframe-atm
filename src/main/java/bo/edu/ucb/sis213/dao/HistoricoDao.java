package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoDao {
    Connection connection;
    public HistoricoDao() throws SQLException{
        connection = ConexionDao.getConnection();
    }
    public void insertarHistorico(int id, String tipoOperacion, double esp) throws SQLException{
        String updateQueryHist = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)"; // Cambia esto según tu tabla
                PreparedStatement preparedStatementHist = connection.prepareStatement(updateQueryHist);
                preparedStatementHist.setInt(1,id);
                preparedStatementHist.setString(2,tipoOperacion);
                preparedStatementHist.setDouble(3, esp);
                preparedStatementHist.executeUpdate(); 
    }
}
