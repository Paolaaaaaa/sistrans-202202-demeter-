package edu.superandes.server.branchoffice.authentication;

import lombok.Data;

@Data
public class ActivationResponse {
  private Long supermarketId;
  private Long branchOfficeId;
  private String activationToken;
}
