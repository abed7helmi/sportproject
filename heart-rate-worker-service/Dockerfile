FROM openjdk:17
EXPOSE 8082
COPY target/heart-rate-worker-service-docker.jar heart-rate-worker-service-docker.jar
ENTRYPOINT ["java","-jar","/heart-rate-worker-service-docker.jar"]