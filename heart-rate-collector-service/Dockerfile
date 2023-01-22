FROM openjdk:17
EXPOSE 8081
COPY target/heart-rate-collector-service-docker.jar heart-rate-collector-service-docker.jar
ENTRYPOINT ["java","-jar","/heart-rate-collector-service-docker.jar"]