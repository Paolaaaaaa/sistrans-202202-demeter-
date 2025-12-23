package edu.superandes.client.authentication;

import static edu.superandes.client.config.ClientConfig.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TokenManager {
  private final Path tokenPath = data.resolve("activationToken");

  public TokenManager() {}

  public String getToken() {
    if (Files.notExists(tokenPath)) {
      return null;
    }

    String token = null;
    try {
      token = Files.readString(tokenPath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return token;
  }

  public void saveToken(String activationToken) {
    System.out.println(tokenPath);
    try {
      Files.writeString(tokenPath, activationToken);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void resetToken() {
    try {
      Files.deleteIfExists(tokenPath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
