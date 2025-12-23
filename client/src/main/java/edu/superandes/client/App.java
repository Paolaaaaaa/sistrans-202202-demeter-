package edu.superandes.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.superandes.client.activation.ActivationController;
import edu.superandes.client.config.GraphicalInterfaceConfig;
import edu.superandes.client.config.module.authentication.ClientAuthenticationModule;
import edu.superandes.server.configuration.AuthenticationModule;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
  private final Injector injector =
      Guice.createInjector(new ClientAuthenticationModule(), new AuthenticationModule());

  private final FXMLLoader fxmlLoader = injector.getInstance(FXMLLoader.class);
  private final GraphicalInterfaceConfig config = new GraphicalInterfaceConfig();

  private boolean activated = false;

  private void onApplicationStartup(Stage primaryStage) {
    primaryStage.setTitle(config.title);
    primaryStage.setMinWidth(config.minWidth);
    primaryStage.setMinHeight(config.minHeight);
    primaryStage.setMaximized(true);

    fxmlLoader.setControllerFactory(injector::getInstance);

    // CHECK FOR ACTIVATION TOKEN ON STARTUP
  }

  private void setRootScene(Stage primaryStage) {
    try (var is = ActivationController.class.getResourceAsStream("activation.fxml")) {
      var root = fxmlLoader.<VBox>load(is);
      var scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void start(Stage primaryStage) {
    onApplicationStartup(primaryStage);
    setRootScene(primaryStage);
  }
}
