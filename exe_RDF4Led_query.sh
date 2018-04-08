#!/usr/bin/env bash
DATASET=$1;
DEVICE=$2;

echo "ARGS: [DS] [DEVICE] [SIZE] ";
echo ""

if [ -n "$DATASET" ]; then
    echo "DATA SET IS $DATASET"
else
    echo "DATA SET's name must not be null";
    exit 1;
fi

echo ""

if [ -n "$DEVICE" ]; then
    echo "";
else
    exit "Can't define Manufacturer";
fi

echo ""

PATH_TO_EXPERIMENT=$(pwd);

echo "$PATH_TO_EXPERIMENT"

if [ -n "$PATH_TO_EXPERIMENT" ]; then
    echo "Path to experiment $PATH_TO_EXPERIMENT !!! "
else
    echo "Path/To/Experiment need to be specified";
    exit 1;
fi

echo "RDF4LED on $DEVICE";

java -cp rdfengine/rdf4led/benchmark.rdf4led.jar dev.insight.benchmark.rdf4led.RDF4LedQuery "$PATH_TO_EXPERIMENT" "$DEVICE" "$DATASET" 10000000;
