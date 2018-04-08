#!/usr/bin/env bash
DATASET=$1;
DEVICE=$2;

if [ -n "$DATASET" ]; then
    echo "DATA SET IS $DATASET"
else
    echo "DATA SET's name must not be null";
    exit 1;
fi

if [ -n "$DEVICE" ]; then
    DEVICE=$(echo ${DEVICE#*:});
else
    echo "Can't define Manufacturer";
    exit 1;
fi

PATH_TO_EXPERIMENT=$(pwd);

echo "$PATH_TO_EXPERIMENT"

if [ -n "$PATH_TO_EXPERIMENT" ]; then
    echo "Path to experiment $PATH_TO_EXPERIMENT !!! "
else
    echo "Path/To/Experiment need to be specified";
    exit 1;
fi

echo "RDF4Led on $DEVICE";

java  -cp rdfengine/rdf4led/benchmark.rdf4led.jar dev.insight.benchmark.rdf4led.RDF4LedInput "$PATH_TO_EXPERIMENT" "$DEVICE" "$DATASET" "1000000";
