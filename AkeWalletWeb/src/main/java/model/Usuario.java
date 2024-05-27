package model;

public class Usuario {
    private int id;
    private String nombre;
    private String clave;
    private double saldo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String clave, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
