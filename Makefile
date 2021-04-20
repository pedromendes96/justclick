run:
	./mvnw spring-boot:run

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down

.DEFAULT_GOAL := run