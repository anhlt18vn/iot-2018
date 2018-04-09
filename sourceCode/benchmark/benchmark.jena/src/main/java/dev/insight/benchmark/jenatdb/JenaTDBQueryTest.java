package dev.insight.benchmark.jenatdb;

/**
 * dev.insight.benchmark.jenatdb
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le-Tuan
 * <p>
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  21/02/18.
 */
public class JenaTDBQueryTest {
  public static void main(String[] args){
    String[] arg = new String[4];

    arg[0] = "/home/anhlt185/experiment";
    arg[1] = "LENOVO";
    arg[2] = "watdiv100/L5";
    arg[3] = "10000000";

    JenaTDBQuery experiment = new JenaTDBQuery(arg);
    experiment.execute();
  }
}
