package dev.insight.benchmark.jenatdb;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.base.file.Location;
import dev.insight.benchmark.Experiment;
import dev.insight.benchmark.QueryExperiment;
import dev.insight.rdf_util.MemoryManager;

import java.util.Iterator;

/**
 * dev.insight.benchmark.jenatdb
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  28/09/17.
 */
public class JenaTDBQuery extends QueryExperiment<Model>
{
    public static void main(String[] args)
    {
        Experiment experiment = new JenaTDBQuery(args);

                   experiment.execute();
    }

    protected JenaTDBQuery(String[] args)
    {
        super(RDFEngine.JENATDB, args);
    }

    protected Model intializeEngine()
    {
        Location location = Location.create(this.getPathToStore());

        Model model = ModelFactory.createModelForGraph(TDBFactory.createDataset(location).getDefaultModel().getGraph());

        return model;
    }

    protected void doQueries(String queryString, Model model)
    {
        Query query = QueryFactory.create(queryString);

        QueryExecution queryExecution = QueryExecutionFactory.create(query, model);

        ResultSet resultSet = queryExecution.execSelect();

        printResult(resultSet);

        MemoryManager.log1();

    }

    protected void printResult(Object object)
    {
        if (object instanceof  ResultSet)
        {
            ResultSet resultSet = (ResultSet) object;
//            int i = 0;
            for (;resultSet.hasNext();)
            {
                QuerySolution querySolution = resultSet.nextSolution();

                System.out.println(querySolution.toString());
//                i++;

//                if (i==1) MemoryManager.log1();
                Iterator<String> vars = querySolution.varNames();

                for (String var="";vars.hasNext();var=vars.next()){
                    System.out.println(querySolution.get(var).toString());
                }
//                System.out.println(i);

            }
//            System.out.println(i);
        }

    }
}
