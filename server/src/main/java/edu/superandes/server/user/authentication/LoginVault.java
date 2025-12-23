package edu.superandes.server.user.authentication;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import com.google.inject.Inject;
import javax.jdo.PersistenceManager;

public class LoginVault {
  private final PersistenceManager pm = getPersistenceManager();
  private final SessionVault sessionVault;

  @Inject
  public LoginVault(SessionVault sessionVault) {
    this.sessionVault = sessionVault;
  }

  @SuppressWarnings("unchecked")
  public String login(String username, String password) {
    var query =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "SELECT u.email FROM user u auth WHERE u.email = ? AND u.password = ?")
            .setParameters(username, password);

    var user = (String) query.executeResultUnique(String.class);

    if (user == null) return null;

    var sessionToken = sessionVault.enterSession(user);

    return sessionToken;
  }

  public void logout(String username, String loginToken) {
    sessionVault.exitSession(username);
  }
}
