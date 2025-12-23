module edu.superandes.client {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.google.guice;
  requires edu.superandes.server;

  exports edu.superandes.client;
  exports edu.superandes.client.activation;
  exports edu.superandes.client.config.module.authentication;
  exports edu.superandes.client.config.module.application;

  opens edu.superandes.client.activation;
}
