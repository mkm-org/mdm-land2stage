package l2s;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import l2s.dao.Dao;
import l2s.dao.jdbi.JdbiUtil;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;


class Util {
    public static Service getDefaultService() {return forDS(oracleDS()); }
    public static Service forDS(DataSource datasource) {
        Dao dao = JdbiUtil.getDao(datasource);
        Service service = new Service(dao);
        return service;
    }
    private static DataSource oracleDS() {
        final Properties props = new Properties();
        final HikariDataSource ds = new HikariDataSource();
        //CONFIG_FILE_URI=/var/conf/apps/l2s/l2s_DEV.properties
        final String CONFIG_FILE = System.getenv("CONFIG_FILE_URI");
        if( Objects.isNull(CONFIG_FILE)) throw new RuntimeException("Set env var for config file e.g: CONFIG_FILE_URI=/var/conf/apps/l2s/l2s_DEV.properties");
        String jdbcURL= null;
        try (final InputStream stream = Files.newInputStream(Paths.get(CONFIG_FILE))
             ) {
            //new Dummy().getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)
            props.load(stream);
            String host = props.getProperty("db.host");
            Integer port = Integer.valueOf(props.getProperty("db.port"));
            String sid = props.getProperty("db.sid");
            String user = props.getProperty("db.user");
            String pwd = props.getProperty("db.password");
            Integer poolSize = Integer.valueOf(props.getProperty("db.poolsize"));
            //
            ds.setMaximumPoolSize(2);
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            jdbcURL = "jdbc:oracle:thin:@"+host+":"+port+"/"+ sid;
            ds.setJdbcUrl(jdbcURL); ;
            ds.setUsername(user);
            ds.setPassword(pwd);
            ds.setMaximumPoolSize(poolSize);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Oracle DS for url="+ jdbcURL+",  from file: " + CONFIG_FILE , e);
        }
        HikariConfig hc = new HikariConfig();
        hc.setDataSource(ds);
        HikariDataSource hdc = new HikariDataSource(hc);
        return hdc;
    }
    private static class Dummy {/*for prop file load*/
    }
}
