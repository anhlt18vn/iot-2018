# Guidelines for running evaluation supporting iswc2018 submission.
## I. Guidelines for install os on devices:
  - Galileo Gen II: https://software.intel.com/en-us/get-started-galileo-windows
  - Raspberry Pi Zero: https://cdn-learn.adafruit.com/downloads/pdf/raspberry-pi-zero-creation.pdf
  - Beagle Bone Black: https://beagleboard.org/getting-started  
## II. Setup experiments:
  - Clone this repo in <home_dir>.
  - Location for the data should be in : <home_dir>/iswc2018/data/<data_set_name>/
  - Location for the queries corresponding to the dataset should be in: <home_dir>/iswc2018/queries/<data_set_name>/
  - Location for the experiment binary should be in: <home_dir>/iswc2018/rdfengine/
  - Location for the source code of the experiment is in: <home_dir>/iswc2018/sourcCode/
  - Source code of RDF4Led can be found at: https://github.com/anhlt18vn/rdf4led
  - Virtuoso should be compiled and placed at: <home_dir>/vos/
## III. Run the experiments:
  - Virtuoso:
    - Input: sh exe_vos_input.sh <root_password> <data_set_name> <device_name>
    - Query: sh exe_vos_query.sh <root_password> <data_set_name> <device_name>
  - RDF4Led:
    - Input: sh exe_RDF4Led_input.sh <data_set_name> <device_name>
    - Query: sh exe_RDF4Led_query.sh <data_set_name> <device_name>
  - Jena TDB:
    - Input: sh exe_jenatdb_input.sh <data_set_name> <device_name>
    - Query: sh exe_jenatdb_input.sh <data_set_name> <device_name>
    
    The results will be produced in folder: <home_dir>/iswc2018/results/. Jena TDB and RDF4Led will store their 
    data in <home_dir>/iswc2018/store/JENATDB/ and <home_dir>iswc2018/store/RDF4Led/.
