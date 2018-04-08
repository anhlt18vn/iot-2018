RESULT_FILE=$1

pid=$(pidof virtuoso-t);

while [ -n "$pid" ];
do
#echo $(printf "%s   %s   %s" $(date +%s%3N) $(cat /proc/${pid}/status |grep VmSize)) 
echo $(printf "%s   %s   %s" $(date +%s%3N) $(cat /proc/${pid}/status |grep VmSize)) >> "$RESULT_FILE"
sleep 2;
pid=$(pidof virtuoso-t);
done 

echo "VIRTUOSO STOPs"
