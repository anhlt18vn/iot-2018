package dev.insight.benchmark.rdf4led;

import dev.insight.benchmark.Experiment;
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

/**
 * dev.insight.benchmark.rdf4led
 *
 * <p>TODO: Add class description
 *
 * <p>Author: Anh Le_Tuan Email: anh.letuan@insight-centre.org
 *
 * <p>Date: 05/10/17.
 */
public class RDF4LedQuery extends QueryExperiment<GraphStore2> {

  public static void main(String[] args) {

    Experiment experiment = new RDF4LedQuery(args);

    experiment.execute();
  }

  public ERDFContext2 context;

  ParserSPARQL11<Integer> parser;

  protected RDF4LedQuery(String[] args) {
    super(RDFEngine.RDF4LED, args);

    context = new ERDFContext2(this.getPathToStore());

    parser = new ParserSPARQL11<>(context);
  }

  @Override
  protected GraphStore2 intializeEngine() {
    GraphStore2 graphStore = (GraphStore2) context.getGraphByName("http://rdf4led.org");

    return graphStore;
  }

  @Override
  protected void doQueries(String queryString, GraphStore2 graphStore) {
    Query<Integer> query = new Query<>(context);

    query = parser.parse(query, queryString);

    Config.R = true;

    AlgebraGenerator<Integer> algebraGenerator = new AlgebraGenerator<>(context);

    Op<Integer> op = algebraGenerator.compile(query);

    QueryExecutor queryExecutor = new QueryExecutor(context, graphStore);

    Forwarder<Integer> opForward = queryExecutor.compile(op, new ForwarderPrint<>(context));

    try {
      opForward.process(new MappingHashMap<Integer>());
    }
    catch (NullPointerException e) {
      //e.printStackTrace();
    }

    MemoryManager.log1();
  }

  @Override
  protected void printResult(Object resultSet) {}
}