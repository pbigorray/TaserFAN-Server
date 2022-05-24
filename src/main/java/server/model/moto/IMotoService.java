package server.model.moto;

import model.Empleado;
import model.Moto;
import model.Result;

import java.util.List;

public interface IMotoService {
    Moto getMoto(String matricula);
    Result<Moto> addMoto(Moto moto);
    Result<Moto> updateMoto(Moto moto);
    Result<Moto> deleteMoto(String matricula);

}
