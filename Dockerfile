FROM java:8

MAINTAINER Juliano Boesel Mohr <juliaaano@gmail.com>

COPY target/app-*.jar app/app.jar

WORKDIR /app

VOLUME /app/logs

EXPOSE 8000

CMD ["java", "-jar", "app.jar"]
