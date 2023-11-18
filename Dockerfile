FROM openjdk:17-jdk-slim
RUN apt-get update && \
    apt-get install -y curl
WORKDIR /devops-spring-boot
COPY target/*.jar /devops-spring-boot/devops-0.0.1-SNAPSHOT.jar
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=9090 -jar devops-0.0.1-SNAPSHOT.jar