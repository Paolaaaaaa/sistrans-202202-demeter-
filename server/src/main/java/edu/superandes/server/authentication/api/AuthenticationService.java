package edu.superandes.server.authentication.api;

import com.google.inject.Inject;
import edu.superandes.server.branchoffice.authentication.BranchOfficeActivationService;
import edu.superandes.server.user.authentication.LoginService;
import edu.superandes.server.user.registration.RegistrationData;
import edu.superandes.server.user.registration.UserRegistrationVault;

/**
 * {@summary Server API for authentication}
 *
 * @author Orion
 */
public class AuthenticationService {
  private final BranchOfficeActivationService activationService;
  private final LoginService loginService;

  private final UserRegistrationVault registrationVault;

  /**
   * @param activatorService activation service implementation
   * @param loginService login service implementation
   */
  @Inject
  public AuthenticationService(
      BranchOfficeActivationService activatorService,
      LoginService loginService,
      UserRegistrationVault registrationVault) {
    this.activationService = activatorService;
    this.loginService = loginService;
    this.registrationVault = registrationVault;
  }

  /**
   * @param activationCode activation code
   * @return an activation token
   */
  public String activate(String activationCode) {
    if (activationCode == null) return null;

    var activationToken = activationService.validateActivationCode(activationCode);
    return activationToken;
  }

  /**
   * @apiNote TODO
   */
  public void deactivate(Session session) {}

  /**
   * @param activationToken
   * @return a connection record storing data with activation identity
   */
  public Connection connect(String activationToken) {
    if (activationToken == null) return null;

    var response = activationService.checkActivationToken(activationToken);

    if (response == null) return null;

    return new Connection(response);
  }

  /**
   * @param connection
   * @return a session
   */
  public Session login(Connection connection, String username, String password) {
    var loginToken = loginService.login(connection, username, password);

    if (loginToken == null) return null;

    return new Session(connection, username, loginToken);
  }

  public void registerClient(ClientRegistrationData registrationData) {
    var data = new RegistrationData(registrationData);

    registrationVault.register(data);
  }

  public void registerEmployee(EmployeeRegistrationData registrationData) {
    var data = new RegistrationData(registrationData);

    registrationVault.register(data);
  }

  /**
   * @param session
   */
  public void logout(Session session) {
    loginService.logout(session);
  }
}
