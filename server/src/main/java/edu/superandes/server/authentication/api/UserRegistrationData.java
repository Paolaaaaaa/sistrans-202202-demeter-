package edu.superandes.server.authentication.api;

import java.util.Objects;

public record UserRegistrationData(String email, String password) {
  public UserRegistrationData {
    Objects.requireNonNull(email);
    Objects.requireNonNull(password);
  }
}
