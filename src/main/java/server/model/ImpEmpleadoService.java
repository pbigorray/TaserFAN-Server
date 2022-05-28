package server.model;

import model.AuthenticateData;
import model.Empleado;
import model.MyDataSource;
import model.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpEmpleadoService implements IEmpleadoService {

    @Override
    public List<Empleado> getAll() {
        List<Empleado> empleadoList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getOracleDataSource();


        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from empleado")) {


            String dni, nombre, apellidos, email, cargo, pass;
            while (resultSet.next()) {
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                pass = resultSet.getString("password");
                email= resultSet.getString("email");
                cargo = resultSet.getString("cargo");

                empleadoList.add(new Empleado(dni, nombre, apellidos, email, cargo, pass));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleadoList;
    }

    @Override
    public Result<Empleado> get(String dni) {
        Empleado empleado;
        DataSource dataSource = MyDataSource.getOracleDataSource();


        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from empleado where dni='" + dni + "'")) {

            String nombre, apellidos, email, cargo, pass;
            if (resultSet.next()) {

                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                pass = resultSet.getString("password");
                email = resultSet.getString("email");
                cargo= resultSet.getString("cargo");


                empleado = new Empleado(dni, nombre, apellidos, email, cargo, pass);
                return new Result.Success<>(empleado);
            } else {
                return new Result.Error( 404,"No existe");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error( e.getErrorCode(),e.getMessage());
        }
    }

    @Override
    public boolean update(Empleado empleado) {
        return false;
    }


    @Override
    public Result<Empleado> delete(String dni) {
        DataSource ds = MyDataSource.getOracleDataSource();
        Empleado empleado = null;

        String nombre, apellidos, email, cargo, pass;
        String sql = "delete from empleado where dni='" + dni + "' returning ";
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                pass = resultSet.getString("password");
                email = resultSet.getString("email");
                cargo= resultSet.getString("cargo");


                empleado = new Empleado(dni, nombre, apellidos, email, cargo, pass);
                return new Result.Success<Empleado>(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(404,"persona a eleminar no encontrada");
        }
        return null;
    }

    @Override
    public Result<Empleado> add(Empleado empleado) {
        // por acabar
        DataSource ds = MyDataSource.getOracleDataSource();

        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();) {
            String sql = "insert into empleado values('" + empleado.getDni() + "','" + empleado.getNombre() + "','" + empleado.getApellidos() + "'," + empleado + ")";


            int count = statement.executeUpdate(sql);
            if (count == 1) {
                return new Result.Success<>(empleado);
            } else {
                return new Result.Error( 404,"No se pudo insertar");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error( e.getErrorCode(),e.getMessage());
        }

    }

    @Override
    public Result<Empleado> login(AuthenticateData ad) {
        DataSource ds = MyDataSource.getOracleDataSource();
        Empleado empleado = null;
        String email= ad.getEmail();
        String pass= ad.getPass();
        String dni,nombre,apellidos,correo,cargo, domicilio, cp, fechaNac;
        String sql="select * from empleado where email=? and password=ENCRYPT_PASWD.encrypt_val(?)";
        try (Connection con = ds.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)){

            int pos=0;
            statement.setString(++pos,email);
            statement.setString(++pos,pass);

            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()) {
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                cargo = resultSet.getString("cargo");
                domicilio = resultSet.getString("domicilio");
                cp = resultSet.getString("cp");
                fechaNac = resultSet.getString("fechaNac");

                empleado = new Empleado(dni, nombre, apellidos, email, cargo, pass);

                return new Result.Success<Empleado>(empleado);
            }else {
                return  new Result.Error(404,"Email o contraseña incorrectos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result.Error( 404,ex.getMessage());
        }
    }
}


