FROM openjdk:8-jre-alpine

RUN apk update

COPY target/scala-**/msakkaapi-assembly-*.jar /app.jar

EXPOSE 8080

ENV MONGO_USER=$MONGO_USER
ENV MONGO_PASSWORD=$MONGO_PASSWORD
ENV MONGO_URL=$MONGO_URL

ENTRYPOINT ["java","-jar","/app.jar"]
