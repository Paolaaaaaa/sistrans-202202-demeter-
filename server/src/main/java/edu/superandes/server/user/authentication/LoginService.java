package edu.superandes.server.user.authentication;

import com.google.inject.Inject;
import edu.superandes.server.authentication.IllegalAuthenticationAccess;
import edu.superandes.server.authentication.api.Connection;
import edu.superandes.server.authentication.api.Session;
import edu.superandes.server.branchoffice.authentication.BranchOfficeActivationService;

/**
 * {@summary login service}
 *
 * @author Orion
 */
public class LoginService {

  private final BranchOfficeActivationService activationService;
  private final LoginVault loginVault;

  /**
   * @param loginVault database retrieval for login
   */
  @Inject
  public LoginService(BranchOfficeActivationService activationService, LoginVault loginVault) {
    this.activationService = activationService;
    this.loginVault = loginVault;
  }

  /**
   * @param connection
   * @param username
   * @param password
   * @return a login token
   */
  public String login(Connection connection, String username, String password) {
    var response = activationService.checkActivationToken(connection.activationToken());

    if (response == null) throw new IllegalAuthenticationAccess();

    var token = loginVault.login(username, password);
    return token;
  }

  /**
   * @param session
   */
  public void logout(Session session) {
    loginVault.logout(session.username(), session.loginToken());
  }
}
