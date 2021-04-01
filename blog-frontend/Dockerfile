# Stage 1

FROM node:10-alpine as angular-build

RUN mkdir -p /app

WORKDIR /app

COPY package.json /app

RUN npm install

COPY . /app

RUN npm run build-custome-with-deploy-url

FROM nginx:1.17.1-alpine

COPY --from=angular-build /app/dist /usr/share/nginx/html