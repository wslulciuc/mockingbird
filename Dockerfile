FROM java:openjdk-8
MAINTAINER Willy Lulciuc "willy@canary.is"

COPY ./dist/src.jvm.mockingbird.api-bundle /app/bin/mockingbird-api.jar

EXPOSE 8080

CMD java -jar /app/bin/mockingbird-api.jar
