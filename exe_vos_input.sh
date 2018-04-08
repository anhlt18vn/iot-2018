#!/usr/bin/env bash
PASSWORD=$1;
DATASET=$2;
DEVICE=$3;

if [ -n "$PASSWORD" ]; then
     echo "PASSWORD IS OK !!! "
else
    echo "PASSWORD must not be null";
    exit 1;
fi

if [ -n "$DATASET" ]; then
    echo "DATA SET IS $DATASET"
else
    echo "DATA SET's name must not be null";
    exit 1;
fi

echo ""

if [ -n "$DEVICE" ]; then
    DEVICE=$(echo ${DEVICE#*:});
else
    exit "Can't define Manufacturer";
fi

#===================================================================================================================================
#setup wdc
echo "Setting up shell script for virtuoso"
java -cp util/benchmark.utility.jar dev.insight.benchmark.tool.VOS_UTIL  "$DEVICE" ./console/ ./data/"$DATASET"/ ./queries/"$DATASET"/ ./result/ ../vos/bin/ "$DATASET" "$PASSWORD";

#Start Virtuoso
sh ./console/virtuoso/virtuoso_startup.sh "$PASSWORD" "../vos/bin/"

#Start mem monitoring for input
#sh ./console/virtuoso/virtuoso_mem_monitor.sh result/"$DATASET"/"$DEVICE"/VIRTUOSO/"$DATASET"_"$DEVICE"_VIRTUOSO_MEM.txt &

#run input btc
echo "VIRTUOSO Input - DATA SET $DATASET";
sh ./console/"$DATASET"_INPUT_VIRTUOSO.sh;

#run query wdc
#echo "VIRTUOSO QUERY - DATA SET $DATASET";
#sh ./console/"$DATASET"_QUERY_VIRTUOSO.sh

#End virtuoso and clear;
#echo "Stopping virtuoso and clear data";
#sh ./console/virtuoso/virtuoso_end_clear.sh "$PASSWORD" "../vos/bin/";

