package server.model.patinete;

import model.Patinete;
import model.Result;

public interface IPatineteService {
    Patinete getPatinete(String matricula);
    Result<Patinete> addPatinete(Patinete patinete);
    Result<Patinete> updatePatinete(Patinete patinete);
    Result<Patinete> deletePatinete(String matricula);
}
