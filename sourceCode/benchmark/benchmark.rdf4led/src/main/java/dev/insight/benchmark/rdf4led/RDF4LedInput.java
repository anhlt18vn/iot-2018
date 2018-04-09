package dev.insight.benchmark.rdf4led;

import dev.insight.benchmark.Experiment;
import dev.insight.benchmark.InputExperiment;
import dev.insight.benchmark.ResultWriter;
import dev.insight.erdf.Config;
import dev.insight.erdf.Context;
import dev.insight.erdf.ERDFContext2;
import dev.insight.erdf.graph.parser.ParserNTriple;
import dev.insight.erdf.graph.storage.GraphStore2;

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
public class RDF4LedInput extends InputExperiment<GraphStore2> {

  public static void main(String[] args) {

    Experiment experiment = new RDF4LedInput(args);

    experiment.execute();
  }

  // ===================================================================================================;
  public Context<Integer> context;

  ResultWriter resultWriter;

  protected RDF4LedInput(String[] args) {
    super(RDFEngine.RDF4LED, args);
  }

  @Override
  protected GraphStore2 initializeEngine()
  {
    context = new ERDFContext2(this.getPathToStore());

    GraphStore2 graphStore = (GraphStore2) context.getGraphByName("http://rdf4led.org");

    return graphStore;
  }

  int i = 0;

  @Override
  protected void doInput(File fileInput, GraphStore2 graphStore) {

    ParserNTriple<Integer> parser = new ParserNTriple<>(this.context);

    graphStore = (GraphStore2) parser.parseToGraphFromFile(fileInput, graphStore);

    i++;
  }

  @Override
  protected long getSize(GraphStore2 graphStore) {
    return i * 100000;
  }

  @Override
  protected void close(GraphStore2 graphStore) {
    context.sync();
    graphStore.sync();
  }

}
