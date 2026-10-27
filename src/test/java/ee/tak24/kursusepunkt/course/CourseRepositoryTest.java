package ee.tak24.kursusepunkt.course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace.NONE;

import ee.tak24.kursusepunkt.support.ContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.flyway.autoconfigure.FlywayAutoConfiguration;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

/** Runs against real PostgreSQL, not an embedded stand-in. See guides 03 and 11. */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Import(ContainerConfig.class)
@ImportAutoConfiguration(FlywayAutoConfiguration.class)
class CourseRepositoryTest {

  @Autowired private CourseRepository repository;

  @Test
  void finds_an_existing_course_by_name_ignoring_case() {
    repository.save(new Course("Java Backend", "WEIGHTED", 5));

    assertThat(repository.existsByNameIgnoreCase("java backend")).isTrue();
    assertThat(repository.existsByNameIgnoreCase("Python")).isFalse();
  }
}
