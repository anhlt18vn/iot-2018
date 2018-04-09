package dev.insight.benchmark.rdfxs1;

import dev.insight.benchmark.InputExperiment;
import dev.insight.benchmark.ResultWriter;
import dev.insight.erdf.Context;
import dev.insight.erdf.graph.parser.ParserNTriple;
import dev.insight.erdf.utility.Log;
import dev.insight.erdf1.ERDFContext1;
import dev.insight.erdf1.GraphStore1;

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
public class RDFXSInput_Cache1 extends InputExperiment<GraphStore1> {

    public Context<Integer> context;

    ResultWriter resultWriter;

    protected RDFXSInput_Cache1(String[] args)
    {
        super(RDFEngine.RDF_XS1, args);

        resultWriter =
                new ResultWriter("RDF_XS1_cache_history.plot", this.pathToExperiment + "/gnuscript/data/");
    }

    @Override
    protected GraphStore1 initializeEngine() {
        context = new ERDFContext1(this.getPathToStore());

        GraphStore1 graphStore = (GraphStore1) context.getGraphByName("http://rdf4led.org");

        return graphStore;
    }

    int i = 0;

    @Override
    protected void doInput(File fileInput, GraphStore1 graphStore) {
        ParserNTriple<Integer> parser = new ParserNTriple<>(this.context);

        graphStore = (GraphStore1) parser.parseToGraphFromFile(fileInput, graphStore);

        //Log.print();

        resultWriter.write(i * 42000 + " " + Log.cacheHistoryToString());

        Log.reset();

        i++;
    }

    @Override
    protected long getSize(GraphStore1 graphStore) {
        return i * 42000;
    }

    @Override
    protected void close(GraphStore1 graphStore) {
        context.sync();

        graphStore.sync();
    }

    protected RDFXSInput_Cache1(RDFEngine engineType, String[] args) {
        super(engineType, args);
    }

    public static void main(String[] args)
    {
        String[] arg = new String[4];

        arg[0] = "/home/anhlt185/experiment";
        arg[1] = "LENOVO";
        arg[2] = "WATDIV";
        arg[3] = "10000000";

        RDFXSInput_Cache1 experiment = new RDFXSInput_Cache1(arg);
        experiment.execute();
    }

}
