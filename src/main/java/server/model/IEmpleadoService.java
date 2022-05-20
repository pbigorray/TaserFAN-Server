package server.model;

import model.AuthenticateData;
import model.Empleado;
import model.Result;

import java.util.List;

public interface IEmpleadoService {
    List<Empleado> getAll();
    Result<Empleado> get(String dni);
    boolean update(Empleado empleado);
    Result<Empleado> delete(String dni);
    Result<Empleado> add(Empleado empleado);
    Result<Empleado> login(AuthenticateData ad);
}
