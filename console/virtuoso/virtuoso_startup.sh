#!/usr/bin/env bash
PASSWORD=$1;
PATH_TO_VIRTUOSO=$2;
FILE_MEM_RESULT=$3;

printf "Virtuoso is starting...";

cd $PATH_TO_VIRTUOSO;

echo "$PASSWORD" | sudo -S ./isql 1111 dba dba exec="shutdown();" &
wait
echo "$PASSWORD" | sudo -S ./virtuoso-t virtuoso.ini

#Wait until sever online

until $(curl --output /dev/null --silent --head --fail http://localhost:8890); do
    printf '.'
    sleep 5
done

echo "Virtuoso is ready";
