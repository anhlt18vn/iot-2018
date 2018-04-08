pid=$(pidof virtuoso-t);
echo "input saved.txt"; 
sh ./console/virtuoso/virtuoso_input_one_file.sh nghecon123 ../vos/bin/ /home/anhlt185/experiment/data/watdiv160/ saved.txt >> log.txt 
echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS); 
echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS)>> ./result/watdiv160/LENOVO/VIRTUOSO/watdiv160_LENOVO_VIRTUOSO_INPUT.txt 2>&1; 
echo "input watdiv160.nt"; 
sh ./console/virtuoso/virtuoso_input_one_file.sh nghecon123 ../vos/bin/ /home/anhlt185/experiment/data/watdiv160/ watdiv160.nt >> log.txt 
echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS); 
echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS)>> ./result/watdiv160/LENOVO/VIRTUOSO/watdiv160_LENOVO_VIRTUOSO_INPUT.txt 2>&1; 
