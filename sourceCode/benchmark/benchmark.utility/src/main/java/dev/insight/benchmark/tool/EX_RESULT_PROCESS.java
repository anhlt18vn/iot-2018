package dev.insight.benchmark.tool;

import dev.insight.rdf_util.FileUtil;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * dev.insight.benchmark.tool
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  09/01/18.
 */
public class EX_RESULT_PROCESS
{
    private String dataSetName;
    private String deviceName;
    private String Engine;
    private int    numLines;
    private int    scale;

    private String path_to_file_result;

    String[] queryName = new String[]{"F1", "F2", "F3", "F4", "F5", "L1", "L2", "L3", "L4", "L5", "S1", "S2", "S3", "S4", "S5", "S6"};

    private EX_RESULT_PROCESS(String dataSetName, String deviceName, int numLines, String Engine, int scale)
    {
        this.dataSetName = dataSetName;

        this.deviceName = deviceName;

        this.Engine      = Engine;

        this.numLines    = numLines;

        this.path_to_file_result = RESULT_PROCESS.pathToResult
                + dataSetName
                + "/" + deviceName
                + "/" + Engine
                + "/";

        this.scale = scale;
    }

    public static EX_RESULT_PROCESS set(String dataSetName, String deviceName, int numLines, String Engine, int scale){
        return new EX_RESULT_PROCESS(dataSetName, deviceName, numLines, Engine, scale);
    }

    public void processInputResult()
    {
        String fileName = dataSetName + "_" + deviceName + "_" + Engine + "_INPUT";

        String fileInput = fileName + ".result";

        String fileInputPlot = fileName + ".plot";

        File file = new File(this.path_to_file_result + fileInput);

        ArrayList<Long> arrayLong = new ArrayList<>();

        ArrayList<String> arrayString = new ArrayList<>();

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String s = bufferedReader.readLine();

            while (s != null)
            {
                arrayLong.add(parseLong(s));

                s = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 1; i< arrayLong.size(); i++)
        {
            long duration = arrayLong.get(i) - arrayLong.get(i-1);

            int speed     = (int) (this.numLines*1000 / duration);

            int size      = i * this.numLines;

            if ((i==1) || (i%scale == 0))
            arrayString.add(size + ", " + speed);
        }

        FileUtil.write(RESULT_PROCESS.pathToPlotData, fileInputPlot, arrayString);
    }

    private long parseLong(String jenaLog)
    {
        int index       = jenaLog.indexOf("\t");

        String toParse  = jenaLog.substring(0, index);

        return Long.parseLong(toParse);
    }

    public String processQuery(String queryName, String result){
        ArrayList<Integer> intArray = new ArrayList<>();

        while (result.contains("\t")){
            int index = result.indexOf("\t");

            String time = result.substring(0, index);

            result = result.substring(index + 1, result.length());

            if (time.length()>1)
            intArray.add(Integer.parseInt(time));
        }

        intArray.add(Integer.parseInt(result));

        Collections.sort(intArray);

        int avg = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i <10; i ++){
            int c = intArray.get(i);
            avg = avg + c;
            min = c < min ? c : min;
            max = c > max ? c : max;
        }

        return queryName + " " + avg/10 + " " + min + " " + max;
    }


    public void processQueryResult(){
        String fileName = dataSetName + "_" + deviceName + "_" + Engine + "_QUERY";

        String fileInput = fileName + ".result";

        String fileInputPlot = fileName + ".plot";

        File file = new File(this.path_to_file_result + fileInput);

        ArrayList<String> arrayString = new ArrayList<>();

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String s = bufferedReader.readLine();

            int queryNameIndex = 0;

            while (s != null)
            {
                if (s.length()>10)
                {
                    arrayString.add(processQuery(queryName[queryNameIndex], s));

                    queryNameIndex++;
                }

                s = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        FileUtil.write(RESULT_PROCESS.pathToPlotData, fileInputPlot, arrayString);
    }
}
