FROM registry.cn-hangzhou.aliyuncs.com/disney-joint-union/java8-dockerize
MAINTAINER Lory Hou <lory.hou@augmentum.com>

RUN mkdir -p /srv/alct/log/ /etc/service/eureka/ 

COPY eureka-server.jar /srv/eureka/eureka-server.jar
COPY conf/start.sh /etc/service/eureka/run
RUN chmod +x /etc/service/eureka/run
EXPOSE 8761
