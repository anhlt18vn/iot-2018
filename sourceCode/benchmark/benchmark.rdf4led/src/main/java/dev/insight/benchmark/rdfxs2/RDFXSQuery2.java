package dev.insight.benchmark.rdfxs2;

import dev.insight.benchmark.QueryExperiment;
import dev.insight.erdf.Config;
import dev.insight.erdf.ERDFContext2;
import dev.insight.erdf.graph.storage.GraphStore2;
import dev.insight.erdf.query.engine.Forwarder;
import dev.insight.erdf.query.engine.ForwarderPrint;
import dev.insight.erdf.query.engine.QueryExecutor;
import dev.insight.erdf.query.sparql.Query;
import dev.insight.erdf.query.sparql.algebra.AlgebraGenerator;
import dev.insight.erdf.query.sparql.algebra.Op;
import dev.insight.erdf.query.sparql.parser.lang.ParserSPARQL11;
import dev.insight.erdf.utility.data.MappingHashMap;
import dev.insight.rdf_util.MemoryManager;

import java.io.File;

/**
 * dev.insight.benchmark.rdfxs2
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le-Tuan
 * <p>
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  19/02/18.
 */
public class RDFXSQuery2 extends QueryExperiment<GraphStore2> {

  public ERDFContext2 context;

  protected RDFXSQuery2(String[] args) {
    super(RDFEngine.RDF_XS1, args);

    context = new ERDFContext2(this.getPathToStore());
    parser = new ParserSPARQL11<>(context);
  }

  public static void main(String[] args)
  {
    String[] arg = new String[4];

    arg[0] = "/home/anhlt185/experiment";
    arg[1] = "LENOVO";
    arg[2] = "watdiv100";
    arg[3] = "10000000";

    RDFXSQuery2 experiment = new RDFXSQuery2(arg);

    experiment.execute();


  }

  ParserSPARQL11<Integer> parser;

  @Override
  protected GraphStore2 intializeEngine() {

    GraphStore2 graphStore2 = (GraphStore2) this.context.getGraphByName("http://rdf4led.org");

    return graphStore2;
  }

  @Override
  protected void doQueries(String queryString, GraphStore2 graphStore) {
    Query<Integer> query = new Query<>(context);

    query = parser.parse(query, queryString);

    AlgebraGenerator<Integer> algebraGenerator = new AlgebraGenerator<>(context);

    Op<Integer> op = algebraGenerator.compile(query);

    Config.R = true;

    QueryExecutor queryExecutor = new QueryExecutor(context, graphStore);

    Forwarder<Integer> opForward = queryExecutor.compile(op, new ForwarderPrint<>(context));

    opForward.process(new MappingHashMap<Integer>());
  }

  @Override
  protected void printResult(Object resultSet) {

  }
}
