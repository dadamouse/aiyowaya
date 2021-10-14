FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} aiyowaya.jar
ENTRYPOINT ["java","-jar","/aiyowaya.jar"]
