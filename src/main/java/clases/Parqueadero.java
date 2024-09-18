package clases;

import enums.TipoVehiculo;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {

    private List<Vehiculo> vehiculos = new ArrayList<>();

    public boolean agregarVehiculo(Vehiculo vehiculo) {
        // Validar si hay plazas disponibles
        if (vehiculo.getTipo() == TipoVehiculo.MOTOCICLETA) {
            vehiculos.add(vehiculo);
            return true;

        } else if (vehiculo.getTipo() == TipoVehiculo.VEHICULO_LIGERO) {

            vehiculos.add(vehiculo);
            return true;

        }
        return false;
    }

}
