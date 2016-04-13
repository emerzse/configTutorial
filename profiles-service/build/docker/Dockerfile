FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8104
ADD profiles-service-0.0.1.jar profiles-service.jar
# RUN bash -c 'touch /profiles-service.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/profiles-service.jar" ]