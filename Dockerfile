FROM ubuntu:latest

# Install Common Tool
RUN apt-get update
RUN apt-get install software-properties-common -y
RUN apt-get install wget -y

# Install JDK 8
RUN apt-get -y install openjdk-8-jdk wget

# Install Tomcat
RUN mkdir /usr/local/tomcat
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.24/bin/apache-tomcat-9.0.24.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp \ && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-9.0.24/* /usr/local/tomcat/
RUN useradd -r -m -U -d /usr/local/tomcat/ -s /bin/false tomcat
RUN chown -RH tomcat: /usr/local/tomcat/

WORKDIR /usr

ADD ./target/promotion-storage.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh","run"]
