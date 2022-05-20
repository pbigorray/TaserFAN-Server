package server.controllers;

import model.AuthenticateData;
import model.Empleado;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.*;
import spark.Request;
import spark.Response;

import java.util.List;

public class EmpleadoController  {
    static Logger logger= LoggerFactory.getLogger(EmpleadoController.class);

    private static IEmpleadoService service=new ImpEmpleadoService();
    private static JSonTranformer<Empleado> jSonTranformer=new JSonTranformer<>();

    public static List<Empleado> getEmpleados(Request req, Response res){
        logger.info("reciving request for all persons");
        return service.getAll();
    }
    public static Result<Empleado> getEmpleado(Request req, Response res){
        String dni=req.queryParams("dni");
        logger.info("get person dni="+dni);

        Result result= service.get(dni);
        if (result instanceof Result.Sucess){
            res.status(200);
        }else {
            res.status(4);
        }
        return result;
    }

    public static Result<Empleado> addEmpleado(Request request, Response res) {
        logger.info("Add new person");
        String body=request.body();
        Empleado e = jSonTranformer.getObject(body,Empleado.class);
        Result<Empleado> result=service.add(e);
        if (result instanceof Result.Sucess){
            res.status(200);
        }else {
            res.status(4);
        }
        return result;
    }

    public static Result<Empleado> deleteEmpleado(Request req, Response res) {

        logger.info("Delete empleado");
        String dni=req.queryParams("dni");
        Result<Empleado> result=service.delete(dni);
        if (result instanceof Result.Sucess){
            res.status(200);
        }else {
            res.status(4);
        }
        return result;
    }
    public static Result<Empleado> login(Request req, Response res){
        logger.info("Inicio de sesion de un usuario");
        String body=req.body();
        JSonTranformer<AuthenticateData>jst=new JSonTranformer<>();
        AuthenticateData ad = jst.getObject(body,AuthenticateData.class);
        Result<Empleado> result=service.login(ad);

        if (result instanceof Result.Sucess){
            res.status(200);
        }else {
            res.status(404);
        }
        return result;
    }
}
