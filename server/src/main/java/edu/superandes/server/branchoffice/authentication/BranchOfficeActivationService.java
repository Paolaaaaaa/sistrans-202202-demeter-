package edu.superandes.server.branchoffice.authentication;

import com.google.inject.Inject;

/**
 * {@summary Branch office activator service}
 *
 * @author Orion
 */
public class BranchOfficeActivationService {
  private final ActivationVault activationVault;

  /**
   * @param vault database retrieval for activation
   */
  @Inject
  public BranchOfficeActivationService(ActivationVault vault) {
    this.activationVault = vault;
  }

  /**
   * @param activationToken
   * @return an activation response
   */
  public ActivationResponse checkActivationToken(String activationToken) {
    var response = activationVault.checkActivationToken(activationToken);
    return response;
  }

  /**
   * @param activationCode
   * @return an activation token
   */
  public String validateActivationCode(String activationCode) {
    var token = activationVault.checkActivationCode(activationCode);
    return token;
  }
}
