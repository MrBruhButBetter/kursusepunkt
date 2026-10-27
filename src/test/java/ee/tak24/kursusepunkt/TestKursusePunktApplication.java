package ee.tak24.kursusepunkt;

import ee.tak24.kursusepunkt.support.ContainerConfig;
import org.springframework.boot.SpringApplication;

/**
 * Run this from your IDE to start the application with a throwaway PostgreSQL, without any compose
 * file. See guide 11.
 */
public class TestKursusePunktApplication {

  public static void main(String[] args) {
    SpringApplication.from(KursusePunktApplication::main).with(ContainerConfig.class).run(args);
  }
}
