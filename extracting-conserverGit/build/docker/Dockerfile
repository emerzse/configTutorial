FROM java:8
MAINTAINER Zsolt Golya "emerzse@gmail.com"
VOLUME /tmp
EXPOSE 8103
ADD extraction-config-server-0.0.1.jar extracting-conserverGit.jar
# RUN bash -c 'touch /extracting-conserverGit.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom" ,"-jar", "/extracting-conserverGit.jar" ]