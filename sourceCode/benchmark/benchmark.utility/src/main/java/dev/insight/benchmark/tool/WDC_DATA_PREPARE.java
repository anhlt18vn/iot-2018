package dev.insight.benchmark.tool;

import dev.insight.rdf_util.FileUtil;

import java.io.BufferedReader;
import java.io.FileReader;
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
 * Date:  27/09/17.
 */
public class WDC_DATA_PREPARE
{
    private static String folderDestination = "/home/anhlt185/Desktop/data/wdc/";

    private static String folderInput       = "/home/anhlt185/Desktop/data/wdcSUBSET/";

    public static void main(String[] args)
    {
        concatFile("html-rdfa-00064.nq.sort" , folderDestination, 114 , 119);
        concatFile("html-rdfa-00065.nq.sort" , folderDestination, 120 , 129);
        concatFile("html-rdfa-00066.nq.sort" , folderDestination, 130 , 139);
        concatFile("html-rdfa-00067.nq.sort" , folderDestination, 140 , 149);
        concatFile("html-rdfa-00068.nq.sort" , folderDestination, 150 , 159);
        concatFile("html-rdfa-00069.nq.sort" , folderDestination, 160 , 169);
        concatFile("html-rdfa-00070.nq.sort" , folderDestination, 170 , 179);
        concatFile("html-rdfa-00071.nq.sort" , folderDestination, 180 , 189);
        concatFile("html-rdfa-00072.nq.sort" , folderDestination, 190 , 199);
        concatFile("html-rdfa-00073.nq.sort" , folderDestination, 200 , 209);
        concatFile("html-rdfa-00074.nq.sort" , folderDestination, 210 , 219);
        concatFile("html-rdfa-00075.nq.sort" , folderDestination, 220 , 229);
        concatFile("html-rdfa-00076.nq.sort" , folderDestination, 230 , 239);
        concatFile("html-rdfa-00077.nq.sort" , folderDestination, 240 , 250);
        }



    private static String Q2T(String s)
    {
        int lastPos = s.lastIndexOf("<");

        s = s.substring(0, lastPos);

        s = s + " .";

        return s;
    }

    public static void concatFile(String fileInput, String folderOut, int form, int to)
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderInput + fileInput));
            int start = form;

            List<String> sList = new ArrayList<>();

            while (start<=to)
            {
                String s = bufferedReader.readLine();

                if (s == null) break;

                if (s.contains("http://www.w3.org/2001/XMLSchema#gYearMonth")
                        ||s.contains("http://www.w3.org/2001/XMLSchema#gYear")
                        ||s.contains("\"Infinity\"^^<http://www.w3.org/2001/XMLSchema#double>")
                        )
                {
                    continue;
                }

                s = Q2T(s);

                if (!sList.contains(s)) sList.add(s);

                if (sList.size() == 40000)
                {
                    System.out.println(start);

                    String filename="";

                    if (start < 10) filename = "wdc00" + start + ".nt";
                    else
                    if (start <100) filename = "wdc0" + start + ".nt";
                    else filename = "wdc" + start + ".nt";

                    FileUtil.write(folderOut, filename, sList);

                    start++;

                    sList.clear();
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
