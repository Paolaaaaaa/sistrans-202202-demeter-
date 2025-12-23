package edu.superandes.server.authentication.api;

import java.util.Objects;

public record Session(Connection connection, String username, String loginToken) {
  /**
   * @param connection
   * @param username
   * @param loginToken valid login token
   * @throws NullPointerException
   */
  public Session {
    Objects.requireNonNull(connection);
    Objects.requireNonNull(username);
    Objects.requireNonNull(loginToken);
  }
}
