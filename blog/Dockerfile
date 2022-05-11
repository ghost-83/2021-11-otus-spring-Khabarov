FROM openjdk:17.0.1-jdk

WORKDIR /usr/src/ghost_main

COPY ./target/blog.jar /usr/src/ghost_main

EXPOSE 8080

ENTRYPOINT ["java","-jar","blog.jar"]