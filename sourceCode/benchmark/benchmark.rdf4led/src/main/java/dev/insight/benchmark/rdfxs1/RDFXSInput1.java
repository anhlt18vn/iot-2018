package dev.insight.benchmark.rdfxs1;

import dev.insight.benchmark.Experiment;
import dev.insight.benchmark.InputExperiment;
import dev.insight.erdf.Context;
import dev.insight.erdf.ERDFContext2;
import dev.insight.erdf.graph.parser.ParserNTriple;
import dev.insight.erdf.graph.storage.GraphStore2;
import dev.insight.rdf_util.MemoryManager;
import dev.insight.erdf.utility.Log;
import dev.insight.erdf1.ERDFContext1;
import dev.insight.erdf1.GraphStore1;

import java.io.File;

/**
 * PACKAGE_NAME
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  04/10/17.
 */
public class RDFXSInput1 extends InputExperiment<GraphStore2>
{

    public static void main(String[] args)
    {
        Experiment experiment = new RDFXSInput1(args);

                   experiment.execute();
    }


    //===================================================================================================;
    public Context<Integer> context;

    protected RDFXSInput1(String[] args)
    {
        super(RDFEngine.RDF_XS1, args);
    }

    @Override
    protected GraphStore2 initializeEngine()
    {
        context = new ERDFContext2(this.getPathToStore());

        GraphStore2 graphStore = (GraphStore2) context.getGraphByName("http://rdf4led.org");

        return graphStore;
    }

    int i=0;

    @Override
    protected void doInput(File fileInput, GraphStore2 graphStore)
    {
        ParserNTriple<Integer> parser = new ParserNTriple<>(this.context);

        graphStore = (GraphStore2) parser.parseToGraphFromFile(fileInput, graphStore);

        MemoryManager.log1();

        //Log.print();

        Log.reset();

        i++;
    }

    @Override
    protected long getSize(GraphStore2 graphStore)
    {
        return i*42000;
    }

    @Override
    protected void close(GraphStore2 graphStore)
    {
        context.sync();

        graphStore.sync();
    }
}
