package ar.edu.utn.frbb.tup.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Cliente extends Persona{

    private TipoPersona tipoPersona;
    private String banco;
    private LocalDate fechaAlta;
    private Set<Cuenta> cuentas = new HashSet<>();
    private TipoCuenta tipoCuenta;

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void getNombreCuenta() {
        for (Cuenta cuenta : cuentas) {
            System.out.println(cuenta.getNombre());
        }
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }
    public void eliminarCuenta(Cuenta cuenta) {
        this.cuentas.remove(cuenta);
    }

    public void realizarRetiro(int monto){
        for (Cuenta cuenta : cuentas) {
            cuenta.retirar(monto);
        }
    }

    public void realizarDeposito(String nombreCuenta, int monto) {
        if (monto > 0) {
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getNombre().equals(nombreCuenta)) {
                    cuenta.depositar(monto);
                    System.out.println("Se depositaron " + monto + " unidades en la cuenta " + nombreCuenta);
                    return;
                }
            }
        System.out.println("Error: Cuenta no encontrada.");
        }
        throw new UnsupportedOperationException("Unimplemented method 'realizarDeposito'");
    }

    
    public boolean tieneCuenta(Long numeroCuenta, Object moneda) {
        if (moneda == null) {
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return true;
                }
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'tieneCuenta'");
    }

    

}
