package server.model.coche;

import model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ImpCocheService implements ICocheService{
    @Override
    public Coche getCoche(String matricula) {
        //consultarCoche
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.consultarCoche(?,?,?,?,?,?,?,?,?,?,?)}";
        ResultSet rs;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");



        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            String marca,descripcion,color,estado;
            String fechaAdq;
            int bateria;
            String tipoCarnet;
            double precioHora;
            int numPlazas,numPuertas;

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
            numPlazas=cs.getInt(10);
            numPuertas=cs.getInt(11);

            return new Coche(matricula, marca, descripcion, bateria,  tipoCarnet,  color,  estado,  fechaAdq,  precioHora,  Tipo.COCHE,  numPlazas,  numPuertas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            return new Result.Success<Coche>(coche);
        } catch (SQLException e){
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public Result<Coche> updateCoche(Coche coche) {
        DataSource ds = MyDataSource.getOracleDataSource();
        String sql = "{call gestionvehiculos.actulizarCoche(?,?,?,?,?,?,?,?,?,?,?)}";
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
            return new Result.Success<Coche>(coche);

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

            return new Result.Success<Vehiculo>(new Vehiculo(matricula,null,null,null, Tipo.COCHE));
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(404,e.getMessage());
        }
    }
}
