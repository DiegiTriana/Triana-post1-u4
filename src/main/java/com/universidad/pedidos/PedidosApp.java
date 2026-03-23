package com.universidad.pedidos;

import com.universidad.pedidos.command.*;
import com.universidad.pedidos.cor.*;
import com.universidad.pedidos.modelo.Pedido;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApp implements CommandLineRunner {
    private final HistorialComandos historial;

    public PedidosApp(HistorialComandos historial) {
        this.historial = historial;
    }

    public static void main(String[] args) {
        SpringApplication.run(PedidosApp.class, args);
    }

    @Override
    public void run(String... args) {
        ValidadorPedido cadena = new ValidadorStock();
        cadena.setNext(new ValidadorMonto()).setNext(new ValidadorCredito());

        System.out.println("\n--- Validando pedido P-001 ---");
        Pedido p1 = new Pedido("P-001", "PROD-A", 3, 45000.0, true);
        boolean ok = cadena.validar(p1);
        System.out.println("Resultado validacion: " + ok);

        if (ok) {
            historial.ejecutar(new ComandoConfirmar(p1));
            historial.ejecutar(new ComandoAplicarDescuento(p1, 10));
            System.out.println("Estado actual: " + p1);

            System.out.println("\n--- Deshaciendo ultima accion ---");
            historial.deshacer();
            System.out.println("Estado despues de undo: " + p1);
        }

        System.out.println("\n--- Validando pedido P-002 (sin credito) ---");
        Pedido p2 = new Pedido("P-002", "PROD-B", 2, 30000.0, false);
        cadena.validar(p2);
    }
}