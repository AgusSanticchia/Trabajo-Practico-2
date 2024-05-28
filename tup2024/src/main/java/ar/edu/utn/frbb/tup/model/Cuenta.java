package ar.edu.utn.frbb.tup.model;

import java.time.LocalDateTime;

public class Cuenta {
    private String nombre;
    private LocalDateTime fechaCreacion;
    private int balance;

    public String getNombre() {
        return nombre;
    }

    public Cuenta setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Cuenta setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public Cuenta setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public void depositar(int monto) {
        if (monto > 0) {
            balance += monto;
            System.out.println("Se depositaron " + monto + " unidades en la cuenta " + nombre);
        } else {
            System.out.println("Error: El monto a depositar debe ser mayor que cero.");
        }
    }

    public void retirar(int monto) {
        if (monto > 0 && balance >= monto) {
            balance -= monto;
            System.out.println("Se retiraron " + monto + " unidades de la cuenta " + nombre);
        } else if (monto <= 0) {
            System.out.println("Error: El monto a retirar debe ser mayor que cero.");
        } else {
            System.out.println("Error: Saldo insuficiente para realizar el retiro.");
        }
    }

    public Long getNumeroCuenta() {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre de la cuenta no puede ser nulo o vacío.");
            return null;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getNumeroCuenta'");
    }

    public Persona getTitular() {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre de la cuenta no puede ser nulo o vacío.");
            return null;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getTitular'");
    }

    public Object getMoneda() {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre de la cuenta no puede ser nulo o vacío.");
            return null;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getMoneda'");
    }

    public Cuenta setMoneda(String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setMoneda'");
    }

    public Cuenta setTipoCuenta(TipoCuenta cajaAhorro) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre de la cuenta no puede ser nulo o vacío.");
            return null;
        }
        throw new UnsupportedOperationException("Unimplemented method 'setTipoCuenta'");
    }

}
