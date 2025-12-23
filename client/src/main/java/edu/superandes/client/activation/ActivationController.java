package edu.superandes.client.activation;

import com.google.inject.Inject;
import edu.superandes.client.config.module.authentication.onActivation;
import edu.superandes.server.authentication.api.AuthenticationService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ActivationController {
  private final AuthenticationService authenticationService;
  private final Parent onActivationNode;
  @FXML private TextField activationTextField;
  @FXML private Label statusLabel;

  @Inject
  public ActivationController(
      AuthenticationService authenticationService, @onActivation Parent onActivationNode) {
    this.authenticationService = authenticationService;
    this.onActivationNode = onActivationNode;
  }

  @FXML
  private void onActivationButtonClicked(Event e) {
    var activationCode = activationTextField.getText();
    var r = authenticationService.activate(activationCode);
    if (r != null) {
      this.changeScene();
    }

    statusLabel.setText("Código de activación inválido");
    statusLabel.setVisible(true);
  }

  private void changeScene() {
    var scene = statusLabel.getScene();
    scene.setRoot(onActivationNode);
  }

  @FXML
  public void initialize() {}
}
