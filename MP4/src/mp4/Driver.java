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
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Driver {

    public static void main(String[] args) throws FileNotFoundException {
        //int d;
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        Graph g = Graph.readDirectedGraph(in);
        for (Vertex u : g) {
            u.d = in.nextInt();
        }

        Timer timer = new Timer();
        CriticalPaths cp = new CriticalPaths(g);
        cp.findCriticalPaths(g);
        System.out.println(g.lenCriticalPath);
        g.criticalPath.remove(g.criticalPath.size() - 1);
        int numberOfCriticalNodes = g.criticalPath.size();
        for (Vertex u : g.criticalPath) {
            System.out.print(u + " ");
        }
        System.out.println();
        System.out.println("Task\tEC\tLC\tSlack");
        for (Vertex u : g) {
            System.out.println(u + "\t" + u.ec + "\t" + u.lc + "\t" + u.slack);
        }

        System.out.println(numberOfCriticalNodes);
        System.out.println("1");
        for (Vertex u : g.criticalPath) {
            System.out.print(u + " ");
        }
System.out.println();
        System.out.println(timer.end());
    }
}
