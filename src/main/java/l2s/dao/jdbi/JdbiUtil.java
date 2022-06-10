package l2s.dao.jdbi;

import l2s.dao.Dao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;

public class JdbiUtil {
    public static Dao getDao(DataSource ds) {
        //if (!type.isInterface()) throw new RuntimeException("Only interface type expected");
        final Jdbi jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin())//common mappers
                .registerRowMapper(new RowMapperLandingCustomer());
        return new DaoImpl(jdbi);
    }
}
