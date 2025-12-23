package edu.superandes.client.config.module.authentication;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ClientAuthenticationModule extends AbstractModule {
  @Override
  protected void configure() {
    super.configure();
  }

  @Provides
  @onActivation
  public Parent onActivationNodeProvider() {
    var n = new VBox();
    n.setStyle("-fx-background-color: red");
    return n;
  }

  @Provides
  @onLogin
  public Parent onLoginNodeProvider() {
    var n = new VBox();
    n.setStyle("-fx-background-color: red");
    return n;
  }
}
