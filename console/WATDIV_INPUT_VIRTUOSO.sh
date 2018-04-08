pid=$(pidof virtuoso-t);
echo "input watdiv10M.nt"; 
sh ./console/virtuoso/virtuoso_input_one_file.sh nghecon123 ../vos/bin/ /home/anhlt185/experiment/data/WATDIV/ watdiv10M.nt >> log.txt 
echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS); 
echo $(date +%s%3N) 	 $(cat /proc/${pid}/status |grep VmSize) 	 $(cat /proc/${pid}/status |grep VmRSS)>> ./result/WATDIV/LENOVO/VIRTUOSO/WATDIV_LENOVO_VIRTUOSO_INPUT.txt 2>&1; 
