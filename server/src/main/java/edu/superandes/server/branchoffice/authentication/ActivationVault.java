package edu.superandes.server.branchoffice.authentication;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import javax.jdo.PersistenceManager;

/**
 * {@summary Database activation provider}
 *
 * @author Orion
 */
public class ActivationVault {
  private final PersistenceManager pm = getPersistenceManager();

  public ActivationVault() {}

  /**
   * @param activationCode
   * @return an activation token
   */
  @SuppressWarnings("unchecked")
  public String checkActivationCode(String activationCode) {
    var query =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "SELECT auth.activation_token FROM branch_office_authentication auth WHERE auth.activation_code = ?")
            .setParameters(activationCode);

    var activationToken = query.executeResultUnique(String.class);
    return (String) activationToken;
  }

  /**
   * @param activationToken
   * @return an activation response
   */
  @SuppressWarnings("unchecked")
  public ActivationResponse checkActivationToken(String activationToken) {
    var query =
        pm.newQuery(
                "javax.jdo.query.SQL",
                "SELECT bo.supermarket_id, auth.branch_office_id FROM branch_office_authentication auth INNER JOIN branch_office bo ON auth.branch_office_id = bo.id WHERE auth.activation_token = ?")
            .setParameters(activationToken);

    var branchOfficeId = query.executeResultUnique(ActivationResponse.class);
    return (ActivationResponse) branchOfficeId;
  }
}
