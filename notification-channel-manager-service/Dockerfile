FROM openjdk:17
EXPOSE 8086
COPY target/notification-channel-manager-service-docker.jar notification-channel-manager-service-docker.jar
ENTRYPOINT ["java","-jar","/notification-channel-manager-service-docker.jar"]