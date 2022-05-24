package server.model.bici;

import model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ImpBiciService implements IBiciService {

    @Override
    public Bicicleta getBicicleta(String matricula) {
        //consultarBici
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.consultarBici(?,?,?,?,?,?,?,?,?,?)}";
        ResultSet rs;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            String marca,descripcion,color,estado;
            String fechaAdq;
            int bateria;
            String tipoCarnet,tipoBici;
            double precioHora;

            cs.setString(1, matricula);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.NUMERIC);
            cs.registerOutParameter(7, Types.DATE);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);

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
            tipoBici=cs.getString(10);


            return new Bicicleta(matricula, marca, descripcion, bateria,  tipoCarnet,  color,  estado,  fechaAdq,  precioHora,  Tipo.BICICLETA,  tipoBici);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            cs.setString(10, bicicleta.getTipoBici());


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
            cs.setString(10, bicicleta.getTipoBici());


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
