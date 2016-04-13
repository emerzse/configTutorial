FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8101
ADD accounts-service-eureka-0.0.1.jar accounts-serviceEureka.jar
# RUN bash -c 'touch /accounts-serviceEureka.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/accounts-serviceEureka.jar" ]