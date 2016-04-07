FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8101
ADD accounts-service-0.0.1.jar accounts-service.jar
# RUN bash -c 'touch /accounts-service.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/accounts-service.jar" ]