package kelasKedua.services;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kelasKedua.models.RawEmployeeData;

public final class GraphRawProcessing {
    private static RawEmployeeData start;

    public static DefaultDirectedGraph<RawEmployeeData, DefaultEdge> rawEmployeeDataToEmployeeGraph(String path) throws StreamReadException, DatabindException, IOException{
        //access json file from directory
        File json = new File(path);

        //initialize object mapper from jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        
        //read json file and map it to array of RawEmployeeData class
        /* 
         * rawEmployeeData is object that store raw data from json file
         */
        RawEmployeeData[] rawEmployeeDatas = objectMapper.readValue(json, RawEmployeeData[].class);
        DefaultDirectedGraph<RawEmployeeData, DefaultEdge> directedGraph = new DefaultDirectedGraph<RawEmployeeData, DefaultEdge>(DefaultEdge.class);
        
        for (RawEmployeeData rawEmployeeData : rawEmployeeDatas) {
            if (rawEmployeeData.getParent() == null) {
                start = rawEmployeeData;
            }
            directedGraph.addVertex(rawEmployeeData);
        }
        for (RawEmployeeData rawEmployeeData : rawEmployeeDatas) {
            if (rawEmployeeData.getParent() != null) {
                RawEmployeeData parentVertex = findEmployee(rawEmployeeData.getParent(), directedGraph, start);
                directedGraph.addEdge(parentVertex, rawEmployeeData);
            }
        }
        return directedGraph;
    }

    public static RawEmployeeData getStartVertex() {
        return start;
    }

    private static RawEmployeeData findEmployee(int employeeId, Graph<RawEmployeeData, DefaultEdge> graph, RawEmployeeData start){
        Iterator<RawEmployeeData> iterator = new DepthFirstIterator<>(graph, start);
        while(iterator.hasNext()){
            RawEmployeeData rawEmployeeData = iterator.next();
            if(rawEmployeeData.getEmployee_id() == employeeId){
                return rawEmployeeData;
            }
        }
        return null;
    }
}
