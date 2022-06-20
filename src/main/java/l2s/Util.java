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
    static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static Service getDefaultService() {return forDS(oracleDS()); }
    public static Service forDS(DataSource datasource) {
        Dao dao = JdbiUtil.getDao(datasource);
        return new Service(dao);
    }
    private static DataSource oracleDS() {
        final HikariDataSource ds = new HikariDataSource();
        final String CONFIG_FILE = System.getenv("CONFIG_FILE_URI");
        if( Objects.isNull(CONFIG_FILE)) throw new RuntimeException("Set env var for config file e.g: CONFIG_FILE_URI=/var/conf/apps/l2s/l2s_DEV.properties");
        String jdbcURL= null;
        try (final InputStream stream = Files.newInputStream(Paths.get(CONFIG_FILE))) {
            //new Dummy().getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)
            final Properties props = new Properties();
            props.load(stream);
            String host = props.getProperty("db.host");
            Integer port = Integer.valueOf(props.getProperty("db.port"));
            String sid = props.getProperty("db.sid");
            String user = props.getProperty("db.user");
            String pwd = props.getProperty("db.password");
            ds.setMaximumPoolSize(2);
            ds.setDriverClassName(ORACLE_DRIVER);
            jdbcURL = String.format( "jdbc:oracle:thin:@%s:%s/%s",host, port, sid);
            ds.setJdbcUrl(jdbcURL);
            ds.setUsername(user);
            ds.setPassword(pwd);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Oracle DS for url="+ jdbcURL+",  from file: " + CONFIG_FILE , e);
        }
        HikariConfig hc = new HikariConfig();
        hc.setDataSource(ds);
        return new HikariDataSource(hc);
    }
//    private static class Dummy {/*for prop file load*/
//    }
}
