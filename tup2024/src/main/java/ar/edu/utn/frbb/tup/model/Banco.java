package ar.edu.utn.frbb.tup.model;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }
    
    public void agregarCuentaACliente(String nombreCliente, Cuenta cuenta) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreCliente)) {
                cliente.addCuenta(cuenta);
                return;
            }
        }
        System.out.println("Error: Cliente no encontrado.");
    }
    
    public void realizarDeposito(String nombreCliente, String nombreCuenta, int monto) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreCliente)) {
                cliente.realizarDeposito(nombreCuenta, monto);
                return;
            }
        }
        System.out.println("Error: Cliente no encontrado.");
    }
    
    public void realizarRetiro(String nombreCliente, String nombreCuenta, int monto) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreCliente)) {
                cliente.realizarRetiro(nombreCuenta, monto);
                return;
            }
        }
        System.out.println("Error: Cliente no encontrado.");
    }
}
