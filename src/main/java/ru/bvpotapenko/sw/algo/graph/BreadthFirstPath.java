package ru.bvpotapenko.sw.algo.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BreadthFirstPath {
    private Map<Integer, Boolean> marked;
    private Map<Integer, Integer> edgeTo;
    private Map<Integer, Integer> distTo;
    private final boolean ignoreWeights;
    private int source;
    private static final int INF = Integer.MAX_VALUE;

    public BreadthFirstPath(Graph g, int source, boolean ignoreWeights) {
        if (source < 0) {
            throw new IllegalArgumentException("Negative Vertex index");
        }
        this.source = source;
        edgeTo = new HashMap<>(g.vertexCount());
        marked = new HashMap<>(g.vertexCount());
        distTo = new HashMap<>(g.vertexCount());
        this.ignoreWeights = ignoreWeights;
        bfs(g);
    }

    private void bfs(Graph g) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked.put(source, true);
        distTo.put(source, 0);

        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            for (Graph.Edge w :
                    g.adjList(vertex)) {
                if (!marked.containsKey(w.to) || !marked.get(w.to)) {
                    marked.put(w.to, true);
                    edgeTo.put(w.to, vertex);
                    int distance;
                    if (ignoreWeights){
                        distance = distTo.get(vertex) + 1;
                    }else{
                        distance = distTo.get(vertex) + w.weight;
                    }
                    distTo.put(w.to, distance);
                    queue.addLast(w.to);
                }
            }
        }
    }

    public boolean hasPathTo(int dist) {
        return marked.containsKey(dist);
    }

    public LinkedList<Integer> pathTo(int dist) {
        if (!hasPathTo(dist)) {
            return null;
        }

        LinkedList<Integer> stack = new LinkedList<>();

        int vertex = dist;
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo.get(vertex);
        }
        return stack;
    }

    public int distTo(int dist) {
        return distTo.containsKey(dist) ? distTo.get(dist) : INF;
    }
}
