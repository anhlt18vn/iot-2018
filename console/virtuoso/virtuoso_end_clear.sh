#!/usr/bin/env bash
PASSWORD=$1;
PATH_TO_VIRTUOSO=$2;

echo "stop virtuoso";

cd $PATH_TO_VIRTUOSO

echo $PASSWORD | sudo -S ./isql 1111 dba dba exec="shutdown();";


echo $PASSWORD | sudo -S rm ../var/lib/virtuoso/db/*.*;




