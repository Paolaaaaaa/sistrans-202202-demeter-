package edu.superandes.client.login;

import com.google.inject.Inject;
import edu.superandes.client.config.module.authentication.onLogin;
import edu.superandes.server.authentication.api.AuthenticationService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
  private final AuthenticationService authenticationService;
  private final Parent onLoginNode;
  @FXML private TextField emailTextField;
  @FXML private TextField passwordTextField;
  @FXML private Label statusLabel;

  @Inject
  public LoginController(AuthenticationService authenticationService, @onLogin Parent onLoginNode) {
    this.authenticationService = authenticationService;
    this.onLoginNode = onLoginNode;
  }

  @FXML
  private void onLoginButtonClicked(Event e) {
    var email = emailTextField.getText();
    var password = passwordTextField.getText();
  }

  private void changeScene() {
    var scene = statusLabel.getScene();
    scene.setRoot(onLoginNode);
  }

  @FXML
  public void initialize() {}
}
