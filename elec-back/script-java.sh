#!/bin/sh
SERVICE_NAME=SERVICE_NAME # service name 
PATH_TO_JAR=./target/elec-back-0.0.1-SNAPSHOT.jar # service jar file
PID_PATH_NAME=/tmp/SERVICE_NAME-pid # pid name 
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -jar  $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null & # -Dspring.profiles.active=dev
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -jar -Dspring.profiles.active=dev $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null & # -Dspring.profiles.active=dev
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac 