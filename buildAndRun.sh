#!/bin/sh
mvn clean package && docker build -t com.test/newtechJavaEE .
docker rm -f newtechJavaEE || true && docker run -d -p 8080:8080 -p 4848:4848 --name newtechJavaEE com.test/newtechJavaEE