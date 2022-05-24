package server;


import server.controllers.*;

import server.model.JSonTranformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        post(API.Routes.AUTHENTICATE, EmpleadoController::login,new JSonTranformer<>());
        get(API.Routes.ALL_PERSON,EmpleadoController::getEmpleados,new JSonTranformer<>());

        get(API.Routes.VEHICULOS, VehiculoContoller::getVehiculos,new JSonTranformer<>());
        post(API.Routes.TIPO_VEHICULO, VehiculoContoller::getTipo,new JSonTranformer<String>());

        post(API.Routes.MOTO,MotoController::add,new JSonTranformer<>());
        put(API.Routes.MOTO,MotoController::update,new JSonTranformer<>());
        get(API.Routes.MOTO, MotoController::get,new JSonTranformer<>());
        delete(API.Routes.MOTO, MotoController::delete,new JSonTranformer<>());

        post(API.Routes.BICI, BiciController::add,new JSonTranformer<>());
        put(API.Routes.BICI, BiciController::update,new JSonTranformer<>());
        get(API.Routes.BICI, BiciController::get,new JSonTranformer<>());
        delete(API.Routes.BICI, BiciController::delete,new JSonTranformer<>());

        post(API.Routes.COCHE, CocheController::add,new JSonTranformer<>());
        put(API.Routes.COCHE, CocheController::update,new JSonTranformer<>());
        get(API.Routes.COCHE, CocheController::get,new JSonTranformer<>());
        delete(API.Routes.COCHE, CocheController::delete,new JSonTranformer<>());

        post(API.Routes.PATINETE, PatineteController::add,new JSonTranformer<>());
        put(API.Routes.PATINETE, PatineteController::update,new JSonTranformer<>());
        get(API.Routes.PATINETE, PatineteController::get,new JSonTranformer<>());
        delete(API.Routes.PATINETE, PatineteController::delete,new JSonTranformer<>());

    }

}

