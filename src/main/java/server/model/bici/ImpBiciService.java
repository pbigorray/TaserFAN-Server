package server.model.bici;

import model.*;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ImpBiciService implements IBiciService {

    @Override
    public Bicicleta getBicicleta(String matricula) {
        //consultarBici
        return null;
    }

    @Override
    public Result<Bicicleta> addBicicleta(Bicicleta bicicleta) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.insertarBici(?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)
        ) {
            cs.setString(1, bicicleta.getMatricula());
            cs.setDouble(2, bicicleta.getPrecioHora());
            cs.setString(3, bicicleta.getMarca());
            cs.setString(4, bicicleta.getDescripcion());
            cs.setString(5, bicicleta.getColor());
            cs.setInt(6, bicicleta.getBateria());
            cs.setString(7, bicicleta.getFechaAdq());
            cs.setString(8, bicicleta.getEstado());
            cs.setString(9, bicicleta.getTipoCarnet());
            cs.setString(10, bicicleta.getTipo());


            cs.execute();

            return new Result.Sucess<Bicicleta>(bicicleta);
        } catch (SQLException e){
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }

    }

    @Override
    public Result<Bicicleta> updateBicicleta(Bicicleta bicicleta) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.actulizarBici(?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs=con.prepareCall(sql);
        ) {

            cs.setString(1, bicicleta.getMatricula());
            cs.setDouble(2, bicicleta.getPrecioHora());
            cs.setString(3, bicicleta.getMarca());
            cs.setString(4, bicicleta.getDescripcion());
            cs.setString(5, bicicleta.getColor());
            cs.setInt(6, bicicleta.getBateria());
            cs.setString(7, bicicleta.getFechaAdq());
            cs.setString(8, bicicleta.getEstado());
            cs.setString(9, bicicleta.getTipoCarnet());
            cs.setString(10, bicicleta.getTipo());


            cs.execute();
            return new Result.Sucess<Bicicleta>(bicicleta);

        } catch (SQLException e) {
            return new Result.Error(e.getErrorCode(),e.getMessage());
        }
    }

    @Override
    public Result<Bicicleta> deleteBicicleta(String matricula) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.eliminarBici(?)}";


        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);

            cs.execute();

            return new Result.Sucess<Vehiculo>(new Vehiculo(matricula,null,null,null, Tipo.BICICLETA));
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(404,e.getMessage());
        }
    }
}
