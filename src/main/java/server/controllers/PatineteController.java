package server.controllers;

import model.Patinete;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.JSonTranformer;
import server.model.patinete.ImpPatineteService;
import spark.Request;
import spark.Response;

public class PatineteController {
    static Logger logger= LoggerFactory.getLogger(MotoController.class);
    private static JSonTranformer<Patinete> jSonTranformer=new JSonTranformer<>();
    private static ImpPatineteService service=new ImpPatineteService();

    public static Patinete get(Request request, Response response) {
        logger.info("Request Patinete by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");
        response.type("application/json");
        response.status(200);
        response.body();
        return service.getPatinete(matricula);

    }

    public static Result<Patinete> delete(Request request, Response res) {
        logger.info("delete Request Patinete by matricula: " + request.queryParams("matricula") );
        String matricula = request.queryParams("matricula");
        Result<Patinete> result = service.deletePatinete(matricula);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }

    public static Result<Patinete> add(Request request, Response res) {
        Patinete m = jSonTranformer.getObject(request.body(), Patinete.class);
        logger.info("add Request Patinete by matricula: " + m.getMatricula() );
        Result<Patinete> result = service.addPatinete(m);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }
    public static Result<Patinete> update(Request request, Response res) {
        Patinete c = jSonTranformer.getObject(request.body(), Patinete.class);
        logger.info("update Request Patinete by matricula: " + c.getMatricula() );
        Result<Patinete> result = service.updatePatinete(c);
        res.type("application/json");
        res.status((result instanceof Result.Sucess)?200:500);
        return result;
    }
}
