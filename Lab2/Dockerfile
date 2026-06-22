FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/Lab2-0.0.1-SNAPSHOT.jar app.jar
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]