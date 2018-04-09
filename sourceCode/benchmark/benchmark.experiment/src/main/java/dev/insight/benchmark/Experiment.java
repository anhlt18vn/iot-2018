package dev.insight.benchmark;

import dev.insight.rdf_util.FileUtil;
import dev.insight.rdf_util.MemoryManager;

import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;

/**
 * Experiment
 * <p>
 * Author: Le Tuan Anh
 * <p>
 * Contact: anh.le@deri.org
 * <p>
 * anh.letuan@insight-centre.org
 * <p>
 * 29/10/16
 */


//[datasetName]_[deviceName]_[]

public abstract class Experiment
{
    protected enum Exp{Input, Query, Reasoning}

    protected enum Device{Desk, BB_BLACK, RASP_Pi0, RASP_PiII, GALILEO, NEXUS4, NEXUS7, CHIP}

    protected enum RDFEngine{RDF_XS1, RDF4LED, BLAZE_GRAPH, JENATDB}

    protected final String pathToExperiment;

    protected String pathToData;

    protected String pathToStore;

    protected String pathToQuery;

    protected String pathToResult;

    protected String nameOfDevice;

    protected String nameOfEngine;

    protected String sizeOfData;

    protected String nameOfExp;

    protected String nameOfData;

    protected Exp  experiment;

    protected RDFEngine  rdfEngine;

    protected ResultWriter resultWriter;

    protected MemoryMonitoring memoryMonitoring;

    protected final StopWatch stopWatch;

    protected String fileResult;

    public PrintStream printStreamFile;

    public PrintStream printStreamTempFile;

    public PrintStream printConsole;

    protected Experiment(Exp experiment, RDFEngine rdfEngine, String[] args)
    {
        this.stopWatch = new StopWatch();

        this.pathToExperiment = args[0];

        this.experiment = experiment;

        this.rdfEngine = rdfEngine;

        this.nameOfDevice = args[1];

        this.nameOfData = args[2];

        switch (this.rdfEngine)
        {
            case RDF_XS1:
            {
                this.nameOfEngine = "RDF_XS1";

                break;
            }

            case RDF4LED:
            {
                this.nameOfEngine = "RDF4LED";

                break;
            }

            case BLAZE_GRAPH:
            {
                this.nameOfEngine = "BLAZEGRAPH";

                break;
            }

            case JENATDB:
            {
                this.nameOfEngine = "JENATDB";

                break;
            }
        }

        switch (experiment)
        {
            case Input:
            {
                pathToData       = resolvePath(this.pathToExperiment + "/data/" + this.nameOfData + "/");

                pathToStore      = resolvePath(this.pathToExperiment + "/store/" + this.nameOfEngine + "/");

                this.nameOfExp   = "INPUT";

                FileUtil.deleteDirectory(new File(this.pathToStore));

                break;
            }
            case Query:
            {
                pathToQuery      = resolvePath(this.pathToExperiment + "/queries/" + this.nameOfData + "/");

                pathToStore      = resolvePath(this.pathToExperiment + "/store/" + this.nameOfEngine + "/");

                this.nameOfExp   = "QUERY";

                break;
            }
            case Reasoning:
            {
                break;
            }
        }

        this.sizeOfData   = args.length>3?args[3]:null;

        this.pathToResult = resolvePath(this.pathToExperiment + "/result/" + this.nameOfData + "/" + this.nameOfDevice + "/" + this.nameOfEngine + "/");

        this.fileResult =   resolveDataName(this.nameOfData) + "_" + this.nameOfDevice + "_" + this.nameOfEngine + "_" + this.nameOfExp;

        this.printConsole = System.out;

        experimentInfo();

        this.resultWriter = getResultWriter();
    }


    protected String getPathToData()
    {
        return this.pathToData;
    }

    protected String getPathToQuery()
    {
        return this.pathToQuery;
    }

    protected String getPathToResult()
    {
        return this.pathToResult;
    }

    protected String getPathToStore()
    {
        return this.pathToStore;
    }

    protected long getSizeOfData()
    {
        return sizeOfData==null?Integer.MAX_VALUE:Long.parseLong(sizeOfData);
    }

    protected void experimentInfo()
    {
        System.out.println("Running Experiment " + this.nameOfExp + " with " + this.nameOfEngine);

        System.out.println("Device : " + this.nameOfDevice);

        System.out.println("Data set: " + this.nameOfData);

        System.out.println("Data path: " + this.pathToData);

        if (pathToStore != null)
        {
            System.out.println("Path to Storage: " + pathToStore);
        }

        if (pathToQuery != null)
        {
            System.out.println("Path to Queries: " + pathToQuery);
        }

        System.out.println("Path to Result: " + pathToResult);
    }

    protected String resolvePath(String s)
    {
        if (s == null) return null;

        while (s.contains("//"))
        {
            s = s.replace("//", "/");
        }
        return  s;
    }

    protected String resolveDataName(String dataName){
        if (dataName.contains("/")) {
            dataName = dataName.replace("/", "_");
        }

        return dataName;
    }


    protected ResultWriter getResultWriter()
    {
        return new ResultWriter(this.fileResult + ".result", this.pathToResult);
    }

    public void execute()
    {
//        try {

            //memoryMonitoring = new MemoryMonitoring(this.fileResult + ".mem", this.pathToResult);
            //Thread thread    = new Thread(memoryMonitoring);

            try
            {

                run();

            }
            catch (OutOfMemoryError e)
            {
                e.printStackTrace();

                System.exit(0);
            }

            //memoryMonitoring.stop();

            //thread.join();
//        }
//        catch (InterruptedException e)
//        {
//            throw new RuntimeException(e.getCause());
//        }
    }

    protected abstract void run();
}
