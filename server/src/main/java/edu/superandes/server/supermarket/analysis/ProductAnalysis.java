package edu.superandes.server.supermarket.analysis;

import lombok.Data;

@Data
public class ProductAnalysis {
  private String supermarketName;
  private String branchOfficeName;
  private String category;
  private String timeUnit;
  private String mostDemandedProduct;
  private long mostDemandedProductCount;
  private String leastDemandedProduct;
  private long leastDemandedProductCount;
}
