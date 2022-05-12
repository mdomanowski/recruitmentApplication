FROM adoptopenjdk/openjdk11:alpine
ADD target/forex-buddy-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD java -jar forex-buddy-0.0.1-SNAPSHOT.jar


