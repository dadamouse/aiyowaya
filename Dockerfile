FROM openjdk:11
COPY ./target/*.jar /Users/weida/Desktop/mydocker/aiyowaya.jar
WORKDIR /Users/weida/Desktop/mydocker/
RUN sh -c 'touch aiyowaya.jar'
ENTRYPOINT ["java","-jar","aiyowaya.jar"]
