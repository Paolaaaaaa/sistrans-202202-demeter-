package edu.superandes.server.authentication.api;

import java.util.Objects;

public record EmployeeRegistrationData(
    UserRegistrationData userData, String role, Long branchOfficeId) {
  public EmployeeRegistrationData {
    Objects.requireNonNull(userData);
    Objects.requireNonNull(role);
    Objects.requireNonNull(branchOfficeId);
  }
}
