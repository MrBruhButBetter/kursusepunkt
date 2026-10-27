package ee.tak24.kursusepunkt.course;

public class CourseNotFoundException extends RuntimeException {

  public CourseNotFoundException(Long id) {
    super("Course " + id + " not found");
  }
}
