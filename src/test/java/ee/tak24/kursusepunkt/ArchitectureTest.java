package ee.tak24.kursusepunkt;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;

/** The layering from guide 02, enforced mechanically. */
@AnalyzeClasses(packages = "ee.tak24.kursusepunkt")
class ArchitectureTest {

  @ArchTest
  static final ArchRule controllers_do_not_touch_repositories =
      noClasses()
          .that()
          .haveSimpleNameEndingWith("Controller")
          .should()
          .dependOnClassesThat()
          .haveSimpleNameEndingWith("Repository");

  @ArchTest
  static final ArchRule entities_do_not_reach_controllers =
      noClasses()
          .that()
          .haveSimpleNameEndingWith("Controller")
          .should()
          .dependOnClassesThat()
          .areAnnotatedWith(Entity.class);
}
