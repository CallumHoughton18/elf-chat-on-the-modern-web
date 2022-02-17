FROM openjdk:8
WORKDIR /
ADD target/web-elf-chat-1.0-SNAPSHOT.jar app.jar
RUN useradd -m webelfuser
USER webelfuser
EXPOSE 8080
CMD java -jar -Dspring.profiles.active=prod app.jar