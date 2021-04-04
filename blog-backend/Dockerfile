FROM node:10-alpine as angular-build

RUN mkdir -p /app/blog-frontend/

WORKDIR /app/blog-frontend/

COPY blog-frontend/package.json /app/blog-frontend/

RUN npm install

COPY . /app
WORKDIR /app/blog-frontend/
RUN npm run build-custome-with-deploy-url

RUN mkdir -p /app/src/main/resources/static/
RUN du -d 2 -a -h /app
FROM java:8 as spring-builder
FROM maven:alpine
COPY --from=angular-build /app/blog-frontend/dist /app/src/main/resources/static/

WORKDIR /app
COPY . .
RUN mvn install -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/blog-2.2.1.RELEASE.war"]
