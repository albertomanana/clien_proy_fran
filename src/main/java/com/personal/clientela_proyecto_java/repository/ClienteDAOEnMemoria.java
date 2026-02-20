package com.personal.clientela_proyecto_java.repository;

import com.personal.clientela_proyecto_java.model.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Qualifier("clienteDAOEnMemoria")
public class ClienteDAOEnMemoria implements ClienteDAO {

    private static final List<Cliente> clientes = new ArrayList<>();
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

    static {
        // Pre-cargar clientes de ejemplo
        Cliente c1 = new Cliente(NEXT_ID.getAndIncrement(), "Juan", "Pérez", "juan@gmail.com", "123456789", "Calle 123",
                null, 100.0, 200.0, 300.0, 400.0);
        c1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        c1.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        c1.setBalanceTotal(c1.calcularBalance());
        clientes.add(c1);

        Cliente c2 = new Cliente(NEXT_ID.getAndIncrement(), "María", "López", "maria@gmail.com", "987654321",
                "Avenida 456", null, 50.0, 150.0, 250.0, 350.0);
        c2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        c2.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        c2.setBalanceTotal(c2.calcularBalance());
        clientes.add(c2);

        Cliente c3 = new Cliente(NEXT_ID.getAndIncrement(), "Pedro", "García", "pedro@gmail.com", "555666777",
                "Plaza Mayor 7", null, 80.0, 180.0, 280.0, 380.0);
        c3.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        c3.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        c3.setBalanceTotal(c3.calcularBalance());
        clientes.add(c3);
    }

    @Override
    public void guardar(Cliente cliente) {
        if (cliente.getId() == 0) {
            cliente.setId(NEXT_ID.getAndIncrement());
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        cliente.setCreatedAt(now);
        cliente.setUpdatedAt(now);
        cliente.setBalanceTotal(cliente.calcularBalance());
        clientes.add(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getId() == cliente.getId()) {
                // Mantener fotoPath si el nuevo viene null o vacío
                if (cliente.getFotoPath() == null || cliente.getFotoPath().isEmpty()) {
                    cliente.setFotoPath(c.getFotoPath());
                }
                cliente.setCreatedAt(c.getCreatedAt()); // Mantener fecha creación
                cliente.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                cliente.setBalanceTotal(cliente.calcularBalance());
                clientes.set(i, cliente);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        clientes.removeIf(c -> c.getId() == id);
    }

    @Override
    public Cliente obtenerPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }
}
