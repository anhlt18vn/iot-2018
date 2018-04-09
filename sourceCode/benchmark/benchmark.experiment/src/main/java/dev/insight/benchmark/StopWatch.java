package dev.insight.benchmark;

/**
 * dev.insight.eva.util
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  27/09/17.
 */
public class StopWatch
{
    long start;

    public StopWatch() {}

    public void start()
    {
        start = System.currentTimeMillis();
    }

    public long eslapedTime()
    {
        return System.currentTimeMillis() -start;
    }

    public long getTimeStamp()
    {
        return System.currentTimeMillis();
    }
}
