#!/bin/bash

PID=$(ps -ef | grep hello-spring-web-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]; then
  echo hello-spring-web-0.0.1-SNAPSHOT.jar is already stopped
else
  echo kill $PID
  kill -9 $PID
fi
