# KursusePunkt

Backend for a course-and-grade management system. It is a REST API; the user interface is built
by a separate module.

> **Status:** early stage. Only the `Course` resource exists so far. Update this file whenever the
> code changes, so it always describes the project as it is today.

## Features

- Create, list, fetch and delete courses (`Course` resource)
- Paged course listing
- Interactive API documentation (Swagger UI)
- Database schema managed by versioned Flyway migrations

<!-- TODO: add each new feature here when you build it (students, enrolments, assignments, results, grading). -->

## Tech stack

Java 26 · Spring Boot 4.1 · Hibernate 7 · PostgreSQL 16 · Flyway 12 · Maven · Docker

## Getting started

### Prerequisites

Docker and Git. Nothing else: the build, the application and the database all run in containers.

### Run

```bash
git clone https://github.com/MrBruhButBetter/kursusepunkt.git
cd kursusepunkt
cp .env.example .env
make dev-docker
```

Wait for the log line `Started KursusePunktApplication`. Then open:

- API: <http://localhost:8080/api/courses>
- Health check: <http://localhost:8080/actuator/health>
- Swagger UI: <http://localhost:8080/swagger-ui.html>

Run `make help` to list every available command.

### Troubleshooting

| Problem | Fix |
| ------- | --- |
| `port is already allocated` for port 5432 | Another program (often a local PostgreSQL) uses the port. Stop it, or set a different database port in `.env`. |
| `UnknownHostException: db` | The app cannot find the database container. Run `docker compose -f compose.dev.yaml --profile docker-dev down`, then `make dev-docker` again. |
| Container exits with code 137 | Docker ran out of memory. Give Docker at least 4 GB. |

## Running the tests

```bash
make test
```

The tests start a real temporary PostgreSQL database with Testcontainers, so Docker must be running.
The first run downloads images and can take a few minutes.

## Architecture

The application is split into layers. Each layer has one job:

- **Controller** receives HTTP requests and returns HTTP responses. It contains no business logic.
- **Service** contains the business rules and decides what counts as one transaction.
- **Repository** is the only layer that talks to the database.
- **Entity** maps one Java class to one database table.
- **DTOs** (request and response records) define exactly what goes in and out of the API. Entities are
  never returned directly.

Architecture tests (ArchUnit) fail the build if a controller uses a repository directly.

<!-- TODO: add a short diagram here (see Domain model section). -->

## Domain model

Currently one entity:

| Entity | Fields |
| ------ | ------ |
| `Course` | `id`, `name`, `gradingKey`, `credits` |

<!-- TODO: add a Mermaid ER diagram when more entities exist. -->

## API

| Method | Path | Description |
| ------ | ---- | ----------- |
| `GET` | `/api/courses` | List courses (paged) |
| `POST` | `/api/courses` | Create a course. Returns `201 Created` with a `Location` header |
| `GET` | `/api/courses/{id}` | Fetch one course |
| `DELETE` | `/api/courses/{id}` | Delete a course. Returns `204 No Content` |

Example:

```bash
curl -i -X POST http://localhost:8080/api/courses -H 'Content-Type: application/json' -d '{"name":"Java Backend","gradingKey":"WEIGHTED","credits":5}'
```

Interactive documentation: <http://localhost:8080/swagger-ui.html>

## Project status and known limitations

- Only the `Course` resource is implemented.
- Swagger UI shows `200` for the create endpoint, but the real response is `201`. The API
  documentation annotations are not finished yet.
<!-- TODO: add honest limitations as you find them. Reviewers value this section. -->

## Author

<your name>, TAK-24