package edu.superandes.server.user.registration;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import com.google.inject.Inject;
import edu.superandes.server.user.UserVault;
import edu.superandes.server.user.role.RoleVault;
import javax.jdo.PersistenceManager;

public class UserRegistrationVault {
  private final PersistenceManager pm = getPersistenceManager();
  private final RoleVault roleVault;
  private final UserVault userVault;

  @Inject
  public UserRegistrationVault(RoleVault roleVault, UserVault userVault) {
    this.roleVault = roleVault;
    this.userVault = userVault;
  }

  public void register(RegistrationData registrationData) {
    var roleId = roleVault.getIdByName(registrationData.role());

    var insertUserQuery =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "INSERT INTO user (email, role_id, password) VALUES ( ? , ? , ?)")
            .setParameters(registrationData.email(), roleId, registrationData.password());

    var r = insertUserQuery.executeResultUnique();

    if (r == null) throw new RuntimeException("Failed to register user");

    var id = userVault.getIdByEmail(registrationData.email());
    if (registrationData.role().equals("CLIENT")) {
      var insertClientQuery =
          pm.newQuery(
                  "javax.jdo.query.SQL",
                  "INSERT INTO client (user_id, supermarket_id) VALUES (?, ?)")
              .setParameters(id, registrationData.supermarketId());

      insertClientQuery.executeResultUnique();
    } else {
      var insertEmployeeQuery =
          pm.newQuery(
                  "javax.jdo.query.SQL",
                  "INSERT INTO employee (user_id, branch_office_id) VALUES (?, ?)")
              .setParameters(id, registrationData.branchOfficeId());

      insertEmployeeQuery.executeResultUnique();
    }
  }

  public void unRegister() {}
}
