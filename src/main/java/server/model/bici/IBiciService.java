package server.model.bici;

import model.Bicicleta;
import model.Moto;
import model.Result;

public interface IBiciService {
    Bicicleta getBicicleta(String matricula);
    Result<Bicicleta> addBicicleta(Bicicleta bicicleta);
    Result<Bicicleta> updateBicicleta(Bicicleta bicicleta);
    Result<Bicicleta> deleteBicicleta(String matricula);

}
