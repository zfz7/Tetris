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
- [] Gitci depoly on to EC2 commit to `release`
- [X] Flyway migrations
- [X] Acceptance tests with `cypress`
- [X] Code scan using CodeQL which block deployment

## Docker Setup Dev
1. Install Docker and Docker Compose
2. Create Docker DB run from app home

```
 docker-compose up -d
```