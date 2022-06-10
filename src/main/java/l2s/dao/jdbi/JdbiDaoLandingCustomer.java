package l2s.dao.jdbi;

import l2s.model.LandingCustomer;
import l2s.model.LandingEntity;
import org.jdbi.v3.core.transaction.TransactionIsolationLevel;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

interface JdbiDaoLandingCustomer {
    @SqlQuery("select_top_x_processable")
    @UseClasspathSqlLocator(stripComments = true)
    @RegisterRowMapper(value = RowMapperLandingCustomer.class)
    @Transaction(TransactionIsolationLevel.READ_COMMITTED)
    List<LandingCustomer> getProcessable();
}
