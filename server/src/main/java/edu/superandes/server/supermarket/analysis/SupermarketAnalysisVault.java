package edu.superandes.server.supermarket.analysis;

import static edu.superandes.server.persistence.PersistenceManagerSingleton.getPersistenceManager;

import java.time.temporal.ChronoUnit;
import javax.jdo.PersistenceManager;

public class SupermarketAnalysisVault {
  private final PersistenceManager pm = getPersistenceManager();

  public SupermarketAnalysisVault() {}

  public void productAnalysis(String category, ChronoUnit chronoUnit) {
    var query = pm.newQuery("javax.jdo.query.SQL", "");
    // filter by category
    // group by branch office
    // group by time unit
    // do agreggates
    // - max units sold of a product in a given category
    // - max money recollected from a product in a given category
    // - min units sold of a product in a given category
  }
}
