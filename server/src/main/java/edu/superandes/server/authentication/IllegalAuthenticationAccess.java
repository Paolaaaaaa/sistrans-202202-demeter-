package edu.superandes.server.authentication;

public class IllegalAuthenticationAccess extends RuntimeException {
  public IllegalAuthenticationAccess(String message) {
    super(message);
  }

  public IllegalAuthenticationAccess() {
    super("Unauthorized access");
  }
}
