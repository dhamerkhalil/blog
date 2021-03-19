# Blog

This project is a blog that users can create postes and comments.

## required configuration

Install Java 1.8 and configure JAVA_HOME envirement variable pointing to JDK folder.

Install maven and add /bin folder to system path

Install MariaDB and create a `root` acount with `root` password ( database config could be changed in application.properties file)

## Build project and run

Run `run.bat` for building all project and then Navigate to `http://localhost:8080/`.

run.bat will install npm and node silenty during build process.