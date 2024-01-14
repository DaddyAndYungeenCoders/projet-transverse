FROM openjdk:21-ea-1-jdk-slim

WORKDIR /app

# Copiez le fichier POM
COPY ./pom.xml .

RUN apt-get update && \
    apt-get install -y maven && \
    mvn dependency:go-offline

COPY ./src ./src

RUN mvn clean package -DskipTests

CMD ["java", "-cp", "target/classes:target/dependency/*", "org.example.Main"]
