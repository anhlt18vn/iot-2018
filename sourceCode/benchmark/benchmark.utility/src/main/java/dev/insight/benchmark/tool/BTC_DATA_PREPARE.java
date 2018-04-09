package dev.insight.benchmark.tool;

import dev.insight.rdf_util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * tools
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  26/09/17.
 */
public class BTC_DATA_PREPARE
{
    private static String folderDestination = "/home/anhlt185/Desktop/data/btc/";

    private static String folderInput       = "/home/anhlt185/Desktop/data/btc_/";

    public static void main(String[] args)
    {
//        FileUtil.extract(folderInput + "btc_chunk_0.nt",
//                            folderDestination,
//                        "btc",
//                        40000,
//                        1,
//                        50);
//
//        FileUtil.extract(folderInput + "btc_chunk_1.nt",
//                        folderDestination,
//                "btc",
//                40000,
//                51,
//                100);
//
//        FileUtil.extract(folderInput + "btc_chunk_2.nt",
//                 folderDestination,
//                "btc",
//                40000,
//                101,
//                150);
//
//        FileUtil.extract(folderInput + "btc_chunk_3.nt",
//                 folderDestination,
//                "btc",
//                40000,
//                151,
//                200);
//
//        FileUtil.extract(folderInput + "btc_chunk_4.nt",
//                 folderDestination,
//                "btc",
//                40000,
//                201,
//                250);

        try {
            PrintStream printStreamFile = new PrintStream(new BufferedOutputStream(new FileOutputStream(folderInput + "btc_result_triple.nt")));

            System.setOut(printStreamFile);

            fromQuadToTriple(folderInput + "result.nt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static void fromQuadToTriple(String fileInput)
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInput));

            String s = bufferedReader.readLine();

            while (s!=null)
            {
                System.out.println(Q2T(s));

                s = bufferedReader.readLine();
            }

            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String Q2T(String s)
    {
        int lastPos = s.lastIndexOf("<");

        s = s.substring(0, lastPos);

        s = s + " .";

        return s;
    }


//    public static void renameFiles() throws Exception
//    {
//        for (int i=1;i<10;i++)
//        {
//            File fileOld = new File(folderDestination +          i +".nt");
//            File fileNew = new File(folderDestination +"btc00" + i +".nt");
//
//            fileOld.renameTo(fileNew);
//        }
//
//        for (int i=10; i<100;i++)
//        {
//            File fileOld = new File(folderDestination +          i +".nt");
//            File fileNew = new File(folderDestination +"btc0"  + i +".nt");
//
//            fileOld.renameTo(fileNew);
//        }
//
//        for (int i=100; i<=250;i++)
//        {
//            File fileOld = new File(folderDestination +          i +".nt");
//            File fileNew = new File(folderDestination +"btc0"  + i +".nt");
//
//            fileOld.renameTo(fileNew);
//        }
//    }


//    public static void concatFile(String fileInput, String folderOut, String prefix, int numberOfLine, int form, int to)
//    {
//        try
//        {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderInput + fileInput));
//
//            int start = form;
//
//            List<String> sList = new ArrayList<>();
//
//            while (start<=to)
//            {
//                String s = bufferedReader.readLine();
//
//                if (s == null) break;
//
//                if (s.contains("http://www.w3.org/2001/XMLSchema#gYearMonth")
//                        ||s.contains("http://www.w3.org/2001/XMLSchema#gYear")
//                        ||s.contains("\"Infinity\"^^<http://www.w3.org/2001/XMLSchema#double>")
//                        )
//                {
//                    continue;
//                }
//
//                if (!sList.contains(s))
//                { sList.add(s); }
//
//                if (sList.size() == numberOfLine)
//                {
//                    System.out.println(start);
//
//                    String filename="";
//
//                    if (start < 10) filename = prefix + "00" + start + ".nt";
//                    else
//                    if (start <100) filename = prefix + "0" + start + ".nt";
//                    else filename = prefix + start + ".nt";
//
//                    FileUtil.write(folderOut, filename, sList);
//
//                    start++;
//
//                    sList.clear();
//                }
//
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
