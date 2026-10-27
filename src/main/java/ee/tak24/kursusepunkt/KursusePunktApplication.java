package ee.tak24.kursusepunkt;

import java.time.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KursusePunktApplication {

  public static void main(String[] args) {
    SpringApplication.run(KursusePunktApplication.class, args);
  }

  /**
   * Injected wherever the current time is needed, so tests can substitute a fixed clock. See guide
   * 07.
   */
  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }
}
