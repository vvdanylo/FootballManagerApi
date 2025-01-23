FROM gradle:jdk21 AS build

WORKDIR /app

COPY . .

RUN ./gradlew dependencies --no-daemon

RUN ./gradlew build --no-daemon

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]