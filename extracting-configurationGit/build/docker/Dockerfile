FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8102
ADD extraction-config-0.0.1.jar extracting-configurationGit.jar
# RUN bash -c 'touch /extracting-configurationGit.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/extracting-configurationGit.jar" ]