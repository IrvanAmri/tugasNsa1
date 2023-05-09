package kelasKedua.services;

import java.io.IOException;
import java.util.Iterator;

import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import kelasKedua.models.RawEmployeeData;

public class GraphManagement {

    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        DefaultDirectedGraph<RawEmployeeData, DefaultEdge> directedGraph = GraphRawProcessing.rawEmployeeDataToEmployeeGraph("D:/kuliah(Amri)/Sem 6/Algoritma dan Struktur Data/Periode 2/kode/dataset/source/MOCK_DATA.json");

        RawEmployeeData start = GraphRawProcessing.getStartVertex();

        Iterator<RawEmployeeData> iterator = new DepthFirstIterator<>(directedGraph, start);
        while (iterator.hasNext()) {
            RawEmployeeData rawEmployeeData = iterator.next();
            System.out.println(rawEmployeeData.getEmployee_id());
        }

        //TODO: visualize graph
    }
}