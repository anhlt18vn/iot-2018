//package dev.insight.benchmark.rdfxs;
//
//import dev.insight.benchmark.InputExperiment;
//import dev.insight.benchmark.ResultWriter;
//import dev.insight.erdf.Context;
//import dev.insight.erdf.ERDFContext;
//import dev.insight.erdf.graph.parser.ParserNTriple;
//import dev.insight.erdf.graph.storage.GraphStore;
//import dev.insight.rdf_util.MemoryManager;
//import dev.insight.erdf.utility.Log;
//
//import java.io.File;
//
///**
// * PACKAGE_NAME
// *
// * <p>TODO: Add class description
// *
// * <p>Author: Anh Le_Tuan Email: anh.letuan@insight-centre.org
// *
// * <p>Date: 04/10/17.
// */
//public class RDFXSInput_Cache extends InputExperiment<GraphStore> {
//    public Context<Integer> context;
//
//    ResultWriter resultWriter;
//
//    protected RDFXSInput_Cache(String[] args)
//    {
//        super(RDFEngine.RDF_XS, args);
//
//        resultWriter =
//                new ResultWriter("RDF_XS_cache_history.plot", this.pathToExperiment + "/gnuscript/data/");
//    }
//
//    @Override
//    protected GraphStore initializeEngine() {
//        context = new ERDFContext(this.getPathToStore());
//
//        GraphStore graphStore = (GraphStore) context.getGraphByName("http://rdfxs.org");
//
//        return graphStore;
//    }
//
//    int i = 0;
//
//    @Override
//    protected void doInput(File fileInput, GraphStore graphStore) {
//
//        ParserNTriple<Integer> parser = new ParserNTriple<>(this.context);
//
//        graphStore = (GraphStore) parser.parseToGraphFromFile(fileInput, graphStore);
//
//        MemoryManager.log1();
//
//        Log.print();
//
//        resultWriter.write(i * 50000 + " " + Log.cacheHistoryToString());
//
//        Log.reset();
//
//        i++;
//    }
//
//    @Override
//    protected long getSize(GraphStore graphStore) {
//        return i * 50000;
//    }
//
//    @Override
//    protected void close(GraphStore graphStore) {
//        context.sync();
//
//        graphStore.sync();
//    }
//
//    public static void main(String[] args)
//    {
//        String[] arg = new String[4];
//
//        arg[0] = "/home/anhlt185/experiment";
//        arg[1] = "LENOVO";
//        arg[2] = "WATDIV";
//        arg[3] = "10000000";
//
//        RDFXSInput_Cache experiment = new RDFXSInput_Cache(arg);
//        experiment.execute();
//    }
//
//}
