package dev.insight.benchmark;


import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;


/**
 * Created by anhlt185 on 26/04/17.
 */
public class MemoryMonitoring implements Runnable
{
    OperatingSystemMXBean bean;

    ResultWriter resultWriter;

    private boolean isStopped;

    private StopWatch stopWatch;

    public MemoryMonitoring(String fileOut, String folderOut)
    {
        this.isStopped = false;

        resultWriter = new ResultWriter(fileOut, folderOut);

        bean    = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        stopWatch = new StopWatch();
    }

    public void run()
    {
        while(!isStopped)
        {
            try
            {
                long free = Runtime.getRuntime().freeMemory();

                long total =  Runtime.getRuntime().totalMemory();

                long consume = free - total;

                String s = stopWatch.getTimeStamp() + "\t" + free + "\t" + total + "\t" + consume + "\n";

                resultWriter.write(s);

                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public void stop()
    {
        isStopped = true;
    }
}
