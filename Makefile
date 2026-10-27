# Common tasks. `make help` lists them.

.DEFAULT_GOAL := help
.PHONY: help dev dev-docker dev-stop up down clean logs psql test build image shell reset mvn

help: ## Show this help
	@grep -E '^[a-z-]+:.*?## ' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-10s\033[0m %s\n", $$1, $$2}'

dev-docker: ## Start database and application, both in containers (the normal way)
	docker compose -f compose.dev.yaml --profile docker-dev up

dev: ## Start only the database (if you run the application from a local IDE)
	docker compose -f compose.dev.yaml up -d db

dev-stop: ## Stop the development database
	docker compose -f compose.dev.yaml down

up: ## Build and start the full stack
	docker compose up --build -d

down: ## Stop the full stack, keep data
	docker compose down

clean: ## Stop and DELETE the database volume
	docker compose down -v
	docker compose -f compose.dev.yaml down -v

logs: ## Follow application logs
	docker compose logs -f app

psql: ## Open a psql shell in the database container
	docker compose exec db psql -U app -d kursusepunkt

test: ## Run the full verification build (in a container, no local JDK needed)
	docker run --rm -v "$$PWD":/w -v kursusepunkt-dev_maven-repo:/root/.m2 -w /w \
		maven:3.9-eclipse-temurin-26 mvn -B verify

build: ## Package the jar without running tests
	docker run --rm -v "$$PWD":/w -v kursusepunkt-dev_maven-repo:/root/.m2 -w /w \
		maven:3.9-eclipse-temurin-26 mvn -B clean package -DskipTests

mvn: ## Run any Maven goal in a container, e.g. make mvn ARGS="spotless:apply"
	docker run --rm -v "$$PWD":/w -v kursusepunkt-dev_maven-repo:/root/.m2 -w /w \
		maven:3.9-eclipse-temurin-26 mvn -B $(ARGS)

image: ## Build the container image locally
	docker build -t kursusepunkt:local .

shell: ## Open a shell inside the running application container
	docker compose exec app sh

reset: clean dev-docker ## Wipe the database and start fresh
	@echo "Database reset. Flyway will re-apply every migration on next start."
