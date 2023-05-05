package kelasKedua.models;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

public final class EmployeeGraph {
    // private HelloJGraphT() {
    // } // ensure non-instantiability.

    /**
     * The starting point for the demo.
     *
     * @param args ignored.
     *
     * @throws URISyntaxException if invalid URI is constructed.
     * @throws ExportException    if graph cannot be exported.
     */
    public static void main(String[] args) {
        DefaultDirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        directedGraph.addVertex("v1");
        directedGraph.addVertex("v2");
        directedGraph.addVertex("v3");
        directedGraph.addEdge("v1", "v2");
        
    }
}