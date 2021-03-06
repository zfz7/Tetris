FROM openjdk:latest
ADD build/libs/tetris.jar tetris.jar
ADD dockerUtils/wait-for-it.sh wait-for-it.sh
ADD keystore.p12 /etc/letsencrypt/live/tetris.zfz7.org/keystore.p12
EXPOSE 8080 8443
CMD chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "postgres_db:5432","--","java", "-jar", "tetris.jar","--spring.profiles.active=deploy"]