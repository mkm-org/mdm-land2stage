package l2s;

import l2s.dao.Dao;
import l2s.model.LandingCustomer;

import java.util.List;

class Service {
    Dao dao;
    public Service(Dao dao){
        this.dao = dao;
    }
    public void processCustomers(){
      List<LandingCustomer> out=  dao.getCustomersToProcess();
      out.stream().forEach(c -> System.out.println(c));
    }
}
