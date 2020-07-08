FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
ADD /target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/app.jar"]
