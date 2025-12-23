package edu.superandes.server.configuration;

import com.google.inject.AbstractModule;
import edu.superandes.server.authentication.api.AuthenticationService;
import edu.superandes.server.branchoffice.authentication.ActivationVault;
import edu.superandes.server.branchoffice.authentication.BranchOfficeActivationService;
import edu.superandes.server.user.authentication.LoginService;
import edu.superandes.server.user.authentication.LoginVault;
import edu.superandes.server.user.authentication.SessionVault;

public class AuthenticationModule extends AbstractModule {
  @Override
  protected void configure() {
    super.configure();
    bind(AuthenticationService.class);
    bind(BranchOfficeActivationService.class);
    bind(ActivationVault.class);
    bind(LoginService.class);
    bind(LoginVault.class);
    bind(SessionVault.class);
  }
}
