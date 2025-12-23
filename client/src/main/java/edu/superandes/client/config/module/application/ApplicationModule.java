package edu.superandes.client.config.module.application;

import com.google.inject.Provides;
import javafx.fxml.FXMLLoader;

public class ApplicationModule {

  @Provides
  private FXMLLoader FXMLLoaderProvider() {
    return new FXMLLoader();
  }
}
