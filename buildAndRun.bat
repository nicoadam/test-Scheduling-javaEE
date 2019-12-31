@echo off
call mvn clean package
call docker build -t com.test/newtechJavaEE .
call docker rm -f newtechJavaEE
call docker run -d -p 8080:8080 -p 4848:4848 --name newtechJavaEE com.test/newtechJavaEE