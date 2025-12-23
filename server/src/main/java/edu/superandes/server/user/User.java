package edu.superandes.server.user;

import lombok.Data;

@Data
public class User {
  private String email;
  private long roleId;
  private String password;
  private long branchOfficeId;
}
