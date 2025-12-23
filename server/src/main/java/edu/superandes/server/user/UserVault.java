package edu.superandes.server.user;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import java.util.List;
import javax.jdo.PersistenceManager;

public class UserVault {
  private final PersistenceManager pm = getPersistenceManager();

  public UserVault() {}

  @SuppressWarnings("unchecked")
  public long getIdByEmail(String email) {
    var query = pm.newQuery("javax.jdo.query.SQL", "SELECT u.id FROM user u");

    var id = query.executeResultUnique(long.class);

    if (id == null) throw new RuntimeException("User doesnt exist.");

    return (long) id;
  }

  @SuppressWarnings("unchecked")
  public List<User> list() {
    var query =
        pm.newQuery(
            "javax.jdo.query.SQL", "SELECT email, role_id, password, branch_office_id FROM user");

    var users = query.executeResultList(User.class);
    return (List<User>) users;
  }

  @SuppressWarnings("unchecked")
  public boolean hasRole(String role, String email) {
    var query =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "SELECT r.role FROM user u JOIN user_role r ON u.role_id = r.id WHERE r.role = ? AND u.email = ?")
            .setParameters(role, email);

    var user = query.executeResultUnique();

    return user != null;
  }
}
