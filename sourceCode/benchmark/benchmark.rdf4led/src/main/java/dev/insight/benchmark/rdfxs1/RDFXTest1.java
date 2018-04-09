package dev.insight.benchmark.rdfxs1;

/**
 * dev.insight.benchmark.rdf4led
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  04/10/17.
 */
public class RDFXTest1
{
    public static void main(String[] args)
    {
        input();
    }

    public static void input()
    {
        String[] args = new String[4];

        args[0] = "/home/anhlt185/experiment";
        args[1] = "LENOVO";
        args[2] = "BSBM";
        args[3] = "10000000";

        RDFXSInput1 experiment = new RDFXSInput1(args);
        experiment.execute();
    }
}
