package server.controllers;

import model.Moto;
import model.Result;
import model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.ImpVehiculoService;
import server.model.JSonTranformer;
import server.model.moto.IMotoService;
import server.model.moto.ImpMotoService;
import spark.Request;
import spark.Response;

public class MotoController {
    static Logger logger= LoggerFactory.getLogger(MotoController.class);
    private static JSonTranformer<Moto> jSonTranformer=new JSonTranformer<>();
    private static ImpMotoService service=new ImpMotoService();

    public static Moto get(Request request, Response response) {
        logger.info("Request moto by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");

        response.type("application/json");
        response.status(200);
        response.body();
        return service.getMoto(matricula);
    }

    public static Result<Moto> delete(Request request, Response res) {
        logger.info("delete Request moto by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");
        Result<Moto> result = service.deleteMoto(matricula);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }

    public static Result<Moto> add(Request request, Response res) {

        Moto m = jSonTranformer.getObject(request.body(), Moto.class);
        logger.info("add Request moto by matricula: " + m.getMatricula() );
        Result<Moto> result = service.addMoto(m);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }
    public static Result<Moto> update(Request request, Response res) {
        Moto m = jSonTranformer.getObject(request.body(), Moto.class);

        logger.info("update Request moto by matricula: " + m.getMatricula() );
        Result<Moto> result = service.updateMoto(m);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }

}
