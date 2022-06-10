package l2s.dao.jdbi;

import l2s.dao.Dao;
import l2s.model.LandingCustomer;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.stream.Collectors;

class DaoImpl implements Dao {
    Jdbi jdbi;

    public DaoImpl(Jdbi jdbi){
        this.jdbi=jdbi;
    }
    @Override
    public List<LandingCustomer> getCustomersToProcess() {
        return jdbi.withExtension(JdbiDaoLandingCustomer.class, dao -> dao.getProcessable()).stream().collect(Collectors.toList());
    }
}
