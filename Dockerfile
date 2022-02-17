FROM openjdk:11.0-jre-slim
WORKDIR /
ENV JAVA_OPTS "-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -XshowSettings:vm"
ADD target/web-elf-chat-1.0-SNAPSHOT.jar app.jar
RUN useradd -m webelfuser
USER webelfuser
EXPOSE 8080
CMD java -jar -Dspring.profiles.active=prod app.jar