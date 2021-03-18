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
- [X] Run on EC2 with certs
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
1. Install Docker, Docker-Compose, openJDK, yarn and certbot
   * If on Ubuntu 20 run `sudo ./setupEC2.sh`
2. Install certs run `sudo certbot certonly --standalone`
2. Create keystore.p12  `openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name tomcat -CAfile chain.pem -caname root`
   * Use password `tetris`
3. Copy certs home folder ` cp /etc/letsencrypt/live/tetris.zfz7.org/keystore.p12 .`
3. Build Jar `./gradlew clean assemble`
4. Build tetris docker image `docker build . -t tetris`
5. Start all containers `docker-compose -f docker-compose.prod.yml up -d`
   * database is not exposed to localhost
   * exposes app on port 80 and 443