FROM java:openjdk-8
MAINTAINER Willy Lulciuc "willy@canary.is"

COPY ./dist/src.jvm.com.mockingbird.mockingbird-bundle /app/bin/server.jar 

EXPOSE 8080

CMD java -jar /app/bin/server.jar
