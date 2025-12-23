package edu.superandes.server.product;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import javax.jdo.PersistenceManager;

public class ProductVault {
  private final PersistenceManager pm = getPersistenceManager();

  public ProductVault() {
    pm.close();
  }
}
