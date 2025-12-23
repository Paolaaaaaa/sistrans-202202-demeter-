module edu.superandes.server {
  exports edu.superandes.server.authentication.api;
  exports edu.superandes.server.configuration;
  exports edu.superandes.server.branchoffice.authentication;
  exports edu.superandes.server.user.authentication;

  requires javax.jdo;
  requires java.sql;
  requires com.google.guice;
  requires static lombok;
}
