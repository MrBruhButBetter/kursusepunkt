package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.course.dto.CourseResponse;
import ee.tak24.kursusepunkt.course.dto.CreateCourseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

  private final CourseRepository repository;

  CourseService(CourseRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public Page<CourseResponse> findAll(Pageable pageable) {
    return repository.findAll(pageable).map(CourseResponse::from);
  }

  @Transactional(readOnly = true)
  public CourseResponse findById(Long id) {
    return repository
        .findById(id)
        .map(CourseResponse::from)
        .orElseThrow(() -> new CourseNotFoundException(id));
  }

  /**
   * Creates a course.
   *
   * @param request validated input
   * @return the created course
   * @throws DuplicateCourseException if a course with the same name already exists
   */
  public CourseResponse create(CreateCourseRequest request) {
    if (repository.existsByNameIgnoreCase(request.name())) {
      throw new DuplicateCourseException(request.name());
    }
    Course course = new Course(request.name(), request.gradingKey(), request.credits());
    return CourseResponse.from(repository.save(course));
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new CourseNotFoundException(id);
    }
    repository.deleteById(id);
  }
}
