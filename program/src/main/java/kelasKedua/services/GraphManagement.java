package kelasKedua.services;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

import kelasKedua.models.RawEmployeeData;

public class GraphManagement {

    public static void main(String[] args)
            throws StreamReadException, DatabindException, IOException, InterruptedException {

        DefaultDirectedGraph<RawEmployeeData, DefaultEdge> directedGraph = GraphRawProcessing
                .rawEmployeeDataToEmployeeGraph(
                        "D:/kuliah(Amri)/Sem 6/Algoritma dan Struktur Data/Periode 2/kode/dataset/source/MOCK_DATA.json");

        RawEmployeeData start = GraphRawProcessing.getStartVertex();

        Iterator<RawEmployeeData> iterator = new DepthFirstIterator<>(directedGraph, start);
        while (iterator.hasNext()) {
            RawEmployeeData rawEmployeeData = iterator.next();
            System.out.println(rawEmployeeData.getEmployee_id());
        }

        // TODO: visualize graph
        // JGraphModelAdapter<RawEmployeeData, DefaultEdge> graphAdapter = new
        // JGraphModelAdapter<RawEmployeeData, DefaultEdge>(
        // directedGraph);
        // mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        // layout.execute(graphAdapter.getDefaultParent());

        // BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null,
        // 2.5, Color.BLACK, true, null);
        // File imgFile = new File(
        // "D:/kuliah(Amri)/Sem 6/Algoritma dan Struktur Data/Periode
        // 2/kode/dataset/result/Java/graph.png");
        // ImageIO.write(image, "PNG", imgFile);

        // create a visualization using JGraph, via an adapter ???????
        // JFrame frame = new JFrame();
        // frame.setSize(400, 400);
        // JGraph jgraph = new JGraph(graphAdapter);
        // frame.getContentPane().add(jgraph);
        // frame.setVisible(true);
        // while (true) {
        // Thread.sleep(2000);
        // }
    }
}