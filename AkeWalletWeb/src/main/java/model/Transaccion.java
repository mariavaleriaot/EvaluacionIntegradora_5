package model;

import java.sql.Timestamp;
/**
 * Clase que representa una transacción financiera.
 */
public class Transaccion {
    private int id;
    private int usuarioId;
    private String tipo;
    private double monto;
    private Timestamp fecha;

    public Transaccion() {
    }

    public Transaccion(int id, int usuarioId, String tipo, double monto, Timestamp fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
