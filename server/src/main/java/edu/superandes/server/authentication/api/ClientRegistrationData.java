package edu.superandes.server.authentication.api;

import java.util.Objects;

public record ClientRegistrationData(UserRegistrationData userData, Long branchOfficeId) {
  public ClientRegistrationData {
    Objects.requireNonNull(userData);
    Objects.requireNonNull(branchOfficeId);
  }
}
