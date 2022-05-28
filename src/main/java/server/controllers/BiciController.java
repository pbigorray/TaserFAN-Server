package server.controllers;

import model.Bicicleta;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.JSonTranformer;
import server.model.bici.ImpBiciService;
import spark.Request;
import spark.Response;

public class BiciController {
    static Logger logger= LoggerFactory.getLogger(MotoController.class);
    private static JSonTranformer<Bicicleta> jSonTranformer=new JSonTranformer<>();
    private static ImpBiciService service=new ImpBiciService();

    public static Bicicleta get(Request request, Response response) {
        logger.info("Request bici by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");

        response.type("application/json");
        response.status(200);
        response.body();
        return service.getBicicleta(matricula);
    }

    public static Result<Bicicleta> delete(Request request, Response res) {
        logger.info("delete Request bici by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");
        Result<Bicicleta> result = service.deleteBicicleta(matricula);
        res.type("application/json");
        res.status((result instanceof Result.Success)?200:500);
        return result;
    }

    public static Result<Bicicleta> add(Request request, Response res) {

        Bicicleta b = jSonTranformer.getObject(request.body(), Bicicleta.class);
        logger.info("add Request bici by matricula: " + b.getMatricula() );
        Result<Bicicleta> result = service.addBicicleta(b);
        res.type("application/json");
        res.status((result instanceof Result.Success)?200:500);
        return result;
    }
    public static Result<Bicicleta> update(Request request, Response res) {
        Bicicleta b = jSonTranformer.getObject(request.body(), Bicicleta.class);

        logger.info("update Request bici by matricula: " + b.getMatricula() );
        Result<Bicicleta> result = service.updateBicicleta(b);
        res.type("application/json");
        res.status((result instanceof Result.Success)?200:500);
        return result;
    }
}
