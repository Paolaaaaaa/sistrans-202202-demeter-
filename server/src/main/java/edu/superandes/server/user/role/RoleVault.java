package edu.superandes.server.user.role;

import edu.superandes.server.persistence.PersistenceManagerSingleton;
import java.util.List;
import javax.jdo.PersistenceManager;

public class RoleVault {
  private final PersistenceManager pm = PersistenceManagerSingleton.getPersistenceManager();

  public RoleVault() {}

  @SuppressWarnings("unchecked")
  public List<String> list() {
    var query = pm.newQuery("javax.jdo.query.SQL", "SELECT role FROM user_role");

    var roles = query.executeResultList(String.class);
    return (List<String>) roles;
  }

  @SuppressWarnings("unchecked")
  public void add(String role) {
    var query =
        pm.newQuery("javax.jdo.query.SQL", "INSERT INTO user_role(role) VALUES(?)")
            .setParameters(role);
    query.executeResultUnique();
  }

  @SuppressWarnings("unchecked")
  public void remove(String role) {
    var query =
        pm.newQuery("javax.jdo.query.SQL", "DELETE FROM user_role WHERE role = ?")
            .setParameters(role);
    query.executeResultUnique();
  }

  @SuppressWarnings("unchecked")
  public long getIdByName(String role) {
    var roleDiscoveryQuery =
        pm.newQuery("javax.jdo.query.SQL", "SELECT r.id FROM user_role r WHERE r.role = ?")
            .setParameters(role);

    var id = roleDiscoveryQuery.executeResultUnique(long.class);

    if (id == null) throw new RuntimeException("role: " + role + " doesnt exist.");

    return (long) id;
  }
}
