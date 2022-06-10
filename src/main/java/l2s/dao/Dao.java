package l2s.dao;

import l2s.model.LandingCustomer;

import java.util.List;

public interface Dao {
    List<LandingCustomer> getCustomersToProcess();
}
