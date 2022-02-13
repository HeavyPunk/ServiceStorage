FROM openjdk:17
MAINTAINER HeavyPunk
RUN mkdir "/usr/src/service-storage"
COPY target/*.jar /usr/src/service-storage
WORKDIR /usr/src/service-storage
EXPOSE 8080
CMD java -jar *.jar
