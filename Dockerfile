FROM openjdk:8
ADD build/libs/crud_service.jar crud_service.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:27017/local", "-jar", "crud_service.jar"]
