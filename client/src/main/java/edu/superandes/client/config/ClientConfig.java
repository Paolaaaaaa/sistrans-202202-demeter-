package edu.superandes.client.config;

import java.nio.file.Path;

public class ClientConfig {
  public static final Path rootPath = Path.of(System.getProperty("user.dir"));
  public static final Path data = rootPath.resolve("data");
}
