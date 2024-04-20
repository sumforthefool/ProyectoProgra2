package com.mycompany.prueba2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TalonarioDAO {

    private Connection conexion;

    public TalonarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Talonario> obtenerTodos() throws SQLException {
        List<Talonario> listaTalonarios = new ArrayList<>();
        String sql = "SELECT id_talonario, descripcion, premio, fecha, valor_numero FROM Talonario";
        try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_talonario");
                String descripcion = resultSet.getString("descripcion");
                String premio = resultSet.getString("premio");
                String fecha = resultSet.getString("fecha");
                int valorNumero = resultSet.getInt("valor_numero");

                Talonario talonario = new Talonario(descripcion, premio, fecha, valorNumero);
                listaTalonarios.add(talonario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + e.getMessage(), "Error de SQL", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        return listaTalonarios;
    }

    public void agregarTalonario(Talonario talonario) throws SQLException {
        String sql = "INSERT INTO Talonario (descripcion, premio, fecha, valor_numero) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, talonario.getDescripcion());
            statement.setString(2, talonario.getPremio());
            statement.setString(3, talonario.getFecha());
            statement.setInt(4, talonario.getValorNumero());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                talonario.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("No se pudo obtener el ID generado automáticamente.");
            }
        }
    }


public void eliminarTalonario(int id) throws SQLException {
    String sql = "DELETE FROM Talonario WHERE id_talonario = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el talonario: " + e.getMessage(), "Error al eliminar", JOptionPane.ERROR_MESSAGE);
        throw e;
    }
}

  
}
