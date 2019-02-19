package ru.bvpotapenko.sw.algo.graph;

/**
 * Graph demo
 */
public class App {
    public static void main(String[] args) {
        Graph g = new Graph(false);
        g.addEdge(0, 2, 1);
        g.addEdge(0, 1, 2);
        g.addEdge(1, 2, 3);
        g.addEdge(5, 3, 7);
        g.addEdge(3, 4, 2);
        g.addEdge(3, 2, 5);
        g.addEdge(4, 2, 4);
        g.addEdge(5, 0, 6);

        BreadthFirstPath bfp = new BreadthFirstPath(g, 0, false);

        System.out.println(bfp.hasPathTo(3));
        System.out.println(bfp.pathTo(3));
        System.out.println(bfp.distTo(3));



    }
}
