package dev.insight.benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ResultWriter
 * <p>
 * Author: Le Tuan Anh
 * <p>
 * Contact: anh.le@deri.org
 * <p>
 * anh.letuan@insight-centre.org
 * <p>
 * 29/10/16
 */


public class ResultWriter
{
    private String fileOut;

    private BufferedWriter bw;

    public ResultWriter(String fileOut, String folderOut)
    {
        this.fileOut = fileOut;

        File file = new File(folderOut);

        if (!file.isDirectory()) {file.mkdirs();}

        try
        {
            bw = new BufferedWriter(new FileWriter(folderOut + "/" + this.fileOut));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void write(String toWrite)
    {
        try
        {
            bw.write(toWrite);

            bw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            bw.flush();

            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}