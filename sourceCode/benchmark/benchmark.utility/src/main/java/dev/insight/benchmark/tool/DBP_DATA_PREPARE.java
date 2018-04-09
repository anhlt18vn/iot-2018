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
public class DBP_DATA_PREPARE
{
    private static String folderDestination = "/home/anhlt185/Desktop/data/dbp/";

    private static String folderInput       = "/home/anhlt185/Desktop/data/dbp_/";

    public static void main(String[] args)
    {
//        concatFile("infobox.ttl",           folderDestination,   1,119);
//        concatFile("mapping_object.ttl",    folderDestination, 120, 219);
//        concatFile("interlanguage.ttl",     folderDestination, 220, 250);

        correctXMLdate("/home/anhlt185/experiment/data/DBP/dbp001.nt","");
    }

    public static void correctXMLdate(String fileInput, String fileOutput)
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInput));

            String s = bufferedReader.readLine();

            ArrayList<String> list = new ArrayList<>();

            while (s!=null)
            {
                if (s.contains("<http://www.w3.org/2001/XMLSchema#date>"))
                {
//                    if (getDate(s).length() != 10)
//                    {
//                        s = correctXMLDate(s);
//                    }

                    s = correctXMLDate(s);
                }

                list.add(s);

                s = bufferedReader.readLine();
            }

            FileUtil.write("/home/anhlt185/experiment/data/DBP/", "dbp001_nt", list);
        }
        catch (Exception e)
        {

        }
    }

    public static String getDate(String s)
    {
        int i = s.indexOf("\"");

        String o = s.substring(i, s.length());

        i = o.indexOf("^");

        String date = o.substring(1,i-1);

        return date;
    }


    public static String correctXMLDate(String s)
    {
        String date = getDate(s);

        String new_date;

        String year = date.substring(0, date.indexOf("-"));
        String month= date.substring(date.indexOf("-") +1 , date.lastIndexOf("-"));
        String day  = date.substring(date.lastIndexOf("-")+1, date.length());

        while (year.length() != 4)
        {
            year = "0" + year;
        }

        while (month.length() != 2)
        {
            month = "0" + month;
        }

        if (month.equals("00")) month = "01";

        while (day.length() != 2)
        {
            day = "0" + day;
        }

        if (day.equals("00")) day = "01";

        System.out.println(date);

        new_date= year + "-" + month + "-" + day;

        String new_s = s.replace(date, new_date);

        System.out.println(s);

        System.out.println(new_s);

        return new_s;
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

                sList.add(s);

                if (sList.size() == 40000)
                {
                    System.out.println(start);

                    String filename="";

                    if (start < 10) filename = "dbp00" + start + ".nt";
                    else
                    if (start <100) filename = "dbp0" + start + ".nt";
                    else filename = "dbp" + start + ".nt";

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
