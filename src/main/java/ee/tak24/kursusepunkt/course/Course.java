package ee.tak24.kursusepunkt.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

/** A course students can be enrolled in. */
@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120)
  private String name;

  /** Selects the grading strategy at runtime. See guide 04. */
  @Column(name = "grading_key", nullable = false, length = 40)
  private String gradingKey;

  @Column(nullable = false)
  private int credits;

  @Version private long version;

  protected Course() {
    // required by JPA
  }

  public Course(String name, String gradingKey, int credits) {
    this.name = name;
    this.gradingKey = gradingKey;
    this.credits = credits;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getGradingKey() {
    return gradingKey;
  }

  public int getCredits() {
    return credits;
  }
}
