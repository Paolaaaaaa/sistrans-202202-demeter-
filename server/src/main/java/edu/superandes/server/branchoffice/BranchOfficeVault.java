package edu.superandes.server.branchoffice;

import edu.superandes.server.persistence.PersistenceManagerSingleton;
import java.util.List;
import javax.jdo.PersistenceManager;

public class BranchOfficeVault {
  private final PersistenceManager pm = PersistenceManagerSingleton.getPersistenceManager();

  public BranchOfficeVault() {}

  @SuppressWarnings("unchecked")
  public List<BranchOffice> list() {
    var query =
        pm.newQuery(
            "javax.jdo.query.SQL",
            "SELECT id, name, city, address, supermarket_id FROM branch_office");

    var branch_offices = query.executeResultList(BranchOffice.class);
    return (List<BranchOffice>) branch_offices;
  }

  @SuppressWarnings("unchecked")
  public void add(BranchOffice branchOffice) {
    var query =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "INSERT INTO branch_office(name, city, address, supermarket_id) VALUES(?, ?, ?, ?)")
            .setParameters(
                branchOffice.getName(),
                branchOffice.getCity(),
                branchOffice.getAddress(),
                branchOffice.getSupermarketId());
    query.executeResultUnique();
  }

  @SuppressWarnings("unchecked")
  public void remove(long id) {
    var query =
        pm.newQuery("javax.jdo.query.SQL", "DELETE FROM branch_office WHERE id = ?")
            .setParameters(id);
    query.executeResultUnique();
  }
}
