package ee.tak24.kursusepunkt.course.dto;

import ee.tak24.kursusepunkt.course.Course;

/**
 * What the API returns. Deliberately separate from the entity: the client contract must not change
 * whenever the database schema does. See guide 02.
 */
public record CourseResponse(Long id, String name, String gradingKey, int credits) {

  public static CourseResponse from(Course course) {
    return new CourseResponse(
        course.getId(), course.getName(), course.getGradingKey(), course.getCredits());
  }
}
