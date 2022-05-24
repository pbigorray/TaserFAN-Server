package server.model.patinete;

import model.Coche;
import model.Patinete;
import model.Result;

public interface IPateneteService {
    Patinete getPatinete(String matricula);
    Result<Patinete> addPatinete(Patinete patinete);
    Result<Patinete> updatePatinete(Patinete patinete);
    Result<Patinete> deletePatinete(String matricula);
}
