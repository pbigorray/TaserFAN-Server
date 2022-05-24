package server.model.patinete;

import model.*;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ImpPatineteService implements IPateneteService{
    @Override
    public Patinete getPatinete(String matricula) {
        //consultarPatinete
        return null;
    }

    @Override
    public Result<Patinete> addPatinete(Patinete patinete) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.insertarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)
        ) {
            cs.setString(1, patinete.getMatricula());
            cs.setDouble(2, patinete.getPrecioHora());
            cs.setString(3, patinete.getMarca());
            cs.setString(4, patinete.getDescripcion());
            cs.setString(5, patinete.getColor());
            cs.setInt(6, patinete.getBateria());
            cs.setString(7, patinete.getFechaAdq());
            cs.setString(8, patinete.getEstado());
            cs.setString(9, patinete.getTipoCarnet());
            cs.setInt(10, patinete.getNumRuedas());
            cs.setInt(11, patinete.getTamanyo());


            cs.execute();

            return new Result.Sucess<Patinete>(patinete);
        } catch (SQLException e){
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public Result<Patinete> updatePatinete(Patinete patinete) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.actulizarPatinete(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs=con.prepareCall(sql);
        ) {

            cs.setString(1, patinete.getMatricula());
            cs.setDouble(2, patinete.getPrecioHora());
            cs.setString(3, patinete.getMarca());
            cs.setString(4, patinete.getDescripcion());
            cs.setString(5, patinete.getColor());
            cs.setInt(6, patinete.getBateria());
            cs.setString(7, patinete.getFechaAdq());
            cs.setString(8, patinete.getEstado());
            cs.setString(9, patinete.getTipoCarnet());
            cs.setInt(10, patinete.getNumRuedas());
            cs.setInt(11, patinete.getTamanyo());

            cs.execute();
            return new Result.Sucess<Patinete>(patinete);

        } catch (SQLException e) {
            return new Result.Error(e.getErrorCode(),e.getMessage());
        }
    }

    @Override
    public Result<Patinete> deletePatinete(String matricula) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.eliminarPatinete(?)}";


        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);

            cs.execute();

            return new Result.Sucess<Vehiculo>(new Vehiculo(matricula,null,null,null, Tipo.PATINETE));
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(404,e.getMessage());
        }
    }

}
