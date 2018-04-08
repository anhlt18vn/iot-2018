#!/usr/bin/env bash
pid=$(pidof virtuoso-t);

PASSWORD=$1;
PATH_TO_QUERIES=$2;
FILE_NAME=$3;
PATH_TO_VIRTUOSO=$4;
PATH_TO_RESULT=$5;

clear;

echo "QUERY $FILE_NAME";

str=$(cat $PATH_TO_QUERIES$FILE_NAME)   # read file into string

str=${str//$'\n'$'\n'/$'['}              # 2 newlines to 1 tab

while [[ "$str" =~ $'\t'$'\n' ]] ; do
  str=${str//$'\t'$'\n'/$'\t'}          # eat up further newlines
done

IFS=$'['                               # field separator is now tab
result=( $str )                         # slit into array

cnt=0

cd $PATH_TO_VIRTUOSO

for x in ${result[@]}; do               # print result
  ((cnt++))
  echo -e "--- group $cnt ---\n"
  echo "$x"
  echo "$PASSWORD" | sudo -S ./isql 1111 dba dba exec="SPARQL $x;" #> /dev/null &
  echo "$FILE_NAME" $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS);
  echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS) >> "$PATH_TO_RESULT" 2>&1;
  wait
done
