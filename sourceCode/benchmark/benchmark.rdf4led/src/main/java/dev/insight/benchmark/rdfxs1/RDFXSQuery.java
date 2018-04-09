//package dev.insight.benchmark.rdfxs1;
//
//import dev.insight.benchmark.Experiment;
//import dev.insight.benchmark.QueryExperiment;
//import dev.insight.erdf.ERDFContext;
//import dev.insight.erdf.graph.storage.GraphStore;
//import dev.insight.erdf.query.sparql.parser.lang.ParserSPARQL11;
//
///**
// * dev.insight.benchmark.rdf4led
// * <p>
// * TODO: Add class description
// * <p>
// * Author:  Anh Le_Tuan
// * Email:   anh.letuan@insight-centre.org
// * <p>
// * Date:  05/10/17.
// */
//public class RDF4LedQuery extends QueryExperiment<GraphStore>
//{
//    public static void main(String[] args)
//    {
//        Experiment experiment = new RDF4LedQuery(args);
//
//                   experiment.execute();
//    }
//
//    public ERDFContext context;
//
//    ParserSPARQL11<Integer> parser;
//
//    protected RDF4LedQuery(String[] args)
//    {
//        super(RDFEngine.RDF_XS, args);
//
//        context = new ERDFContext(this.getPathToStore());
//
//        parser  = new ParserSPARQL11<>(context);
//    }
//
//    @Override
//    protected GraphStore intializeEngine()
//    {
//        GraphStore graphStore = (GraphStore) context.getGraphByName("http://rdfxs.org");
//
//        return graphStore;
//    }
//
//    @Override
//    protected void doQueries(String queryString, GraphStore graphStore)
//    {
////        Query<Integer> query = new Query<>(context);
////
////                       query = parser.parse(query, queryString);
////
////        AlgebraGenerator<Integer> algebraGenerator = new AlgebraGenerator<>(context);
////
////        Op<Integer> op = algebraGenerator.compile(query);
////
////        QueryExecutor queryExecutor = new QueryExecutor(context, graphStore);
////
////        Forwarder<Integer> opForward = queryExecutor.compile(op, new ForwarderPrint<>(context));
////
////                           opForward.process(new MappingHashMap<Integer>());
//
//    }
//
//    @Override
//    protected void printResult(Object resultSet)
//    {
//
//    }
//}
