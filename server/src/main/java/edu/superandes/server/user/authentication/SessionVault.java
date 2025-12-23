package edu.superandes.server.user.authentication;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;
import static java.util.UUID.randomUUID;

import edu.superandes.server.authentication.IllegalAuthenticationAccess;
import java.time.Instant;
import javax.jdo.PersistenceManager;

public class SessionVault {
  private final PersistenceManager pm = getPersistenceManager();

  public SessionVault() {}

  @SuppressWarnings("unchecked")
  public String enterSession(String user) {
    var activeSessionsQuery =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "SELECT s.email FROM user_session s WHERE s.email = ? AND s.session_end IS NULL")
            .setParameters(user);

    var u = activeSessionsQuery.executeResultUnique();

    if (u != null) throw new IllegalAuthenticationAccess("There is an already existing session");

    var timestamp = Instant.now();
    var token = randomUUID();
    var enterSessionQuery =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "INSERT INTO user_session(email, session_start, login_token) VALUES(?, ?, ?)")
            .setParameters(user, timestamp, token);

    enterSessionQuery.executeResultUnique();

    return token.toString();
  }

  @SuppressWarnings("unchecked")
  public void exitSession(String username) {
    var timestamp = Instant.now();
    var query =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "UPDATE user_session s SET s.session_end = ? WHERE s.email = ? AND s.session_end IS NULL")
            .setParameters(timestamp, username);

    query.executeResultUnique();
  }
}
