#!/usr/bin/env bash
DATASET=$1;
DEVICE=$2;

#if [ -n "$PASSWORD" ]; then
#    echo "PASSWORD IS OK !!! "
#else
#    echo "PASSWORD must not be null";
#    exit 1;
#fi

if [ -n "$DATASET" ]; then
    echo "DATA SET IS $DATASET"
else
    echo "DATA SET's name must not be null";
    exit 1;
fi

#DEVICE=$(echo "$PASSWORD" | sudo -S dmidecode -t system | grep "Manufacturer");

if [ -n "$DEVICE" ]; then
    echo "$DEVICE";
else
    exit "Can't define Manufacturer";
fi

PATH_TO_EXPERIMENT=$(pwd);

echo "$PATH_TO_EXPERIMENT"

if [ -n "$PATH_TO_EXPERIMENT" ]; then
    echo "Path to experiment $PATH_TO_EXPERIMENT !!! "
else
    echo "Path/To/Experiment need to be specified";
    exit 1;
fi

echo "JENATDB on $DEVICE";

java -Xmx200m -cp rdfengine/jenatdb/benchmark.jena.jar dev.insight.benchmark.jenatdb.JenaTDBInput "$PATH_TO_EXPERIMENT" "$DEVICE" "$DATASET" 10000000;
