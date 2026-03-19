package com.personal.clientela_proyecto_java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.personal.clientela_proyecto_java.model.TipoCliente;

@Repository
public class TipoClienteDAOJdbc implements TipoClienteDAO {

    private Connection getConnection() {
        return Conexion.getInstancia().getConnection();
    }

    @Override
    public List<TipoCliente> listarTodos() {
        List<TipoCliente> tipos = new ArrayList<>();
        String sql = "SELECT id, nombre FROM tipo_cliente ORDER BY nombre ASC";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                tipos.add(new TipoCliente(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tipos de cliente.");
            e.printStackTrace();
        }
        return tipos;
    }
}
