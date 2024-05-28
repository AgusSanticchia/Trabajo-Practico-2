package ar.edu.utn.frbb.tup.persistence.entity;

import ar.edu.utn.frbb.tup.model.Cuenta;
import java.time.LocalDateTime;

public class CuentaEntity extends BaseEntity{
    String nombre;
    LocalDateTime fechaCreacion;
    int balance;
    String tipoCuenta;
    Long titular;

    public CuentaEntity(Cuenta cuenta) {
        super(cuenta.getNumeroCuenta());
        this.nombre = cuenta.getNombre();
        this.balance = cuenta.getBalance();
        this.tipoCuenta = cuenta.getNumeroCuenta().toString();
        this.titular = cuenta.getTitular().getDni();
        this.fechaCreacion = cuenta.getFechaCreacion();
    }
}
