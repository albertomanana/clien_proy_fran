package com.personal.clientela_proyecto_java.repository;

import com.personal.clientela_proyecto_java.model.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("clienteDAOJdbc")
public class ClienteDAOJdbc implements ClienteDAO {

    private Connection getConnection() {
        return Conexion.getInstancia().getConnection();
    }

    @Override
    public void guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, email, telefono, direccion, foto_path, pago1, pago2, pago3, pago_final, balance_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setString(6, cliente.getFotoPath());
            pstmt.setDouble(7, cliente.getPago1());
            pstmt.setDouble(8, cliente.getPago2());
            pstmt.setDouble(9, cliente.getPago3());
            pstmt.setDouble(10, cliente.getPagoFinal());
            pstmt.setDouble(11, cliente.getBalanceTotal());
            pstmt.executeUpdate();
            System.out.println("✅ Cliente guardado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar el cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, apellidos = ?, email = ?, telefono = ?, direccion = ?, foto_path = ?, pago1 = ?, pago2 = ?, pago3 = ?, pago_final = ?, balance_total = ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellidos());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setString(6, cliente.getFotoPath());
            pstmt.setDouble(7, cliente.getPago1());
            pstmt.setDouble(8, cliente.getPago2());
            pstmt.setDouble(9, cliente.getPago3());
            pstmt.setDouble(10, cliente.getPagoFinal());
            pstmt.setDouble(11, cliente.getBalanceTotal());
            pstmt.setInt(12, cliente.getId());
            pstmt.executeUpdate();
            System.out.println("✅ Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el cliente.");
            e.printStackTrace();
        }
    }

    @Override
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el cliente por ID.");
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY created_at DESC";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar los clientes.");
            e.printStackTrace();
        }
        return clientes;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidos(rs.getString("apellidos"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setFotoPath(rs.getString("foto_path"));
        cliente.setPago1(rs.getDouble("pago1"));
        cliente.setPago2(rs.getDouble("pago2"));
        cliente.setPago3(rs.getDouble("pago3"));
        cliente.setPagoFinal(rs.getDouble("pago_final"));
        cliente.setBalanceTotal(rs.getDouble("balance_total"));
        cliente.setCreatedAt(rs.getTimestamp("created_at"));
        cliente.setUpdatedAt(rs.getTimestamp("updated_at"));
        return cliente;
    }
}
