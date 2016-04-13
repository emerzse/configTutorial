FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8104
ADD profiles-service-eureka-0.0.1.jar profiles-serviceEureka.jar
# RUN bash -c 'touch /profiles-serviceEureka.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/profiles-serviceEureka.jar" ]