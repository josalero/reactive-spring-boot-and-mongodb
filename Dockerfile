FROM openjdk:8u92-jre-alpine
WORKDIR /article
ENV version 0.0.1-SNAPSHOT
ADD ./build/libs/WebReactive-SpringBoot-MongoDb-${version}.jar /article/article-service.jar
CMD java -jar article-service.jar