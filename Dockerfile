FROM openjdk:17-jdk-slim
WORKDIR /devops-spring-boot
COPY target/*.jar /devops-spring-boot/devops-0.0.1-SNAPSHOT.jar
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=9090 -jar devops-0.0.1-SNAPSHOT.jar