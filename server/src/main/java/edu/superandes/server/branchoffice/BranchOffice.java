package edu.superandes.server.branchoffice;

import lombok.Data;

@Data
public class BranchOffice {
  private long id;
  private String name;
  private String city;
  private String address;
  private String supermarketId;
}
