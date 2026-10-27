package ee.tak24.kursusepunkt.course;

import ee.tak24.kursusepunkt.course.dto.CourseResponse;
import ee.tak24.kursusepunkt.course.dto.CreateCourseRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @Operation(summary = "List courses")
  @GetMapping
  public Page<CourseResponse> list(Pageable pageable) {
    return courseService.findAll(pageable);
  }

  @Operation(summary = "Fetch one course")
  @GetMapping("/{id}")
  public CourseResponse get(@PathVariable Long id) {
    return courseService.findById(id);
  }

  @Operation(summary = "Create a course")
  @PostMapping
  public ResponseEntity<CourseResponse> create(@Valid @RequestBody CreateCourseRequest request) {
    CourseResponse created = courseService.create(request);
    return ResponseEntity.created(URI.create("/api/courses/" + created.id())).body(created);
  }

  @Operation(summary = "Delete a course")
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    courseService.delete(id);
  }
}
