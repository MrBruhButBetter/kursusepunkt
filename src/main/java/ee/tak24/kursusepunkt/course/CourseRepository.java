package ee.tak24.kursusepunkt.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  boolean existsByNameIgnoreCase(String name);
}
