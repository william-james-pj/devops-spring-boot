FROM openjdk:8-jre-slim
WORKDIR /devops-spring-boot
COPY target/*.war /devops-spring-boot/devops-0.0.1-SNAPSHOT.war
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=9090 -jar devops-0.0.1-SNAPSHOT.war