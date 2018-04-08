#!/usr/bin/env bash

PASSWORD=$1;
PATH_TO_QUERIES=$2;
FILE_NAME=$3;
PATH_TO_VIRTUOSO=$4;

clear;

echo "QUERY $FILE_NAME";

QUERY="$(cat $PATH_TO_QUERIES$FILE_NAME)"

cd $PATH_TO_VIRTUOSO

echo "$PASSWORD" | sudo -S ./isql 1111 dba dba exec="SELCT COUNT(*) FROM (SPARQL $QUERY);" &
wait

