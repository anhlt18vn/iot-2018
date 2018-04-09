package dev.insight.benchmark;

import dev.insight.rdf_util.MemoryManager;

import java.io.File;
import java.util.Arrays;

/**
 * InputExperiment
 * <p>
 * Author: Le Tuan Anh
 * <p>
 * Contact: anh.le@deri.org
 * <p>
 * anh.letuan@insight-centre.org
 * <p>
 * 29/10/16
 */


public abstract class InputExperiment<Engine> extends Experiment
{
    protected InputExperiment(RDFEngine engineType, String[] args)
    {
        super(Exp.Input, engineType, args);
    }

    @Override
    public void run()
    {
        Engine engine = initializeEngine();

        File file = new File(getPathToData());

        int count = 1;

        File[] files = file.listFiles();

        Arrays.sort(files);

        for (File input:files)
        {
            if (input.isHidden()) continue;

            if (input.isDirectory()) continue;

            if (input.getName().contains(".txt")) continue;

            long size = getSize(engine);

            System.out.println("Inserting file: " + input.getName() + ": " + size);

            if (size>= this.getSizeOfData()) break;

            this.stopWatch.start();

            doInput(input, engine);

            String toWrite = this.stopWatch.getTimeStamp() +
                       "\t" + this.stopWatch.eslapedTime() +
                       "\t" + count*100000;

            System.out.println(count++ + " file: " + input.getName() + " ---- " + toWrite);
            System.out.println("speed: " + 100000*1000/this.stopWatch.eslapedTime() +"\n\n" );

            MemoryManager.log1();

            resultWriter.write(toWrite + "\n");
        }

        resultWriter.close();

        close(engine);
    }

    protected abstract Engine initializeEngine();

    protected abstract void doInput(File fileInput, Engine engine);

    protected abstract long getSize(Engine engine);

    protected abstract void close(Engine engine);
}
