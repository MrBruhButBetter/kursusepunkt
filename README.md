# KursusePunkt

Backend for a course-and-grade management system. REST API; the user interface is a separate module.

## Getting started

Requires **Docker**. Nothing else — no JDK, no Maven, no PostgreSQL.

```bash
make dev-docker
```

- API — http://localhost:8080/api/courses
- Health — http://localhost:8080/actuator/health
- API docs — http://localhost:8080/swagger-ui.html

`make help` lists every command.

## Running the tests

```bash
make test
```

Tests run against a real PostgreSQL started by Testcontainers, so Docker must be running.

## What is already here

A single `Course` resource, implemented through the whole stack as a worked example:

```
src/main/java/ee/tak24/kursusepunkt/
├── KursusePunktApplication.java
├── common/GlobalExceptionHandler.java   RFC 9457 problem details
└── course/
    ├── Course.java                      entity
    ├── CourseRepository.java            Spring Data
    ├── CourseService.java               business logic, transactions
    ├── CourseController.java            REST endpoints
    └── dto/                             request and response records
```

Plus a Flyway migration, a web slice test, a Testcontainers repository test, and ArchUnit rules that
fail the build if a controller reaches into a repository.

Build on this pattern for the rest of the domain: students, enrolments, assignments, results.

## Study guides

The guides for this project live in their own repository:

**[SB-M8-guide](https://github.com/kuressaareametikool/SB-M8-guide)**

Start with `00-getting-started.md`.

## Project status

Template. Replace this README as the project takes shape — guide 09 explains what it should become.
