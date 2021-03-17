FROM openjdk:latest
ADD build/libs/tetris.jar tetris.jar
ADD docker-utils/wait-for-it.sh wait-for-it.sh
EXPOSE 8080
CMD chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "postgres_db:5432","--","java", "-jar", "tetris.jar","-Dspring.profiles.active=cloud"]