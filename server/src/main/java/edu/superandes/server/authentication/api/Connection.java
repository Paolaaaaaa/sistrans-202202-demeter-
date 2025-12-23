package edu.superandes.server.authentication.api;

import edu.superandes.server.branchoffice.authentication.ActivationResponse;
import java.util.Objects;

/**
 * {@summary Connection record that provides the identity associated with an activation code. It is
 * guaranteed to always be in a valid state.}
 *
 * @author Orion
 */
public record Connection(long supermarketId, long branchOfficeId, String activationToken) {
  /**
   * @param supermarketId supermarket identification
   * @param branchOfficeId branch office identification
   * @param activationToken valid activation token
   * @throws NullPointerException
   */
  public Connection {
    Objects.requireNonNull(supermarketId);
    Objects.requireNonNull(branchOfficeId);
    Objects.requireNonNull(activationToken);
  }

  /**
   * @param response response from an activation service
   * @throws NullPointerException
   */
  public Connection(ActivationResponse response) {
    this(response.getSupermarketId(), response.getBranchOfficeId(), response.getActivationToken());
  }
}
