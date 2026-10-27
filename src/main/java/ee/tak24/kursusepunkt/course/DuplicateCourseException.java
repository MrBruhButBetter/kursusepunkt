package ee.tak24.kursusepunkt.course;

public class DuplicateCourseException extends RuntimeException {

  public DuplicateCourseException(String name) {
    super("A course named '" + name + "' already exists");
  }
}
