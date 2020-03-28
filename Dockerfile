FROM openjdk:8
ADD target/data-getter-micro.jar data-getter-micro.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "data-getter-micro.jar"]