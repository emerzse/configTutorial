FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8761
ADD eureka-service-0.0.1.jar eureka-serviceCatalog.jar
# RUN bash -c 'touch /eureka-serviceCatalog.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/eureka-serviceCatalog.jar" ]