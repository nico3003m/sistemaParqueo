package clases;

import enums.TipoVehiculo;
import java.time.Duration;
import java.time.LocalDateTime;

public class Vehiculo {

    private String placa;
    private TipoVehiculo tipo;
    private boolean esHibirdo;
    private LocalDateTime ingreso;
    private LocalDateTime salida;
    private int plazaAsignada;

    public Vehiculo(String placa, TipoVehiculo tipo, boolean esHibirdo, LocalDateTime ingreso, int plazaAsignada) {
        this.placa = placa;
        this.tipo = tipo;
        this.esHibirdo = esHibirdo;
        this.ingreso = ingreso;
        this.plazaAsignada = plazaAsignada;
    }

    public Vehiculo() {
    }

   public double costos() {
    if (ingreso == null || salida == null) {
        return 0;
    }

    double tarifa = (tipo == TipoVehiculo.MOTOCICLETA) ? 62 : 120;
    long horas = Duration.between(ingreso, salida).toHours();
    double costoTotal = tarifa * horas;

    if (esHibirdo) {
        costoTotal *= 0.75;
    }

    return costoTotal;
}


    public String getPlaca() {
        return placa;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public boolean isEsHibirdo() {
        return esHibirdo;
    }

    public LocalDateTime getIngreso() {
        return ingreso;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public int getPlazaAsignada() {
        return plazaAsignada;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public void setEsHibirdo(boolean esHibirdo) {
        this.esHibirdo = esHibirdo;
    }

    public void setIngreso(LocalDateTime ingreso) {
        this.ingreso = ingreso;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public void setPlazaAsignada(int plazaAsignada) {
        this.plazaAsignada = plazaAsignada;
    }

   
}
