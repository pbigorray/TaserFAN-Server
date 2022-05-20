package server;


import server.controllers.EmpleadoController;

import server.controllers.VehiculoContoller;
import server.model.JSonTranformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        post(API.Routes.AUTHENTICATE, EmpleadoController::login,new JSonTranformer<>());
        get(API.Routes.ALL_PERSON,EmpleadoController::getEmpleados,new JSonTranformer<>());



        get(API.Routes.VEHICULOS, VehiculoContoller::getVehiculos,new JSonTranformer<>());
        post(API.Routes.TIPO_VEHICULO, VehiculoContoller::getTipo,new JSonTranformer<String>());
    }

}

