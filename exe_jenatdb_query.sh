#!/usr/bin/env bash
DATASET=$1;
DEVICE=$2;
SIZE=$3;

echo "ARGS: [DS] [DEVICE] [SIZE] ";

if [ -n "$DATASET" ]; then
    echo "DATA SET IS $DATASET"
else
    echo "DATA SET's name must not be null";
    exit 1;
fi

if [ -n "$DEVICE" ]; then
    echo "";
else
    exit "Can't define Manufacturer";
fi

if [ -n "$SIZE" ]; then
    echo "";
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

echo "JENA TDB on $DEVICE";

#java -cp rdfengine/jenatdb/benchmark.jena.jar dev.insight.benchmark.jenatdb.JenaTDBInput "$PATH_TO_EXPERIMENT" "$DEVICE" "$DATASET" "$SIZE";
java -cp rdfengine/jenatdb/benchmark.jena.jar dev.insight.benchmark.jenatdb.JenaTDBQuery "$PATH_TO_EXPERIMENT" "$DEVICE" "$DATASET" "$SIZE";
