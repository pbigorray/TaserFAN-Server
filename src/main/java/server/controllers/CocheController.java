package server.controllers;

import model.Coche;
import model.Moto;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.JSonTranformer;
import server.model.coche.ImpCocheService;
import server.model.moto.ImpMotoService;
import spark.Request;
import spark.Response;

public class CocheController {
    static Logger logger= LoggerFactory.getLogger(MotoController.class);
    private static JSonTranformer<Coche> jSonTranformer=new JSonTranformer<>();
    private static ImpCocheService service=new ImpCocheService();

    public static Coche get(Request request, Response response) {
        logger.info("Request coche by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");

        response.type("application/json");
        response.status(200);
        response.body();
        return service.getCoche(matricula);
    }

    public static Result<Coche> delete(Request request, Response res) {
        logger.info("delete Request Coche by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");
        Result<Coche> result = service.deleteCoche(matricula);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }

    public static Result<Coche> add(Request request, Response res) {

        Coche m = jSonTranformer.getObject(request.body(), Coche.class);
        logger.info("add Request Coche by matricula: " + m.getTipoCarnet() );
        Result<Coche> result = service.addCoche(m);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }
    public static Result<Coche> update(Request request, Response res) {
        Coche c = jSonTranformer.getObject(request.body(), Coche.class);

        logger.info("update Request Coche by matricula: " + c.getMatricula() );
        Result<Coche> result = service.updateCoche(c);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }
}
