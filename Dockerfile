FROM openjdk:17-jdk
MAINTAINER VICTORIAP
COPY build/libs/*.jar /
CMD ["java", "-jar", "/desafioBackend.jar"]
RUN rm /etc/localtime
RUN ln -s /usr/share/zoneinfo/America/Buenos_Aires /etc/localtime

