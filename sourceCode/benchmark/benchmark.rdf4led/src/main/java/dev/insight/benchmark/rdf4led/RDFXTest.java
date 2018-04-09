//package dev.insight.benchmark.rdfxs;
//
///**
// * dev.insight.benchmark.rdf4led
// * <p>
// * TODO: Add class description
// * <p>
// * Author:  Anh Le_Tuan
// * Email:   anh.letuan@insight-centre.org
// * <p>
// * Date:  04/10/17.
// */
//public class RDFXTest
//{
//    public static void main(String[] args)
//    {
//        input();
////        query();
//    }
//
//    public static void input()
//    {
//
//
//        String[] args = new String[4];
//
//        args[0] = "/home/anhlt185/experiment";
//        args[1] = "LENOVO";
//        args[2] = "BSBM";
//        args[3] = "10000000";
//
//        RDF4LedInput experiment = new RDF4LedInput(args);
//        experiment.execute();
//    }
//
//
//    public static void query()
//    {
//        String[] args = new String[4];
//
//        args[0] = "/home/anhlt185/experiment";
//        args[1] = "LENOVO";
//        args[2] = "BSBM";
//        args[3] = "10000000";
//
//        RDF4LedQuery experiment = new RDF4LedQuery(args);
//
//        System.setOut(experiment.printConsole);
//
//        experiment.execute();
//
//        //GraphStore gs = (GraphStore) experiment.context.getGraphByName("http://rdfxs.org");
//
//        //System.out.println(gs.size());
//    }
//
//    public static void dumbApage()
//    {
////        ERDFContext context = new ERDFContext("/home/anhlt185/experiment/store/RDF_XS");
////
////        GraphStore  gs      = (GraphStore) context.getGraphByName("http://rdfxs.org");
////
////        TripleIndex POS = gs.POS;
////
////        Cursor cursor = POS.searchCursor(new int[]{2908220, 346140461, 346140205});
////
////        PageEntry pageEntry = POS.bufferLayer.findPageEntry(cursor.getPageOrder(), 3);
//
////        int[] data = ((PageCompressedTriple) pageEntry.getPage()).getData();
//
////        ArrayUtil.println(data, 1,3, 0);
//
//    }
//}
