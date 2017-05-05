FROM registry.cn-hangzhou.aliyuncs.com/disney-joint-union/java8-dockerize
MAINTAINER Lory Hou <lory.hou@augmentum.com>

RUN mkdir -p /srv/alct/log/ /etc/service/config/ 

COPY config-server.jar /srv/config/config-server.jar
COPY conf/start.sh /etc/service/config/run
RUN chmod +x /etc/service/config/run
EXPOSE 8099
