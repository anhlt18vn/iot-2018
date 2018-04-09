package dev.insight.benchmark.jenatdb;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.base.file.Location;
import dev.insight.benchmark.Experiment;
import dev.insight.benchmark.InputExperiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
public class JenaTDBInput  extends InputExperiment<Model>
{

    public static void main(String[] args)
    {
        Experiment experiment = new JenaTDBInput(args);

                   experiment.execute();
    }


    protected JenaTDBInput(String[] args)
    {
        super(RDFEngine.JENATDB, args);
    }


    protected Model initializeEngine()
    {

        Location location = Location.create(this.getPathToStore());

        Model model = ModelFactory.createModelForGraph(TDBFactory.createDataset(location).getDefaultModel().getGraph());

        return model;
    }

    protected void doInput(File fileInput, Model model)
    {
        try
        {
            model.read(new FileInputStream(fileInput), null, "N-TRIPLES");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    protected long getSize(Model model)
    {
        return model.size();
    }

    protected void close(Model model)
    {
        TDB.sync(model.getGraph());
    }
}
