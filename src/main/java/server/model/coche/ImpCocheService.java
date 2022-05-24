package server.model.coche;

import model.*;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ImpCocheService implements ICocheService{
    @Override
    public Coche getCoche(String matricula) {
        //consultarCoche
        return null;
    }

    @Override
    public Result<Coche> addCoche(Coche coche) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.insertarCoche(?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)
        ) {
            cs.setString(1, coche.getMatricula());
            cs.setDouble(2, coche.getPrecioHora());
            cs.setString(3, coche.getMarca());
            cs.setString(4, coche.getDescripcion());
            cs.setString(5, coche.getColor());
            cs.setInt(6, coche.getBateria());
            cs.setString(7, coche.getFechaAdq());
            cs.setString(8, coche.getEstado());
            cs.setString(9, coche.getTipoCarnet());
            cs.setInt(10, coche.getNumPlazas());
            cs.setInt(11, coche.getNumPuertas());


            cs.execute();

            return new Result.Sucess<Coche>(coche);
        } catch (SQLException e){
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public Result<Coche> updateCoche(Coche coche) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.actulizarCoche(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs=con.prepareCall(sql);
        ) {

            cs.setString(1, coche.getMatricula());
            cs.setDouble(2, coche.getPrecioHora());
            cs.setString(3, coche.getMarca());
            cs.setString(4, coche.getDescripcion());
            cs.setString(5, coche.getColor());
            cs.setInt(6, coche.getBateria());
            cs.setString(7, coche.getFechaAdq());
            cs.setString(8, coche.getEstado());
            cs.setString(9, coche.getTipoCarnet());
            cs.setInt(10, coche.getNumPlazas());
            cs.setInt(11, coche.getNumPuertas());

            cs.execute();
            return new Result.Sucess<Coche>(coche);

        } catch (SQLException e) {
            return new Result.Error(e.getErrorCode(),e.getMessage());
        }
    }

    @Override
    public Result<Coche> deleteCoche(String matricula) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.eliminarCoche(?)}";


        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);

            cs.execute();

            return new Result.Sucess<Vehiculo>(new Vehiculo(matricula,null,null,null, Tipo.COCHE));
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(404,e.getMessage());
        }
    }
}
