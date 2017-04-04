/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp4;

/**
 *
 * @author Kavya Gupta
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Vertex implements Iterable<Edge> {

    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int d;  // duration of task corresponding to vertex
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
    //public boolean visited;
    int slack;
    int ec;
    int lc;
    int indegree;
    int outdegree;
    int top;
    
    

    /**
     * Constructor for the vertex
     *
     * @param n : int - name of the vertex
     */
    Vertex(int n) {
        name = n;
        seen = false;
        d = Integer.MAX_VALUE;
        adj = new ArrayList<Edge>();
        revAdj = new ArrayList<Edge>();
        indegree=0;
        outdegree=0;
        top=0;
        slack=0;
        lc=0;
        ec=0;
        /* only for directed graphs */
        //visited = false;
    }

    public Iterator<Edge> iterator() {
        return adj.iterator();
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
        return Integer.toString(name);
    }
}
