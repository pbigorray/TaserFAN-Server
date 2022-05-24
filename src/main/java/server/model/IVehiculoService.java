package server.model;

import model.*;

import java.util.List;

public interface IVehiculoService {
    List<Vehiculo> getAll();
    List<Vehiculo> getVehiculos(Tipo tipo);
    List<Vehiculo> getCoche(String matricula);
    List<Vehiculo> getMoto(String matricula);
    List<Vehiculo> getBici(String matricula);
    List<Vehiculo> getPatinete(String matricula);

}
