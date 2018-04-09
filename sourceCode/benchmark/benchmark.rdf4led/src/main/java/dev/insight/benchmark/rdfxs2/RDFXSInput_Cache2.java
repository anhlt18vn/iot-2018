package dev.insight.benchmark.rdfxs2;

import dev.insight.benchmark.InputExperiment;
import dev.insight.benchmark.ResultWriter;
import dev.insight.erdf.Config;
import dev.insight.erdf.Context;
import dev.insight.erdf.ERDFContext2;
import dev.insight.erdf.graph.parser.ParserNTriple;
import dev.insight.erdf.graph.storage.GraphStore2;
import dev.insight.rdf_util.MemoryManager;
import dev.insight.erdf.utility.Log;

import java.io.File;

/**
 * PACKAGE_NAME
 *
 * <p>TODO: Add class description
 *
 * <p>Author: Anh Le_Tuan Email: anh.letuan@insight-centre.org
 *
 * <p>Date: 04/10/17.
 */
public class RDFXSInput_Cache2 extends InputExperiment<GraphStore2> {

    public ERDFContext2 context;

    ResultWriter resultWriter;

    protected RDFXSInput_Cache2(String[] args)
    {
        super(RDFEngine.RDF4LED, args);

        resultWriter =
                new ResultWriter("RDF_XS2_cache_history.plot", this.pathToExperiment + "/gnuscript/data/");
    }

    @Override
    protected GraphStore2 initializeEngine() {

        context = new ERDFContext2(this.getPathToStore());

        GraphStore2 graphStore = (GraphStore2) context.getGraphByName("http://rdf4led.org");

        return graphStore;
    }

    int i = 0;

    @Override
    protected void doInput(File fileInput, GraphStore2 graphStore) {
        ParserNTriple<Integer> parser = new ParserNTriple<>(this.context);

        graphStore = (GraphStore2) parser.parseToGraphFromFile(fileInput, graphStore);

        MemoryManager.log1();

        Log.print();


        resultWriter.write(i * 42000 + " " + Log.cacheHistoryToString());

        Log.reset();

        i++;
    }

    @Override
    protected long getSize(GraphStore2 graphStore) {
        return i * 42000;
    }

    @Override
    protected void close(GraphStore2 graphStore) {
        context.sync();

        graphStore.sync();
    }

    protected RDFXSInput_Cache2(RDFEngine engineType, String[] args) {
        super(engineType, args);
    }

    public static void main(String[] args)
    {
        String[] arg = new String[5];

        arg[0] = "/home/anhlt185/experiment";
        arg[1] = "LENOVO";
        arg[2] = "WATDIV";
        arg[3] = "10000000";
        Config.setMaxMemory(140);

        RDFXSInput_Cache2 experiment = new RDFXSInput_Cache2(arg);
        experiment.execute();
    }

}
