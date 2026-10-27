package ee.tak24.kursusepunkt.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/** Input for creating a course. Validated before it reaches the service. */
public record CreateCourseRequest(
    @NotBlank @Size(max = 120) String name,
    @NotBlank @Size(max = 40) String gradingKey,
    @Positive int credits) {}
