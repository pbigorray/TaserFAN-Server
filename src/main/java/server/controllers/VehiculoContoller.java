package server.controllers;

import model.DeletingVehiculo;
import model.Result;
import model.Tipo;
import model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.IEmpleadoService;
import server.model.ImpVehiculoService;
import server.model.JSonTranformer;
import spark.Request;
import spark.Response;

import java.util.List;

public class VehiculoContoller {
    static Logger logger= LoggerFactory.getLogger(VehiculoContoller.class);

    private static ImpVehiculoService service=new ImpVehiculoService();
    private static JSonTranformer<Vehiculo> jSonTranformer=new JSonTranformer<>();
    private static JSonTranformer<Tipo> uwu=new JSonTranformer<>();



    public static List<Vehiculo> getVehiculos(Request req, Response res){
        logger.info("reciving request for all vehiculos");
        return service.getAll();
    }
    public static List<Vehiculo> getTipo(Request req, Response res){
//       String tipo=req.queryParams("tipo");
          String body=req.body();


        Tipo tipo=uwu.getObject(body,Tipo.class);
        logger.info("get tipo vehiculo="+tipo);
        List<Vehiculo> list= service.getVehiculos(tipo);

        return list;
    }

//
//    public static Result<Vehiculo> addVehiculo(Request request, Response res) {
//        logger.info("Add new vehiculo");
//        String body=request.body();
//        Vehiculo v = jSonTranformer.getObject(body,Vehiculo.class);
//        Result<Vehiculo> result=service.add(v);
//        if (result instanceof Result.Sucess){
//            res.status(200);
//        }else {
//            res.status(4);
//        }
//        return result;
//    }
//
//    public static Result<Vehiculo> deleteVehiculo(Request req, Response res) {
//
//        logger.info("Delete vehiculo");
//        String matricula=req.queryParams("matricula");
//        Result<Vehiculo> result=service.delete(matricula);
//        if (result instanceof Result.Sucess){
//            res.status(200);
//        }else {
//            res.status(4);
//        }
//        return result;
//    }

}
