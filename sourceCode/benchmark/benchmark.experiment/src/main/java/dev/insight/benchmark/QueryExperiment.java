package dev.insight.benchmark;


import dev.insight.rdf_util.FileUtil;

import java.io.*;
import java.util.Arrays;

/**
 * QueryExperiment
 * <p>
 * Author: Le Tuan Anh
 * <p>
 * Contact: anh.le@deri.org
 * <p>
 * anh.letuan@insight-centre.org
 * <p>
 * 31/10/16
 */


public abstract  class QueryExperiment<Engine> extends Experiment
{
    protected QueryExperiment(RDFEngine engineType, String[] args)
    {
        super(Exp.Query, engineType, args);

        try
        {
            this.printStreamTempFile = new PrintStream(new BufferedOutputStream(new FileOutputStream(File.createTempFile(this.fileResult , ".rs"))));

            this.printStreamFile = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.getPathToResult() + "/" + this.fileResult + ".rs")), true);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    @Override
//    public void run()
//    {
//        Engine engine = intializeEngine();
//
//        File queries  = new File(getPathToQuery());
//
//        File[] listFiles= queries.listFiles();
//
//        Arrays.sort(listFiles);
//
//        for (File file:listFiles) {
//
//            if (file.isHidden()) continue;
//
//            if (file.getName().contains("~")) continue;
//
//            if (file.isDirectory()) continue;
//
//            System.out.println("Query " + file.getName() + " is running");
//
//            String queryString = FileUtil.readStringFromFile(file);
//
//            String[] listQueries = readListQueries(queryString);
//
//            if (listQueries.length <= 2) {
//                queryOne(listQueries[0], engine);
//            } else {
//                queryMany(listQueries, engine);
//            }
//        }
//
//        resultWriter.close();
//    }

    @Override
    public void run()
    {
        Engine engine = intializeEngine();

        File queries  = new File(getPathToQuery());

        doQueryFolder(queries, engine);

        resultWriter.close();
    }

    private void doQueryFolder(File queries, Engine engine){

        File[] listFiles= queries.listFiles();

        Arrays.sort(listFiles);

        for (File file:listFiles) {

            if (file.isHidden()) continue;

            if (file.getName().contains("~")) continue;

            if (file.isDirectory()) {
                doQueryFolder(file, engine);
            }

            System.out.println("Query " + file.getName() + " is running");

            String queryString = FileUtil.readStringFromFile(file);

            String[] listQueries = readListQueries(queryString);

            if (listQueries.length <= 2) {
                queryOne(listQueries[0], engine);
            } else {
                queryMany(listQueries, engine);
            }
        }

    }

    private void queryMany(String[] queries, Engine engine){

        String toWrite = "";

        System.out.println(queries[0]);

        System.setOut(this.printConsole);

//        stopWatch.start();
//
//        doQueries(queries[0], engine);
//
//        System.out.println(stopWatch.eslapedTime());
//
//        System.setOut(this.printStreamTempFile);

        for (int index=0; index<queries.length-1; index ++)
        {
            stopWatch.start();

            doQueries(queries[index], engine);

            long time   = stopWatch.eslapedTime();

            toWrite = toWrite + "\t" + time;
        }

        System.setOut(this.printConsole);

        System.out.println(toWrite);

        resultWriter.write(toWrite + "\n\n");
    }

    private void queryOne(String queryString, Engine engine){

        String toWrite = "";

        System.setOut(this.printConsole);

        System.out.println(queryString);

//        stopWatch.start();
//
//        doQueries(queryString, engine);
//
//        System.out.println(stopWatch.eslapedTime());

//        System.setOut(this.printStreamTempFile);

        for (int i = 0; i < 1; i++)
        {
            stopWatch.start();

            doQueries(queryString, engine);

            long time   = stopWatch.eslapedTime();

            toWrite = toWrite + "\t" + time;
        }

        System.setOut(this.printConsole);

        System.out.println(toWrite);

        resultWriter.write(toWrite + "\n\n");
    }


    protected String[] readListQueries(String listQuery){
      String[] part = listQuery.split("\n\n");

      return part;
    }

    protected abstract Engine intializeEngine();

    protected abstract void doQueries(String queryString, Engine engine);

    protected long getSize(Engine engine){ return  0;}

    protected abstract void printResult(Object resultSet);
}
