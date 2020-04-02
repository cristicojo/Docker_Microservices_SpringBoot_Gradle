FROM openjdk:8
ADD build/libs/docker_crud_service.jar docker_crud_service.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:27017/local", "-jar", "docker_crud_service.jar"]
