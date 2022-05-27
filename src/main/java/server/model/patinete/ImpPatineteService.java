package server.model.patinete;

import model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ImpPatineteService implements IPatineteService {
    @Override
    public Patinete getPatinete(String matricula) {
        //consultarPatinete

        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.consultarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";
        ResultSet rs;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");



        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            String marca,descripcion,color,estado;
            String fechaAdq;
            int bateria;
            String tipoCarnet;
            double precioHora;
            int numRuedas,tamanyo;

            cs.setString(1, matricula);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.NUMERIC);
            cs.registerOutParameter(7, Types.DATE);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.NUMERIC);
            cs.registerOutParameter(11, Types.NUMERIC);



            cs.execute();


            precioHora=cs.getDouble(2);
            marca=cs.getString(3);
            descripcion=cs.getString(4);
            color=cs.getString(5);
            bateria=cs.getInt(6);
            String formetdate=simpleDateFormat.format(cs.getObject(7));
            fechaAdq=formetdate;
            estado=cs.getString(8);
            tipoCarnet=cs.getString(9);
            numRuedas=cs.getInt(10);
            tamanyo=cs.getInt(11);

            return new Patinete(matricula, marca, descripcion, bateria,  tipoCarnet,  color,  estado,  fechaAdq,  precioHora,  Tipo.PATINETE,  numRuedas,  tamanyo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String sql = "{call gestionvehiculos.actulizarPatinete(?,?,?,?,?,?,?,?,?,?,?)}";
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
