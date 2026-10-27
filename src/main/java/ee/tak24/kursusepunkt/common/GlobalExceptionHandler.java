package ee.tak24.kursusepunkt.common;

import static java.util.stream.Collectors.toMap;

import ee.tak24.kursusepunkt.course.CourseNotFoundException;
import ee.tak24.kursusepunkt.course.DuplicateCourseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Turns exceptions into RFC 9457 problem details, so every error has the same shape. */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CourseNotFoundException.class)
  ProblemDetail notFound(CourseNotFoundException e) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    problem.setTitle("Course not found");
    return problem;
  }

  @ExceptionHandler(DuplicateCourseException.class)
  ProblemDetail conflict(DuplicateCourseException e) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    problem.setTitle("Course already exists");
    return problem;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ProblemDetail invalid(MethodArgumentNotValidException e) {
    ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problem.setTitle("Validation failed");
    problem.setProperty(
        "errors",
        e.getBindingResult().getFieldErrors().stream()
            .collect(
                toMap(
                    FieldError::getField,
                    FieldError::getDefaultMessage,
                    (first, second) -> first)));
    return problem;
  }
}
