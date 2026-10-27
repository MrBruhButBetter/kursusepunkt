CREATE TABLE course (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(120) NOT NULL,
    grading_key VARCHAR(40)  NOT NULL,
    credits     INTEGER      NOT NULL,
    version     BIGINT       NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX uq_course_name ON course (lower(name));
