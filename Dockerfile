FROM maven:3.8.1-jdk-11 as build-stage
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:ubi
COPY --from=build-stage ./build/target/*.jar ./avaliador-familia.jar
EXPOSE 8080
ENTRYPOINT java -jar avaliador-familia.jar