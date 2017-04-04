/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp4;

import static java.lang.Integer.min;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Kavya Gupta
 */
public class CriticalPaths {

    int criticalPathLength;
    private ArrayList<Vertex> criticalVertexList;
    public ArrayList<ArrayDeque<Vertex>> allCriticalPaths;

    //Constructor 
    CriticalPaths(Graph g) {

        //add s and t
        g.s = g.v.get(g.v.size() - 2);
        g.t = g.v.get(g.v.size() - 1);
        //edges from s to indegree=0 and from t to outdegree=0
        for (Vertex u : g) {
            u.indegree = u.revAdj.size();
            u.outdegree = u.adj.size();
            if (u.indegree == 0 && u != g.s) {
                g.addEdge(g.s, u, 1);
                u.indegree++;
            }
            if (u.outdegree == 0 && u != g.t) {
                g.addEdge(u, g.t, 1);
                u.outdegree++;
            }

        }
        List<Vertex> dfsTopList = DFSTopOrder(g);
        List<Vertex> algo1List = Algo1(g);
        EC_calculate(algo1List);
        LC_calculate(dfsTopList);


    }

    
    //method to find LC and slack
    private void LC_calculate(List<Vertex> topologicalOrder) {
        Vertex t = topologicalOrder.get(0);
        t.lc = t.ec;
        for (Vertex u : topologicalOrder) {
            u.lc = t.lc;
        }
        for (Vertex u : topologicalOrder) {
            for (Edge edge : u.revAdj) {
                Vertex p = edge.otherEnd(u);
                p.lc = Math.min(p.lc, u.lc - u.d);
                p.slack = p.lc - p.ec;

            }
        }

    }

    //method to find EC
    private void EC_calculate(List<Vertex> topologicalOrder) {
        topologicalOrder.get(0).ec = 0;
        for (Vertex u : topologicalOrder) {
            for (Edge edge : u.adj) {
                Vertex v = edge.otherEnd(u);
                if (v.ec < v.d + u.ec) {
                    v.ec = v.d + u.ec;
                }
            }

        }

    }

    //method for Topological Ordering using Take 1: Algorithm 1
    private List<Vertex> Algo1(Graph graph) {

        List<Vertex> topList = new LinkedList<Vertex>();
        Queue<Vertex> q = new LinkedList<>();
        int count = 0;
        for (Vertex u : graph) {
            if (u.indegree == 0) {
                q.add(u);
            }
        }
        while (!q.isEmpty()) {
            Vertex u = q.remove();
            topList.add(u);
            u.top = ++count;
            for (Edge edge : u.adj) {
                Vertex v = edge.otherEnd(u);
                v.indegree--;

                if (v.indegree == 0) {
                    q.add(v);
                }
            }
        }
        if (count != graph.size) {
            return null;
        }
        return topList;
    }

    //method to calculate the topological order of a given graph (outer loop) : Algorithm 2
    private List<Vertex> DFSTopOrder(Graph graph) {
        List<Vertex> dfsList = new LinkedList<Vertex>();
        for (Vertex vertex : graph) {
            if (!vertex.seen) {
                DFSVisit(vertex, dfsList);
            }
        }
        return dfsList;
    }

    //method for the DFS traversal of the graph
    public static void DFSVisit(Vertex vertex, List<Vertex> list) {
        vertex.seen = true;
        for (Edge e : vertex.adj) {
            Vertex other = e.otherEnd(vertex);
            if (!other.seen) {
                DFSVisit(other, list);
            }
        }

        list.add(vertex);
    }

    //calculating the critical paths in the graph 
    void findCriticalPaths(Graph g) {
        Vertex u = g.s;
        while (u != g.t) {
            for (Edge e : u.adj) {
                Vertex v = e.otherEnd(u);
                if (v.slack == 0 && v.lc == (u.lc + v.d)) {
                    u = v;
                    g.criticalPath.add(v);
                    g.lenCriticalPath
                            += v.d;
                    break;
                }
            }
        }
    }

}
