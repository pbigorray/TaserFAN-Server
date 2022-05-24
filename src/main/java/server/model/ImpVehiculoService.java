package server.model;

import model.*;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import server.model.IEmpleadoService;

import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImpVehiculoService implements IVehiculoService {
    @Override
    public List<Vehiculo> getAll() {
        List<Vehiculo> list = new ArrayList<>();
        for (Vehiculo vehiculo : getVehiculos(Tipo.COCHE)) {
            list.add(vehiculo);
        }
        for (Vehiculo vehiculo : getVehiculos(Tipo.MOTO)) {
            list.add(vehiculo);
        }
        for (Vehiculo vehiculo : getVehiculos(Tipo.BICICLETA)) {
            list.add(vehiculo);
        }
        for (Vehiculo vehiculo : getVehiculos(Tipo.PATINETE)) {
            list.add(vehiculo);
        }

        return list;
    }

    @Override
    public List<Vehiculo> getVehiculos(Tipo tipo) {
        DataSource dataSource = MyDataSource.getOracleDataSource();
        List<Vehiculo> list = new ArrayList<>();
        ResultSet rs;

        String sql = "{call gestionvehiculos.listarvehiculos(?,?)}";
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, tipo.getStr());
            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.execute();

            rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                list.add(new Vehiculo(rs.getString("c1"), rs.getString("c2"), rs.getString("c4"), rs.getString("c7"), tipo));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehiculo> getMoto(String matricula) {
        return null;
    }

    @Override
    public List<Vehiculo> getBici(String matricula) {
        return null;
    }

    @Override
    public List<Vehiculo> getPatinete(String matricula) {
        return null;
    }

    @Override
    public List<Vehiculo> getCoche(String matricula) {
        return null;
    }



}
