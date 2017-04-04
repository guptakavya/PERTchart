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
import java.lang.Exception;

public class Edge {

    Vertex from; // head vertex
    Vertex to; // tail vertex
    int weight;// weight of edge

    /**
     * Constructor for Edge
     *
     * @param u : Vertex - Vertex from which edge starts
     * @param v : Vertex - Vertex on which edge lands
     * @param w : int - Weight of edge
     */
    Edge(Vertex u, Vertex v, int w) {
        from = u;
        to = v;
        weight = w;
    }

    /**
     * Method to find the other end end of an edge, given a vertex reference
     * This method is used for undirected graphs
     *
     * @param u : Vertex
     * @return : Vertex - other end of edge
     */
    public Vertex otherEnd(Vertex u) {
        assert from == u || to == u;
        // if the vertex u is the head of the arc, then return the tail else return the head
        if (from == u) {
            return to;
        } else {
            return from;
        }
    }

    public String toString() {
        return from + " " + to + " " + weight;
    }
}
