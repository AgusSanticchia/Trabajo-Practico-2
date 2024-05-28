package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.TipoPersona;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

class ClienteServiceTest {

    private static ClienteService clienteService;

    @BeforeAll
    public static void setUp() {
        clienteService = new ClienteService();
    }

    @Test
    public void testClienteMenor18Años() throws ClienteAlreadyExistsException {
        Cliente clienteMenorDeEdad = new Cliente();
        clienteMenorDeEdad.setFechaNacimiento(LocalDate.of(2020, 2, 7));
        try {
            clienteService.darDeAltaCliente(clienteMenorDeEdad);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

//    private void assertThrows(Class<IllegalArgumentException> class1, Object object) {
//        try {
//            object.toString();
//            fail("Expected IllegalArgumentException to be thrown");
//        } catch (IllegalArgumentException e) {
//            // Expected exception
//        }
//        throw new UnsupportedOperationException("Unimplemented method 'assertThrows'");
//    }

    @Test
    public void testClienteSuccess() throws ClienteAlreadyExistsException {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento(LocalDate.of(1978,3,25));
        cliente.setDni(29857643);
        cliente.setTipoPersona(TipoPersona.INDIVIDUAL);

        clienteService.darDeAltaCliente(cliente);

        ClienteDao dao = new ClienteDao();
        assertNotNull(dao.find(29857643));
    }

    @Test
    public void testClienteAlreadyExistsException() throws ClienteAlreadyExistsException {
        Cliente pepeRino = new Cliente();
        pepeRino.setDni(26456439);
        pepeRino.setNombre("Pepe");
        pepeRino.setApellido("Rino");
        pepeRino.setFechaNacimiento(LocalDate.of(1978, 3, 25));
        pepeRino.setTipoPersona(TipoPersona.LEGAL_ENTITY);

        clienteService.darDeAltaCliente(pepeRino);

        Cliente mateoA = new Cliente();
        mateoA.setDni(26456439);
        mateoA.setNombre("Mateo");
        mateoA.setApellido("Abraham");
        mateoA.setFechaNacimiento(LocalDate.of(2001, 12, 18));
        mateoA.setTipoPersona(TipoPersona.LEGAL_ENTITY);

        try {
            clienteService.darDeAltaCliente(mateoA);
            fail("Expected ClienteAlreadyExistsException");
        } catch (ClienteAlreadyExistsException e) {
            // Expected exception
        }
    }

//   private void assertThrows(Class<? extends Exception> exceptionClass, Runnable runnable) {
//    try {
//        runnable.run();
//        fail("Expected " + exceptionClass.getSimpleName() + " to be thrown");
//    } catch (Exception e) {
//        if (exceptionClass.isInstance(e)) {
//            return; // Expected exception
//        }
//            throw new AssertionError("Expected " + exceptionClass.getSimpleName() + ", but got " + e.getClass().getSimpleName(), e);
//        }
//        throw new AssertionError("Expected " + exceptionClass.getSimpleName() + " to be thrown");
//    }


    @Test
    public void testAgregarCuentaAClienteSuccess() throws TipoCuentaAlreadyExistsException {
        Cliente pepeRino = new Cliente();
        pepeRino.setDni(26456439);
        pepeRino.setNombre("Pepe");
        pepeRino.setApellido("Rino");
        pepeRino.setFechaNacimiento(LocalDate.of(1978, 3,25));
        pepeRino.setTipoPersona(TipoPersona.LEGAL_ENTITY);

        Cuenta cuenta = new Cuenta()
                .setMoneda("ARS")
                .setBalance(500000)
                .setTipoCuenta(TipoCuenta.CAJA_AHORRO);

        clienteService.agregarCuenta(cuenta, pepeRino);

        assertEquals(1, pepeRino.getCuentas().size());
        assertEquals(pepeRino, cuenta.getTitular());
    }
}