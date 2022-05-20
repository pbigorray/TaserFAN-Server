package model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelOracle {
    public List<Empleado> getEmpleado() {
        List<Empleado> empleados = new ArrayList<>();
        DataSource dataSource = MyDataSource.getOracleDataSource();


        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from empleado")) {


            String dni;
            String nombre;
            String apellidos;
            String email;
            String cargo;
            String pass;
            while (resultSet.next()) {
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                email = resultSet.getString("email");
                cargo = resultSet.getString("cargo");
                pass = resultSet.getString("password");
                empleados.add(new Empleado(dni, nombre, apellidos, email, cargo,pass));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public boolean inicioSesion1(String email, String password) {
        DataSource ds = MyDataSource.getOracleDataSource();

        boolean auth=false;
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(*) from empleado where email='" + email + "' and password=ENCRYPT_PASWD.encrypt_val('"+password+"')")){

            resultSet.next();

            int count=resultSet.getInt(1);
            auth=(count==0)?false:true;

            } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return auth;
    }
    public Empleado inicioSesion2(String email, String password) {
        DataSource ds = MyDataSource.getOracleDataSource();
        Empleado empleado=null;
        try (Connection con = ds.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from empleado where email='" + email + "' and password=ENCRYPT_PASWD.encrypt_val('"+password+"')")){

            String dni;
            String nombre;
            String apellidos;
            String correo;
            String cargo;
            String pass;
            String domicilio,cp,fechaNac;
            resultSet.next();
            dni = resultSet.getString("dni");
            nombre = resultSet.getString("nombre");
            apellidos = resultSet.getString("apellidos");
            correo = resultSet.getString("email");
            domicilio = resultSet.getString("domicilio");
            cp = resultSet.getString("cp");
            fechaNac = resultSet.getString("fechaNac");

            cargo = resultSet.getString("cargo");
            pass = resultSet.getString("password");

            empleado=new Empleado(dni,nombre,apellidos,correo,cargo,pass);
//            int count=resultSet.getInt(1);
//            auth=(count==0)?false:true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empleado;
    }
}
