package edu.superandes.server.user.registration;

import edu.superandes.server.authentication.api.ClientRegistrationData;
import edu.superandes.server.authentication.api.EmployeeRegistrationData;
import edu.superandes.server.authentication.api.UserRegistrationData;

public record RegistrationData(
    String email, String password, String role, Long branchOfficeId, Long supermarketId) {
  public RegistrationData(ClientRegistrationData clientRegistrationData) {
    this(
        clientRegistrationData.userData(), "CLIENT", null, clientRegistrationData.branchOfficeId());
  }

  public RegistrationData(
      UserRegistrationData userRegistrationData,
      String role,
      Long branchOfficeId,
      Long supermarketId) {
    this(
        userRegistrationData.email(),
        userRegistrationData.password(),
        role,
        branchOfficeId,
        supermarketId);
  }

  public RegistrationData(EmployeeRegistrationData employeeRegistrationData) {
    this(
        employeeRegistrationData.userData(),
        employeeRegistrationData.role(),
        employeeRegistrationData.branchOfficeId(),
        null);
  }
}
