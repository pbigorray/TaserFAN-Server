package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

    private static MyConfig instance;
    private final String DEFAULT_PROPERTIES = "default.properties";
    private final String CUSTOM_PROPERTIES = "custom.properties";
    private Properties appProperties;

    private MyConfig(){
        Properties defaultProperties=new Properties();

        try(FileInputStream fis=new FileInputStream(DEFAULT_PROPERTIES)) {

            defaultProperties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        appProperties=new Properties(defaultProperties);

        try(FileInputStream fis=new FileInputStream(CUSTOM_PROPERTIES)){
            appProperties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void  save(){
        try(FileOutputStream fos = new FileOutputStream(CUSTOM_PROPERTIES)) {
            appProperties.store(fos,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void  saveXML(){
        try(FileOutputStream fos = new FileOutputStream(CUSTOM_PROPERTIES)) {
            appProperties.storeToXML(fos,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static MyConfig getInstance(){
        if (instance==null)
            instance=new MyConfig();
        return instance;
    }

    public String getUsername(){
        return appProperties.getProperty("USERNAME");
    }
//    public void setUsername(String username){
//        appProperties.setProperty("USERNAME",username);
//    }
    public  String getPassword(){
        return appProperties.getProperty("PASSWORD");
    }
//    DB_HOST=localhost
//            DB_PORT=3306
//    DB_SCHEMA=api_test
    public String getDBHost(){
        return appProperties.getProperty("DB_HOST");
    }

    public String getDBPort(){
        return appProperties.getProperty("DB_PORT");
    }
    public String getDBSchema(){
        return appProperties.getProperty("DB_SCHEMA");
    }
    public String getOracleUser(){
        return appProperties.getProperty("ORACLE_USER");
    }
    public String getOraclePass(){
        return appProperties.getProperty("ORACLE_PASS");
    }
    public String getOracleUrl(){
        return appProperties.getProperty("ORACLE_URL");
    }
}
