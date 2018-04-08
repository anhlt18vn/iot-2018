PASSWORD=$1;
PATH_TO_VIRTUOSO=$2;
PATH_TO_DATA=$3;
FILE_NAME=$4;
FILE_RESULT=$5

printf "Importing $FILE_NAME ========";

cd "$PATH_TO_VIRTUOSO";

echo "Load ";

echo "$PASSWORD" |  sudo -S ./isql 1111 dba dba exec="ld_dir('$PATH_TO_DATA','$FILE_NAME','http://virtuoso.org');";

echo "Commit";

echo "$PASSWORD" | sudo -S ./isql 1111 dba dba exec="rdf_loader_run (log_enable=>3);" &
wait
echo "$PASSWORD" | sudo -S ./isql 1111 dba dba exec="checkpoint;";

echo "clear";

echo "$PASSWORD" | sudo -S ./isql 1111 dba dba exec="delete from db.dba.load_list;";
