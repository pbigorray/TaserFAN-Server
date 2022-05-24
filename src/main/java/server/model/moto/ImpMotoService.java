package server.model.moto;

import model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ImpMotoService implements IMotoService {


    @Override
    public Moto getMoto(String matricula) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.consultarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        ResultSet rs;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");



        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            String marca,descripcion,color,estado;
            String fechaAdq;
            int bateria;
            String tipoCarnet;
            double precioHora;
            int velocidadMax,cilindrada;

            cs.setString(1, matricula);
            cs.registerOutParameter(2,Types.DECIMAL);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.NUMERIC);
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
            velocidadMax=cs.getInt(10);
            cilindrada=cs.getInt(11);

            return new Moto(matricula, marca, descripcion, bateria,  tipoCarnet,  color,  estado,  fechaAdq,  precioHora,  Tipo.MOTO,  velocidadMax,  cilindrada);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<Moto> updateMoto(Moto moto) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.actulizarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs=con.prepareCall(sql);
        ) {

            cs.setString(1, moto.getMatricula());
            cs.setDouble(2, moto.getPrecioHora());
            cs.setString(3, moto.getMarca());
            cs.setString(4, moto.getDescripcion());
            cs.setString(5, moto.getColor());
            cs.setInt(6, moto.getBateria());
            cs.setString(7, moto.getFechaAdq());
            cs.setString(8, moto.getEstado());
            cs.setString(9, moto.getTipoCarnet());
            cs.setInt(10, moto.getVelocidadMax());
            cs.setInt(11, moto.getCilindrada());

            cs.execute();
            return new Result.Sucess<Moto>(moto);

        } catch (SQLException e) {
            return new Result.Error(e.getErrorCode(),e.getMessage());
        }

    }

    @Override
    public Result<Moto> deleteMoto(String matricula) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.eliminarMoto(?)}";


        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);


            cs.execute();

            return new Result.Sucess<Vehiculo>(new Vehiculo(matricula,null,null,null, Tipo.MOTO));
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(404,e.getMessage());
        }
    }

    @Override
    public Result<Moto> addMoto(Moto moto) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.insertarMoto(?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)
        ) {
            cs.setString(1, moto.getMatricula());
            cs.setDouble(2, moto.getPrecioHora());
            cs.setString(3, moto.getMarca());
            cs.setString(4, moto.getDescripcion());
            cs.setString(5, moto.getColor());
            cs.setInt(6, moto.getBateria());

            cs.setString(7, moto.getFechaAdq());
            cs.setString(8, moto.getEstado());
            cs.setString(9, moto.getTipoCarnet());
            cs.setInt(10, moto.getVelocidadMax());
            cs.setInt(11, moto.getCilindrada());

            cs.execute();

            return new Result.Sucess<Moto>(moto);
        } catch (SQLException e){
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }
}
