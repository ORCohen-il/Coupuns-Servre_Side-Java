FROM openjdk:latest
WORKDIR /usr/app
COPY new_coupons_project-0.0.1.jar /home/new_coupons_project-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/home/new_coupons_project-0.0.1-SNAPSHOT.jar"]