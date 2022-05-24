package server.model.coche;

import model.Coche;
import model.Moto;
import model.Result;

public interface ICocheService {
    Coche getCoche(String matricula);
    Result<Coche> addCoche(Coche coche);
    Result<Coche> updateCoche(Coche coche);
    Result<Coche> deleteCoche(String matricula);
}
