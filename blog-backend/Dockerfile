FROM java:8 as spring-builder
FROM maven:alpine
WORKDIR /app
COPY . .
RUN mvn install -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/blog-2.2.1.RELEASE.war"]
