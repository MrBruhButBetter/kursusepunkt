package ee.tak24.kursusepunkt.course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import ee.tak24.kursusepunkt.common.GlobalExceptionHandler;
import ee.tak24.kursusepunkt.course.dto.CourseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

/** Web slice test: the controller is real, everything below it is mocked. See guide 07. */
@WebMvcTest(CourseController.class)
@Import(GlobalExceptionHandler.class)
class CourseControllerTest {

  @Autowired private MockMvcTester mvc;

  @MockitoBean private CourseService courseService;

  @Test
  void returns_a_course_by_id() {
    when(courseService.findById(1L)).thenReturn(new CourseResponse(1L, "Java", "WEIGHTED", 5));

    assertThat(mvc.get().uri("/api/courses/1"))
        .hasStatusOk()
        .bodyJson()
        .extractingPath("$.name")
        .isEqualTo("Java");
  }

  @Test
  void rejects_a_course_with_a_blank_name() {
    assertThat(
            mvc.post()
                .uri("/api/courses")
                .contentType(APPLICATION_JSON)
                .content(
                    """
                    {"name": "", "gradingKey": "WEIGHTED", "credits": 5}
                    """))
        .hasStatus(HttpStatus.BAD_REQUEST);

    verifyNoInteractions(courseService);
  }

  @Test
  void reports_a_missing_course_as_a_problem_detail() {
    when(courseService.findById(9999L)).thenThrow(new CourseNotFoundException(9999L));

    assertThat(mvc.get().uri("/api/courses/9999"))
        .hasStatus(HttpStatus.NOT_FOUND)
        .bodyJson()
        .extractingPath("$.title")
        .isEqualTo("Course not found");
  }
}
