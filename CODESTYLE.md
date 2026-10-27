# Code standard

Agreed: <date>. Changes require a group decision.

## Formatting
- Google Java Format, enforced by Spotless. `make mvn ARGS=spotless:apply` before every commit.
- Line length 100. UTF-8. LF line endings. Final newline.

## Language
- All identifiers, comments and documentation in English.

## Naming
| Thing | Convention | Example |
|---|---|---|
| Class | UpperCamelCase, noun | `EnrolmentService` |
| Method | lowerCamelCase, verb | `calculateFinalGrade` |
| Boolean method | reads as a question | `isEligible`, `hasPassed` |
| Constant | UPPER_SNAKE_CASE | `MAX_ENROLMENTS` |
| Package | lowercase, singular | `...kursusepunkt.enrolment` |
| Test | behaviour in snake_case | `rejects_zero_total_weight` |
| DB table / column | snake_case | `enrolment`, `final_grade` |
| Branch | `feature/<issue>-slug` | `feature/42-grade-report` |

## Rules
- Constructor injection only. No `@Autowired` on fields.
- No commented-out code in main. Git remembers.
- No `System.out.println` outside tests.
- Public domain API carries Javadoc.

## Commits
Conventional Commits: `feat|fix|test|docs|refactor|chore(scope): summary`

## Merging
No merge without one classmate's review and a green CI build.
