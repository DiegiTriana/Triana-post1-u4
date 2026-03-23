package com.universidad.pedidos.cor;

import com.universidad.pedidos.modelo.Pedido;

public abstract class ValidadorPedido {
    protected ValidadorPedido siguiente;

    public ValidadorPedido setNext(ValidadorPedido next) {
        this.siguiente = next;
        return next;
    }

    public abstract boolean validar(Pedido pedido);

    protected boolean delegar(Pedido pedido) {
        if (siguiente == null) return true;
        return siguiente.validar(pedido);
    }
}