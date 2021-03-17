# Tetris
Template to build side projects
## Goals
- [X] Spring/Kotlin backend
- [X] Postgres database
- [X] React/Typescript frontend
- [X] Use grade to build and test
- [X] Gitci build and test
- [X] Basic hello world functionality
- [X] Deploy to Heroku
- [X] Gitci depoly on to heroku commit to `release`
- [ ] Gitci depoly on to EC2 commit to `release`
- [X] Flyway migrations
- [X] Acceptance tests with `cypress`
- [X] Code scan using CodeQL which block deployment

## Dev Setup
Will run tetris.jar on local machine and DB inside docker
1. Install Docker and Docker Compose
2. Create Docker database, run from app home
    * exposes database on port 5432
    * exposes app on port 8080
```
 docker-compose up -d
```

## Heruko Setup
1. Java dyno: ` web java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/*.jar `
2. Postgress database `heroku-postgresql`

## EC2 Setup
1. Install Docker, Docker-Compose and openJDK
2. Build Jar `./gradlew clean assemble`
3. Build tetris docker image `docker build . -t tetris`
4. Start all conatiners `docker-compose -f docker-compose.prod.yml up -d`
   * database is not exposed to localhost
   * exposes app on port 80