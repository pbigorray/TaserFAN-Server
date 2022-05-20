package model;


import oracle.jdbc.datasource.impl.OracleDataSource;
import properties.MyConfig;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataSource {

    public static DataSource getOracleDataSource() {


        String user = MyConfig.getInstance().getOracleUser();
        String pass =MyConfig.getInstance().getOraclePass();
        String url=MyConfig.getInstance().getOracleUrl();

        Properties props = new Properties();

        OracleDataSource oracleDS = null;
        try (FileInputStream fis = new FileInputStream("default.properties")) {

            props.load(fis);

            oracleDS = new OracleDataSource();
            oracleDS.setURL(url);
            oracleDS.setUser(user);
            oracleDS.setPassword(pass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oracleDS;
    }
}
